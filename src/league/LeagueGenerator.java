package league;

import database.DatabaseHelper;

public class LeagueGenerator {
	
	private LeagueParser leagueParser;
	private LeagueDBInterator leagueUpdater;
	
	public LeagueGenerator(DatabaseHelper dbHelper)
	{
		this.leagueParser = new LeagueParser(leagueUpdater);
		this.leagueUpdater = new LeagueDBInterator(dbHelper);
	}
	
	public League generateLeague(String leagueID)
	{
		leagueParser.parseLeague(leagueID);
		  
		  League league = new League(leagueID, leagueParser.getLeagueName(), leagueParser.getSport(), leagueParser.getOwnerID(), 
				  	leagueParser.getDescription(), leagueParser.getCasterIDs(), leagueParser.getTeamIDs(), leagueParser.getMatchIDs(), leagueUpdater);
		  
		  return league;
	}
}
