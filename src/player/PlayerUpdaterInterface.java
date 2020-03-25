package player;

import org.json.simple.JSONObject;

public interface PlayerUpdaterInterface {

	public JSONObject getPlayerDetails(String leagueID, String teamID, String playerID, boolean print);
}
