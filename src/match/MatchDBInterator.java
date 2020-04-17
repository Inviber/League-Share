package match;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class MatchDBInterator implements MatchDBInteratorInterface {
	
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper;
	
	public MatchDBInterator(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public JSONObject getMatchDetails(String leagueID, String matchID) {
		
		JSONObject matchData = null;
		Document matchDocument = dbHelper.getMatchDocumentByID(leagueID, matchID);

		try 
		{
			Object obj = parser.parse(matchDocument.toJson());
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
//			matchData = new JSONObject();
		}
		
		return matchData;
	}
	
	public void updateMatchData(String leagueID, String matchID, int homeScore, int awayScore, Calendar matchDate) {
		// Make int score values into string and send into DB
		String home = "" + homeScore;
		String away = "" + awayScore;
		dbHelper.updateMatchScore(leagueID, matchID, home, away);
//		System.out.println("home: " + home);
//		System.out.println("away: " + away);
		
		// Make calendar date into string date then send into DB
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		Date newDate = new Date();
		newDate = matchDate.getTime();
		date = sdf.format(newDate);
		
		dbHelper.updateMatchDate(leagueID, matchID, date);
//		System.out.println("date: " + date);
	}
	
}
