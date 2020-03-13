import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MatchParser {
	private String leagueID;
	private String matchID;
	private String homeTeamID;
	private String awayTeamID;
	private String homeScore;
	private String awayScore;
	private String date;
	
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = 
			new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
	private JSONObject matchData;
	
	MatchParser(String leagueID, String matchID)
	{
		this.leagueID = leagueID;
		this.matchID = matchID;
		
		populateMatchDetails();
	}
	
	private void populateMatchDetails()
	{
		getMatchDetails(false);
		
		this.homeTeamID = (String) matchData.get("homeTeamID");
		this.awayTeamID = (String) matchData.get("awayTeamID");
		this.homeScore = (String) matchData.get("homeScore");
		this.awayScore = (String) matchData.get("awayScore");
		this.date = (String) matchData.get("date");

	}
	
	void getMatchDetails(boolean print) 
	{
		Document teamDocument = dbHelper.getMatchDocumentByID(leagueID, matchID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray matchDataArray = (JSONArray) leagueData.get("matches");
						
			matchData = (JSONObject) matchDataArray.get(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (print)
		{
			System.out.println(matchData.toString());
		}
	}

	String getMatchID() 
	{
		return matchID;
	}

	String getHomeTeamID() 
	{
		return homeTeamID;
	}

	String getAwayTeamID() 
	{
		return awayTeamID;
	}

	String getDate() 
	{
		return date;
	}

	String getHomeScore() 
	{
		return homeScore;
	}

	void setHomeScore(String homeScore) 
	{
		this.homeScore = homeScore;
	}

	String getAwayScore() 
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
