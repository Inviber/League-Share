package team;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

/*
 * 		- teams[]				createTeam(String leagueID, String teamName, String zipcode)
 *			- teamName
 *			- zipcode
 *			- players[]			addPlayer(String leagueID, String teamName, String firstName, String lastName, ...) //each player will need a unique ID because people can have the same names
 *				- firstName
 *				- lastName
 *				- stats[]
 *					- completely unique to the league
 */

public class TeamParser {
	private String leagueID;
	private String teamID;
	private String teamName;
	private String zipcode;
	private ArrayList<String> playerIDs = new ArrayList<String>();
	
	// Database related variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper;
//	private DatabaseHelper dbHelper = new DatabaseHelper(
//			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
//			"LeagueShare");
	private JSONObject teamData;
	
	public TeamParser(String leagueID, String teamID, DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
		this.leagueID = leagueID;
		this.teamID = teamID;
		populateTeamDetails();
	}
	
//	TeamParser(String teamID, DatabaseHelper dbHelper)
//	{
//		this.dbHelper = dbHelper;
//		this.teamID = teamID;
//		populateTeamDetails();
//	}
	
	void populateTeamDetails() 
	{
		getTeamDetails();
		  
		this.teamName = (String) teamData.get("teamName"); 
//		System.out.println(teamName);
		this.zipcode = (String) teamData.get("zipcode"); 
		this.leagueID = (String) teamData.get("leagueID");
		  
		JSONArray players = (JSONArray) teamData.get("players");
		  		  
		for (int i = 0; i < players.size(); i++)
		{
			JSONObject player = (JSONObject) players.get(i);
			String oid = player.get("_id").toString(); 
			String[] id = oid.split("\""); // removing oid from string.
			playerIDs.add(id[3]); // id is stored in element 3.
		}
		  
		//System.out.println(teamName + " " + zipcode  + " " + playerIDs.toString());
		  
	}

	void getTeamDetails() 
	{		
		Document teamDocument = dbHelper.getTeamDocumentByID(leagueID, teamID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
						
			for (int i = 0; i < teamDataArray.size(); i++)
			{
				JSONObject currentTeamData = (JSONObject) teamDataArray.get(i);
				String oid = currentTeamData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(teamID)) // if this is the id searched for...
				{
					teamData = currentTeamData; // save this data.
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	void printTeamData()
	{
		System.out.println(teamData.toString());
	}
	
	public String getLeagueID() 
	{
		return leagueID;
	}

	public String getTeamName() 
	{
		return teamName;
	}

	String getZipcode() 
	{
		return zipcode;
	}

	public ArrayList<String> getPlayerIDs()
	{
		return playerIDs;
	}
	
	public String getTeamID()
	{
		return teamID;
	}
	
	void setTeamID(String teamID)
	{
		this.teamID = teamID;
	}
	
	/*
	String createPlayer(String firstName, String lastName) 
	{
		String playerID = dbHelper.createPlayer(leagueID, teamID, firstName, lastName);
		playerIDs.add(playerID);
		new PlayerParser(leagueID, teamID, playerID);
		return playerID;
	}
	
	void deletePlayer(String playerID) 
	{
		if (playerIDs.contains(playerID))
		{
			dbHelper.deletePlayer(leagueID, teamID, playerID);
			playerIDs.remove(playerID);
		}
		else
		{
			return; // not in database.
		}
	}
	
	*/
	
	void closeDatabase() 
	{
		dbHelper.getClient().close();
	}
}
