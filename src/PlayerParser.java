import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PlayerParser {
	private String leagueID;
	private String teamID;
	private String playerID;
	private String firstName;
	private String lastName;
	private ArrayList<String> statisticNames = new ArrayList<String>();
	private HashMap<String, String> statistics = new HashMap<String, String>();
	
	// Database related variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = new DatabaseHelper(
			"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
			"LeagueShare");
	private JSONObject playerData;
	
	PlayerParser(String leagueID, String teamID, String playerID)
	{
		this.leagueID = leagueID;
		this.teamID = teamID;
		this.playerID = playerID;
	}
	
	void populateTeamDetails() 
	{
		getTeamDetails(false);
		  
		this.firstName = (String) playerData.get("firstName"); 
		this.lastName = (String) playerData.get("lastName"); 
		  
		JSONArray matchStatNames = (JSONArray) playerData.get("statisticNames");
		  		  
		for (int i = 0; i < matchStatNames.size(); i++)
		{
			JSONObject stat = (JSONObject) matchStatNames.get(i);
			String oid = stat.get("_id").toString(); 
			String[] id = oid.split("\""); // removing oid from string.
			statisticNames.add(id[3]); // id is stored in element 3.
		}
		
		JSONArray matchStatValue = (JSONArray) playerData.get("statValue");
		  
		ArrayList<String> statisticValues = new ArrayList<String>();
		
		for (int i = 0; i < matchStatValue.size(); i++)
		{
			JSONObject stat = (JSONObject) matchStatValue.get(i);
			String oid = stat.get("_id").toString(); 
			String[] id = oid.split("\""); // removing oid from string.
			statisticValues.add(id[3]); // id is stored in element 3.
		}
		
		for (int i = 0; i < statisticValues.size(); i++)
		{
			statistics.put(statisticNames.get(i), statisticValues.get(i)); // append each statisitic to the hash map
		}
		  
		//System.out.println(teamName + " " + zipcode  + " " + playerIDs.toString());
		  
	}

	void getTeamDetails(boolean print) 
	{
		Document teamDocument = dbHelper.getTeamDocumentByID(leagueID, teamID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
						
			JSONObject teamData = (JSONObject) teamDataArray.get(0);
			
			JSONArray playerDataArray = (JSONArray) teamData.get("players");
			
			playerData = (JSONObject) playerDataArray.get(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (print)
		{
			System.out.println(playerData.toString());
		}
	}

	String getPlayerID() 
	{
		return playerID;
	}

	String getFirstName() 
	{
		return firstName;
	}

	String getLastName() 
	{
		return lastName;
	}

	ArrayList<String> getStatisticNames() 
	{
		return statisticNames;
	}

	HashMap<String, String> getStatistics() 
	{
		return statistics;
	}
	
	String getStatstic(String statisticName)
	{
		return statistics.get(statisticName);
	}
}
