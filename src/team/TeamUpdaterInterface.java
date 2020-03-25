package team;

import org.json.simple.JSONObject;

public interface TeamUpdaterInterface {

	public JSONObject getTeamDetails(String leagueID, String teamID);
}
