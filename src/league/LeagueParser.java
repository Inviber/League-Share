package league;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LeagueParser implements LeagueParserInterface {
	
	private String leagueID;
	private String leagueName;
	private String ownerID;
	private String sport;
	private String description;
	private ArrayList<String> casterIDs = new ArrayList<String>();
	private ArrayList<String> teamIDs = new ArrayList<String>();
	private ArrayList<String> matchIDs = new ArrayList<String>();
	private ArrayList<String> trackedStatisticsIDs = new ArrayList<String>();

	
	// Database related variables
	private LeagueDBInterator leagueDBInterator;
	private JSONObject leagueData;

	public LeagueParser(LeagueDBInterator leagueDBInterator)  // will change once we determine how to get the unique identifier for this document.
	{
		this.leagueDBInterator = leagueDBInterator;
	}

	public void parseLeague(String leagueID) 
	{
		leagueData = leagueDBInterator.getLeagueDetails(leagueID, false);
		
		  leagueName = (String) leagueData.get("leagueName"); 
		  sport = (String) leagueData.get("sport"); 
		  ownerID = (String) leagueData.get("ownerID"); 
		  description = (String) leagueData.get("description");
		  
		  casterIDs = (ArrayList<String>) leagueData.get("casterIDs");
		  
		  JSONArray teams = (JSONArray) leagueData.get("teams");
		  
		  this.teamIDs = new ArrayList<String>();	// needs to be cleared out before filling again.
		  
		  // have to do this to get the ID from the team because team is an object, not just a array of strings.
		  for (int i = 0; i < teams.size(); i++)
		  {
			  JSONObject team = (JSONObject) teams.get(i);
			  String oid = team.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  teamIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  JSONArray matches = (JSONArray) leagueData.get("matches");
		  
		  this.matchIDs = new ArrayList<String>(); 	// needs to be cleared out before filling again.
		  
		  // have to do this to get the ID from the match because team is an object, not just a array of strings.
		  for (int i = 0; i < matches.size(); i++)
		  {
			  JSONObject match = (JSONObject) matches.get(i);
			  String oid = match.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  matchIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  JSONArray trackedStatistics = (JSONArray) leagueData.get("trackedStatistics");
		  
		  this.trackedStatisticsIDs = new ArrayList<String>(); 	// needs to be cleared out before filling again.
		  
		  // have to do this to get the ID from the match because team is an object, not just a array of strings.
		  for (int i = 0; i < trackedStatistics.size(); i++)
		  {
			  JSONObject trackedStatistic = (JSONObject) trackedStatistics.get(i);
			  String oid = trackedStatistic.get("_id").toString(); 
			  String[] id = oid.split("\""); // removing oid from string.
			  trackedStatisticsIDs.add(id[3]); // id is stored in element 3.
		  }
		  
		  //System.out.println(leagueName + " " + sport  + " " + description  + " " +  casterIDs + " " + teamIDs + " " + matchIDs);
	}

	public String getLeagueID() {
		return leagueID;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public String getSport() {
		return sport;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getCasterIDs() {
		return casterIDs;
	}

	public ArrayList<String> getTeamIDs() {
		return teamIDs;
	}

	public ArrayList<String> getMatchIDs() {
		return matchIDs;
	}
	
	public ArrayList<String> getTrackedStatisticsIDs() {
		return matchIDs;
	}
}
