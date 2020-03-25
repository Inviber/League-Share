package player;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlayerParser {
	private String playerID;
	private String firstName;
	private String lastName;
	private ArrayList<String> statisticNames = new ArrayList<String>();
	private HashMap<String, String> statistics = new HashMap<String, String>();
	
	// Database related variables
	private PlayerDBInterator playerUpdater;
	private JSONObject playerData;
	
	public PlayerParser(PlayerDBInterator playerUpdater)
	{
		this.playerUpdater = playerUpdater;
	}
	
	public void parsePlayer(String leagueID, String teamID, String playerID) 
	{
		playerData = playerUpdater.getPlayerDetails(leagueID, teamID, playerID, false);
		  
		this.firstName = (String) playerData.get("firstName"); 
		this.lastName = (String) playerData.get("lastName"); 
		  
		JSONArray matchStatistics = (JSONArray) playerData.get("statistics");
		
		ArrayList<String> statisticValues = new ArrayList<String>();

		// separating stat name from value for hashmap
		for (int i = 0; i < matchStatistics.size(); i++)
		{
			JSONObject stat = (JSONObject) matchStatistics.get(i);
			String statName = stat.get("statName").toString(); 
			statisticNames.add(statName.split("\"")[0]); //  go to the next ", name is stored in element 1.
			
			String statValue = stat.get("statValue").toString(); 
			statisticValues.add(statValue.split("\"")[0]); //  go to the next ", value is stored in element 1.
		}
		
		// adding values to hashmap.
		for (int i = 0; i < statisticValues.size(); i++)
		{
			statistics.put(statisticNames.get(i), statisticValues.get(i)); // append each statisitic to the hash map
		}
		
		//System.out.println(firstName + " " + lastName  + " " + statisticNames.toString() + " " + statistics.toString());
	}

	
	String getPlayerID() 
	{
		return playerID;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getLastName() 
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
