import java.util.ArrayList;
import org.bson.Document;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class League {
	private String leagueID;
	private String leagueName;
	private String ownerID;
	private String sport;
	private String description;
	private ArrayList<String> casterIDs = new ArrayList<String>();
	private ArrayList<String> teamIDs = new ArrayList<String>();

	// Database related variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = new DatabaseHelper(
			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
			"LeagueShare");
	private JSONObject leagueData;

	League(String leagueName)  // will change once we determine how to get the unique identifier for this document.
	{
		this.leagueName = leagueName;
		leagueID = dbHelper.getLeagueIDByLeagueName(leagueName);
		populateLeagueDetails();
	}

	private void populateLeagueDetails() 
	{
		getLeagueDetails(false);

		  this.leagueName = (String) leagueData.get("leagueName"); 
		  this.sport =(String) leagueData.get("sport"); 
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
		  
		  //System.out.println(leagueName + " " + sport  + " " + description  + " " +  casterIDs + " " + teamIDs);
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

	String getLeagueDescription() 
	{
		return description;
	}

	String getOwnerID() 
	{
		return ownerID;
	}

	ArrayList<String> getTeamIDs() 
	{
		return teamIDs;
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
			addTeam(teamID);
			new Team(leagueID, teamID);
			return teamID;
		}
		else return "";
	}
	
	void addTeam(String teamID) 
	{
		if (!teamIDs.contains(teamID)) 
		{
			teamIDs.add(teamID);
		}
	}
	
	void deleteTeam(String teamName) 
	{
		String teamID = dbHelper.getTeamIDByTeamName(leagueID, teamName);
		if (teamID == "")
		{
			return; // not in database.
		}
		dbHelper.deleteTeam(leagueID, teamID);
		removeTeam(teamID);
	}

	void removeTeam(String teamID) 
	{
		if (teamIDs.contains(teamID)) 
		{
			teamIDs.remove(teamID);
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
