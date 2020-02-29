import java.util.ArrayList;
import java.util.Iterator;

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

	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = new DatabaseHelper(
			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
			"LeagueShare");
	private JSONObject leagueData;

	League(String leagueName)  // will change once we determine how to get the unique identifier for this document.
	{
		this.leagueName = leagueName;
		populateLeagueDetails();
	}

	private void populateLeagueDetails() {
		getLeagueDetails();

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
		  
		  System.out.println(leagueName + " " + sport  + " " + description  + " " +  casterIDs + " " + teamIDs);
	}

	void getLeagueDetails() {
		Document leagueDocument = dbHelper.getDocument("Leagues", dbHelper.getLeagueIDByLeagueName(leagueName));

		try {
			Object obj = parser.parse(leagueDocument.toJson());
			leagueData = (JSONObject) obj;

			System.out.println(leagueData.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String getLeagueID() {
		return leagueID;
	}

	String getLeagueDescription() {
		return description;
	}

	String getOwnerID() {
		return ownerID;
	}

	ArrayList<String> getTeamIDs() {
		return teamIDs;
	}

	ArrayList<String> getCasterIDs() {
		return casterIDs;
	}

	boolean addTeam(String teamID) {
		if (!teamIDs.contains(teamID)) {
			teamIDs.add(teamID);
			if (teamIDs.contains(teamID))
				return true;
		}

		return false;

	}

	boolean removeTeam(String teamID) {
		if (teamIDs.contains(teamID)) {
			teamIDs.remove(teamID);
			if (!teamIDs.contains(teamID))
				return true;
		}

		return false;
	}

	void setDescription(String leagueDescription) {
		this.description = leagueDescription;
	}

	void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	void addCasterIDs(String casterID) {
		if (this.casterIDs.contains(casterID)) {
			return; // already within array.
		} else {
			this.casterIDs.add(casterID);
			// dbHelper.addCasterID(this.leagueID, casterID);
		}

	}

	void removeCasterIDs(String casterID) {
		if (this.casterIDs.contains(casterID)) {
			this.casterIDs.remove(casterID);
			// dbHelper.removeCasterID(this.leagueID, casterID);
		} else {
			return; // not within array.
		}
	}

	void closeDatabase() {
		dbHelper.getClient().close();
	}

}
