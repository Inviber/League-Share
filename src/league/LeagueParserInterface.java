package league;

import java.util.ArrayList;

public interface LeagueParserInterface {

	public void parseLeague(String leagueID);
	
	public String getLeagueID();
	public String getLeagueName();
	public String getOwnerID();
	public String getSport();
	public String getDescription();
	public ArrayList<String> getCasterIDs();
	public ArrayList<String> getTeamIDs();
	public ArrayList<String> getMatchIDs();
	
}
