package league;

import database.DatabaseHelper;

public class LeagueGenerator {
	
	private LeagueParser leagueParser;
	private LeagueDBInterator leagueDBInterator;
	
	public LeagueGenerator(DatabaseHelper dbHelper)
	{
		this.leagueParser = new LeagueParser(leagueDBInterator);
		this.leagueDBInterator = new LeagueDBInterator(dbHelper);
	}
	
	public League generateLeague(String leagueID)
	{
		leagueParser.parseLeague(leagueID);
		  
		  League league = new League(leagueID, leagueParser.getLeagueName(), leagueParser.getSport(), leagueParser.getOwnerID(), 
				  	leagueParser.getDescription(), leagueParser.getCasterIDs(), leagueParser.getTeamIDs(), leagueParser.getMatchIDs(), leagueDBInterator);
		  
		  return league;
	}
}
