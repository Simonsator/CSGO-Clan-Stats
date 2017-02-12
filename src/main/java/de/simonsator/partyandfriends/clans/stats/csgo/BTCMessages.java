package de.simonsator.partyandfriends.clans.stats.csgo;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class BTCMessages extends LanguageConfiguration {

	public BTCMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("ClanStats.Name", "CSGO");
		set("ClanStats.Wins", "&7Der Clan hat &a[WON] &7Spiele gewonnen.");
		set("ClanStats.Played", "&7Der Clan hat &a[GAMES] &7Spiele gespielt.");
		set("ClanStats.Points", "&7Der Clan hat &a[POINTS] &7Punkte.");
		set("ClanStats.Deaths", "&7Die Leute in diesem Clan sind insgesammt &a[DEATHS]&7 mal gestorben.");
		set("ClanStats.Kills", "&7Die Leute in diesem Clan haben &a[KILLS] &7Leute getötet.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new BTCMessages(LANGUAGE.OWN, FILE)).getCreatedConfiguration();
	}
}
