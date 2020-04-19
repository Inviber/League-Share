package team;

import org.json.simple.JSONObject;

public interface TeamDBInteratorInterface {

	public JSONObject getTeamDetails(String leagueID, String teamID);
	public void createTeam(String leagueID, String teamName, String zipcode);
	public void updateTeam(String leagueID, String teamID, String teamName, String zipcode);
	public void deleteTeam(String leagueID, String teamID);
}
