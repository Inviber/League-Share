package player;

import org.json.simple.JSONObject;

public interface PlayerDBInteratorInterface {

	public JSONObject getPlayerDetails(String leagueID, String teamID, String playerID, boolean print);
	public void updatePlayerStatistics(String leagueID, String teamID, String playerID, String statName, int statValue);
	public void createPlayer(String leagueID, String teamID, String firstName, String lastName);
	public void updatePlayer(String leagueID, String teamID, String playerID, String firstName, String lastName);
	public void deletePlayer(String leagueID, String teamID, String playerID);
}
