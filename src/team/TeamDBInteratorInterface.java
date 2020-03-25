package team;

import org.json.simple.JSONObject;

public interface TeamDBInteratorInterface {

	public JSONObject getTeamDetails(String leagueID, String teamID);
}
