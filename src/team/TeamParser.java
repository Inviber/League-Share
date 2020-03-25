package team;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	private TeamDBInterator teamUpdater;
	private JSONObject teamData;
	
	public TeamParser(TeamDBInterator teamUpdater)
	{
		this.teamUpdater = teamUpdater;
	}
	
	public void parseTeam(String leagueID, String teamID) 
	{
		teamUpdater.getTeamDetails(leagueID, teamID);
		  
		this.teamName = (String) teamData.get("teamName"); 

		this.zipcode = (String) teamData.get("zipcode"); 
		this.leagueID = (String) teamData.get("leagueID");
		  
		JSONArray players = (JSONArray) teamData.get("players");
		  		  
		  // have to do this to get the ID from the team because player is an object, not just a array of strings.
		for (int i = 0; i < players.size(); i++)
		{
			JSONObject player = (JSONObject) players.get(i);
			String oid = player.get("_id").toString(); 
			String[] id = oid.split("\""); // removing oid from string.
			playerIDs.add(id[3]); // id is stored in element 3.
		}
		  
		//System.out.println(teamName + " " + zipcode  + " " + playerIDs.toString());
		  
	}
	
	public void printTeamData()
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

	public String getZipcode() 
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
	
	public void setTeamID(String teamID)
	{
		this.teamID = teamID;
	}
}
