package league;

import database.DatabaseHelper;

public class LeagueGenerator {
	
	private LeagueParser leagueParser;
	private LeagueUpdater leagueUpdater;
	
	public LeagueGenerator(DatabaseHelper dbHelper)
	{
		this.leagueParser = new LeagueParser(dbHelper, leagueUpdater);
		this.leagueUpdater = new LeagueUpdater(dbHelper);
	}
	
	public League generateLeague(String leagueID)
	{
		leagueParser.parseLeague(leagueID);
		  
		  League league = new League(leagueID, leagueParser.getLeagueName(), leagueParser.getSport(), leagueParser.getOwnerID(), 
				  	leagueParser.getDescription(), leagueParser.getCasterIDs(), leagueParser.getTeamIDs(), leagueParser.getMatchIDs(), leagueUpdater);
		  
		  //System.out.println(leagueName + " " + sport  + " " + description  + " " +  casterIDs + " " + teamIDs + " " + matchIDs);
		  return league;
	}
}
