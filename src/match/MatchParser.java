package match;
import java.util.Calendar;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class MatchParser implements MatchParserInterface{
	private String leagueID;
	private String matchID;
	private JSONObject matchData;
	private MatchDBInterator matchDBInterator;
	
	public MatchParser(String leagueID, String matchID, MatchDBInterator matchDBInterator)
	{
		this.leagueID = leagueID;
		this.matchID = matchID;
		this.matchDBInterator = matchDBInterator;

		getMatchDetails();
	}
	
	public void getMatchDetails() 
	{
		matchData = matchDBInterator.getMatchDetails(leagueID, matchID);
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
		Calendar matchCalendar = Calendar.getInstance();

		int month, day, year;
		try {
			String[] intValuesOfDate = ( (String) matchData.get("date") ).split("/");
			month = Integer.parseInt( intValuesOfDate[0] );
			day = Integer.parseInt( intValuesOfDate[1] );
			year = Integer.parseInt( intValuesOfDate[2] );
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			month = 0;
			day = 0;
			year = 0;
		}

		matchCalendar.clear();
		matchCalendar.set(year + 2000, month - 1, day);

		return matchCalendar;
	}

	public int getHomeScore() 
	{
		try {
			return Integer.parseInt( ((String) matchData.get("homeScore")) );
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