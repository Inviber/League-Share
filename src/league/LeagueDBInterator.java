package league;

import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class LeagueDBInterator implements LeagueDBInteratorInterface  {
	
	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();

	
	public LeagueDBInterator(DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
	}
	
	public JSONObject getLeagueDetails(String leagueID , boolean print) 
	{
		JSONObject leagueData = null;
		Document leagueDocument = dbHelper.getDocument("Leagues", leagueID);

		try 
		{
			Object obj = parser.parse(leagueDocument.toJson());
			leagueData = (JSONObject) obj;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (print)
		{
			System.out.println(leagueData.toString());
		}
		
		return leagueData;
	}
	
	public ArrayList<String> getLeagueByName(String search)
	{
		return dbHelper.getLeagueIDByLeagueName(search);
	}
	
	public void addCasterIDs(String leagueID, String casterID) 
	{
		dbHelper.addLeagueCasterID(leagueID, casterID);
	}
	
	public void removeCasterIDs(String leagueID, String casterID)
	{
		dbHelper.removeLeagueCasterID(leagueID, casterID);
	}
	
	public void createTrackedStatistic(String leagueID, String statisticName)
	{
		dbHelper.createTrackedStatistic(leagueID, statisticName);
	}
	
	public void deleteTrackedStatistic(String leagueID, String trackedStatisticID)
	{
		dbHelper.deleteTrackedStatistic(leagueID, trackedStatisticID);
	}
	
	public void createLeague(String leagueName, String ownerID, String sport, String description)
	{
		dbHelper.createLeague(leagueName, ownerID, sport, description);
	}
	
	public void deleteLeague(String leagueID)
	{
		dbHelper.deleteLeague(leagueID);
	}
}
