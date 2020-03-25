package league;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class LeagueUpdater implements LeagueUpdaterInterface  {
	
	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();

	
	public LeagueUpdater(DatabaseHelper dbHelper)
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
	
	public void addCasterIDs(String leagueID, String casterID) 
	{
		dbHelper.addLeagueCasterID(leagueID, casterID);
	}
	
	public void removeCasterIDs(String leagueID, String casterID)
	{
		dbHelper.removeLeagueCasterID(leagueID, casterID);
	}
}
