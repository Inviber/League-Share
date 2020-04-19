package league;

import java.util.ArrayList;

import database.DatabaseHelper;

public class LeagueGenerator {
	
	private LeagueParser leagueParser;
	private LeagueDBInterator leagueDBInterator;
	
	public LeagueGenerator(DatabaseHelper dbHelper)
	{
		this.leagueDBInterator = new LeagueDBInterator(dbHelper);
		this.leagueParser = new LeagueParser(leagueDBInterator);
	}
	
	public League generateLeague(String leagueID)
	{
		leagueParser.parseLeague(leagueID);
		  
		  League league = new League(leagueID, leagueParser.getLeagueName(), leagueParser.getSport(), leagueParser.getOwnerID(), 
				  	leagueParser.getDescription(), leagueParser.getCasterIDs(), leagueParser.getTeamIDs(), leagueParser.getMatchIDs(), 
				  	leagueParser.getTrackedStatisticsIDs(), leagueDBInterator);
		  
		  return league;
	}
	
	public ArrayList<String> searchLeagueByName(String search) 
	{
		// returns an array list of the league ID and name, arrayList.at(0) = id, arraylist.at(1) = leageName
		return leagueDBInterator.getLeagueByName(search);
	}
	
	public void createLeague(String leagueName, String ownerID, String sport, String description)
	{
		leagueDBInterator.createLeague(leagueName, ownerID, sport, description);
	}
	
	public void deleteLeague(String leagueID)
	{
		leagueDBInterator.deleteLeague(leagueID);
	}
	
	public LeagueDBInterator getLeagueDBInterator()
	{
		return this.leagueDBInterator;
	}
}
