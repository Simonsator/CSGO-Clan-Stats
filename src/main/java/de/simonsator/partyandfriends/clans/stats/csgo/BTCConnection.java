package de.simonsator.partyandfriends.clans.stats.csgo;

import de.simonsator.partyandfriends.communication.sql.SQLCommunication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author simonbrungs
 * @version 1.0.0 30.11.16
 */
public class BTCConnection extends SQLCommunication {
	protected BTCConnection(String pDatabase, String pURL, String pUserName, String pPassword) {
		super(pDatabase, pURL, pUserName, pPassword);
	}

	public PlayerData getPlayerData(UUID pUUID) {
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		try {
			prepStmt = con.prepareStatement(
					"SELECT KILLS, DEATHS, HEADSHOTS, BOMBPLANTED Tode FROM `" + DATABASE + "`.`CounterStrike` WHERE UUID=? LIMIT 1");
			prepStmt.setString(1, pUUID.toString());
			rs = prepStmt.executeQuery();
			if (rs.next())
				return new PlayerData(rs.getInt("KILLS"), rs.getInt("DEATHS"), rs.getInt("HEAD_SHOTS"),
						rs.getInt("BOMB_PLANTED"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(rs, prepStmt);
		}
		return null;
	}
}
