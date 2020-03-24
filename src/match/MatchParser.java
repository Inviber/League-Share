package match;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class MatchParser {
	private String leagueID;
	private String matchID;
	private String homeTeamID;
	private String awayTeamID;
	private String homeScore;
	private String awayScore;
	private String date;
	
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
		
		populateMatchDetails();
	}
	
	private void populateMatchDetails()
	{
		getMatchDetails();
		
		this.homeTeamID = (String) matchData.get("homeTeamID");
		this.awayTeamID = (String) matchData.get("awayTeamID");
		this.homeScore = (String) matchData.get("homeScore");
		this.awayScore = (String) matchData.get("awayScore");
		this.date = (String) matchData.get("date");

	}
	
	void getMatchDetails() 
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
	
	void printMatchData()
	{
		System.out.println(matchData.toString());
	}

	String getMatchID() 
	{
		return matchID;
	}

	public String getHomeTeamID() 
	{
		return homeTeamID;
	}

	public String getAwayTeamID() 
	{
		return awayTeamID;
	}

	public String getDate() 
	{
		return date;
	}

	public String getHomeScore() 
	{
		return homeScore;
	}

	void setHomeScore(String homeScore) 
	{
		this.homeScore = homeScore;
	}

	public String getAwayScore() 
	{
		return awayScore;
	}

	void setAwayScore(String awayScore) 
	{
		this.awayScore = awayScore;
	}
	
	
	/*
	@Test
	void createMatchInTeam() {
		String testAddMatchID = team.createMatch("homeTeamAdd", "awayTeam", "date", "finalScore");
				
		assertTrue(team.getMatchIDs().contains(testAddMatchID), "testAddMatchID is pressent in team array");
	
		team.deleteMatch(testAddMatchID);
	}
	
	@Test
	void deleteMatchFromLeague()
	{
		String testRemoveMatchID = team.createMatch("homeTeamRemove", "awayTeam", "date", "finalScore");
		
		assertTrue(team.getMatchIDs().contains(testRemoveMatchID), "testRemoveMatchID is pressent in team array");

		team.deleteMatch(testRemoveMatchID);

		assertFalse(team.getMatchIDs().contains(testRemoveMatchID), "testRemoveMatchID is no longer present in team array");
	}
	 */
	
	
}
