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
		populateTeamDetails();
	}
	
	void populateTeamDetails() 
	{
		getPlayerDetails(false);
		  
		this.firstName = (String) playerData.get("firstName"); 
		this.lastName = (String) playerData.get("lastName"); 
		  
		JSONArray matchStatistics = (JSONArray) playerData.get("statistics");
		
		ArrayList<String> statisticValues = new ArrayList<String>();

		for (int i = 0; i < matchStatistics.size(); i++)
		{
			JSONObject stat = (JSONObject) matchStatistics.get(i);
			String statName = stat.get("statName").toString(); 
			statisticNames.add(statName.split("\"")[0]); //  go to the next ", name is stored in element 1.
			
			String statValue = stat.get("statValue").toString(); 
			statisticValues.add(statValue.split("\"")[0]); //  go to the next ", value is stored in element 1.
		}

		
		for (int i = 0; i < statisticValues.size(); i++)
		{
			statistics.put(statisticNames.get(i), statisticValues.get(i)); // append each statisitic to the hash map
		}
		  
		System.out.println(firstName + " " + lastName  + " " + statisticNames.toString() + " " + statistics.toString());
		  
	}

	void getPlayerDetails(boolean print) 
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
	
	void closeDatabase() 
	{
		dbHelper.getClient().close();
	}
}
