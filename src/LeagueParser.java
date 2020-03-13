import java.util.ArrayList;
import org.bson.Document;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class LeagueParser {
	private String leagueID;
	private String leagueName;
	private String ownerID;
	private String sport;
	private String description;
	private ArrayList<String> casterIDs = new ArrayList<String>();
	private ArrayList<String> teamIDs = new ArrayList<String>();
	private ArrayList<String> matchIDs = new ArrayList<String>();
	

	// Database related variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = new DatabaseHelper(
			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
			"LeagueShare");
	private JSONObject leagueData;

	LeagueParser(String leagueID)  // will change once we determine how to get the unique identifier for this document.
	{
		this.leagueID = leagueID;
		populateLeagueDetails();
	}

	private void populateLeagueDetails() 
	{
		getLeagueDetails(false);

		  this.leagueName = (String) leagueData.get("leagueName"); 
		  this.sport = (String) leagueData.get("sport"); 
		  this.description = (String) leagueData.get("description");
		  
		  this.casterIDs = (ArrayList<String>) leagueData.get("casterIDs");
		  
		  JSONArray teams = (JSONArray) leagueData.get("teams");
		  
		  for (int i = 0; i < teams.size(); i++)
		  {
			  JSONObject team = (JSONObject) teams.get(i);
			  String oid = team.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  teamIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  JSONArray matches = (JSONArray) leagueData.get("matches");
		  
		  for (int i = 0; i < matches.size(); i++)
		  {
			  JSONObject match = (JSONObject) matches.get(i);
			  String oid = match.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  matchIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  //System.out.println(leagueName + " " + sport  + " " + description  + " " +  casterIDs + " " + teamIDs + " " + matchIDs);
	}

	void getLeagueDetails(boolean print) 
	{
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
	}

	String getLeagueID() 
	{
		return leagueID;
	}
	
	String getLeagueName() 
	{
		return leagueName;
	}

	String getSport() 
	{
		return sport;
	}
	
	String getOwnerID() 
	{
		return ownerID;
	}
	
	String getLeagueDescription() 
	{
		return description;
	}

	ArrayList<String> getTeamIDs() 
	{
		return teamIDs;
	}
	
	ArrayList<String> getMatchIDs() 
	{
		return matchIDs;
	}

	ArrayList<String> getCasterIDs() 
	{
		return casterIDs;
	}

	String createTeam(String teamName, String zipcode) 
	{
		if (dbHelper.getTeamIDByTeamName(leagueID, teamName) == "")
		{
			String teamID = dbHelper.createTeam(leagueID, teamName, zipcode);
			teamIDs.add(teamID);
			new TeamParser(leagueID, teamID);
			return teamID;
		}
		else return "";
	}
	
	void deleteTeam(String teamName) 
	{
		String teamID = dbHelper.getTeamIDByTeamName(leagueID, teamName);
		if (teamID == "")
		{
			return; // not in database.
		}
		dbHelper.deleteTeam(leagueID, teamID);
		teamIDs.remove(teamID);
	}

	void removeTeam(String teamID) 
	{
		if (teamIDs.contains(teamID)) 
		{
			teamIDs.remove(teamID);
		}
	}
	
	String createMatch(String homeTeamID, String awayTeamID, String date) 
	{
		String matchID = dbHelper.createMatch(leagueID, homeTeamID, awayTeamID, date);
		matchIDs.add(matchID);
		new MatchParser(leagueID, matchID);
		return matchID;
	}
	
	void deleteMatch(String matchID) 
	{
		if (matchIDs.contains(matchID))
		{
			dbHelper.deleteMatch(leagueID, matchID);
			matchIDs.remove(matchID);
		}
		else
		{
			return; // not in database.
		}
	}

	void setDescription(String description) 
	{
		this.description = description;
	}

	void setOwnerID(String ownerID) 
	{
		this.ownerID = ownerID;
	}

	void addCasterIDs(String casterID) 
	{
		if (!this.casterIDs.contains(casterID)) 
		{
			this.casterIDs.add(casterID);
			dbHelper.addLeagueCasterID(this.leagueID, casterID);
		}
	}

	void removeCasterIDs(String casterID) 
	{
		if (this.casterIDs.contains(casterID)) 
		{
			this.casterIDs.remove(casterID);
			dbHelper.removeLeagueCasterID(this.leagueID, casterID);
		} 
	}

	void closeDatabase() 
	{
		dbHelper.getClient().close();
	}

}
