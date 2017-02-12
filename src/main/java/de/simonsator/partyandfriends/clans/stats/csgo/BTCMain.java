package de.simonsator.partyandfriends.clans.stats.csgo;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author simonbrungs
 * @version 1.0.0 20.01.17
 */
public class BTCMain extends Plugin implements ClanStat {
	private Configuration config;
	private BTCConnection connection;
	private Configuration messagesConfig;

	public void onEnable() {
		try {
			config = (new BTCConfig(new File(getDataFolder(), "config.yml"))).getCreatedConfiguration();
			messagesConfig = (new BTCMessages(Language.OWN, new File(getDataFolder(), "messages.yml"))).getCreatedConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection = new BTCConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(this, this);
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<PlayerData>();
		for (PAFPlayer player : players) {
			PlayerData data = connection.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int deaths = 0;
		int headShots = 0;
		int bombPlanted = 0;
		int kills = 0;
		for (PlayerData data : playerData) {
			bombPlanted += data.BOMB_PLANTED;
			headShots += data.HEAD_SHOTS;
			deaths += data.DEATHS;
			kills += data.KILLS;
		}
		pSender.sendMessage(messagesConfig.getString("ClanStats.BombPlanted").replace("[BOMB_PLANTED]", bombPlanted + ""));
		pSender.sendMessage(messagesConfig.getString("ClanStats.Points").replace("[POINTS]", headShots + ""));
		pSender.sendMessage(messagesConfig.getString("ClanStats.Deaths").replace("[DEATHS]", deaths + ""));
		pSender.sendMessage(messagesConfig.getString("ClanStats.Kills").replace("[KILLS]", kills + ""));
	}

	public String getName() {
		return messagesConfig.getString("ClanStats.Name");
	}
}
