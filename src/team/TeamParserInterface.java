package team;

import java.util.ArrayList;

public interface TeamParserInterface {

	public void parseTeam(String leagueID, String teamID);
	
	public String getLeagueID();
	public String getTeamName();
	public String getZipcode();
	public ArrayList<String> getPlayerIDs();
	public String getTeamID();
}
