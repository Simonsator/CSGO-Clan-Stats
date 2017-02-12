package de.simonsator.partyandfriends.clans.stats.csgo;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class PlayerData {
	public final int BOMB_PLANTED;
	public final int HEAD_SHOTS;
	public final int DEATHS;
	public final int KILLS;

	public PlayerData(int kills, int deaths, int pHeadShots, int pBombPlanted) {
		KILLS = kills;
		DEATHS = deaths;
		HEAD_SHOTS = pHeadShots;
		BOMB_PLANTED = pBombPlanted;
	}
}
