package match;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class MatchParser {
	private String leagueID;
	private String matchID;

	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper;
//	private DatabaseHelper dbHelper = 
//			new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
	private JSONObject matchData;
	
	public MatchParser(String leagueID, String matchID, DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
		this.leagueID = leagueID;
		this.matchID = matchID;

		getMatchDetails();
	}
	
	public void getMatchDetails() 
	{
		Document teamDocument = dbHelper.getMatchDocumentByID(leagueID, matchID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray matchDataArray = (JSONArray) leagueData.get("matches");
									
			for (int i = 0; i < matchDataArray.size(); i++)
			{
				JSONObject currentMatchData = (JSONObject) matchDataArray.get(i);
				String oid = currentMatchData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(matchID)) // if this is the id searched for...
				{
					matchData = currentMatchData; // save this data.
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void printMatchData()
	{
		System.out.println(matchData.toString());
	}

	public String getMatchID() 
	{
		return matchID;
	}

	public String getHomeTeamID() 
	{
		return (String) matchData.get("homeTeamID");
	}

	public String getAwayTeamID() 
	{
		return (String) matchData.get("awayTeamID");
	}

	public Calendar getDate() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date matchDate = new Date();
		Calendar matchCalendar = Calendar.getInstance();

		try {
			matchDate = sdf.parse( (String) matchData.get("date") );
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		matchCalendar.setTime(matchDate);
		return matchCalendar;
	}

	public int getHomeScore() 
	{
		try {
			return Integer.parseInt( (String) matchData.get("homeScore") );
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return -1;
		}
	}

	public int getAwayScore() 
	{
		try {
			return Integer.parseInt( (String) matchData.get("awayScore") );
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return -1;
		}
	}
}