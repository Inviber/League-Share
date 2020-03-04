import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * 		- teams[]				createTeam(String leagueID, String teamName, String zipcode)
 *			- teamName
 *			- zipcode
 *			- players[]			addPlayer(String leagueID, String teamName, String firstName, String lastName, ...) //each player will need a unique ID because people can have the same names
 *				- firstName
 *				- lastName
 *				- stats[]
 *					- completely unique to the league
 *			- matches[]
 *				- homeTeam
 *				- awayTeam
 *				- date
 *				- finalScore
 */

public class Team {
	private String leagueID;
	private String teamID;
	private String teamName;
	private String zipcode;
	private ArrayList<String> playerIDs = new ArrayList<String>();
	private ArrayList<String> matchIDs = new ArrayList<String>();
	
	// Database related variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = new DatabaseHelper(
			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
			"LeagueShare");
	private JSONObject teamData;
	
	Team(String leagueID, String teamName)
	{
		this.leagueID = leagueID;
		this.teamName = teamName;
		populateTeamDetails();
	}
	
	void populateTeamDetails() 
	{
		getTeamDetails();
		  
		  this.zipcode =(String) teamData.get("zipcode"); 
		  
		  JSONArray players = (JSONArray) teamData.get("players");
		  		  
		  for (int i = 0; i < players.size(); i++)
		  {
			  JSONObject player = (JSONObject) players.get(i);
			  String oid = player.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  playerIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  JSONArray matches = (JSONArray) teamData.get("matches");
		  
		  for (int i = 0; i < matches.size(); i++)
		  {
			  JSONObject match = (JSONObject) matches.get(i);
			  String oid = match.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  matchIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  System.out.println(teamName + " " + zipcode  + " " + playerIDs.toString()  + " " +  matchIDs.toString());
		  
	}

	void getTeamDetails() 
	{
		this.teamID = dbHelper.getTeamIDByTeamName(leagueID, teamName);
		Document teamDocument = dbHelper.getTeamDocumentByID(leagueID, teamID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
			
			teamData = (JSONObject) teamDataArray.get(0);
			
			System.out.println(teamData.toString());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	String getLeagueID() 
	{
		return leagueID;
	}

	String getTeamName() 
	{
		return teamName;
	}

	String getZipcode() 
	{
		return zipcode;
	}

	ArrayList<String> getMatchIDs() 
	{
		return matchIDs;
	}

	ArrayList<String> getPlayerIDs()
	{
		return playerIDs;
	}
	
	String getTeamID()
	{
		return teamID;
	}
	
	void setTeamID(String teamID)
	{
		this.teamID = teamID;
	}
	
	void closeDatabase() 
	{
		dbHelper.getClient().close();
	}
}
