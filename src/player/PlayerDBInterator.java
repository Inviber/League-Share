package player;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class PlayerDBInterator implements PlayerDBInteratorInterface {

	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();
	
	public PlayerDBInterator(DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
	}
	
	public JSONObject getPlayerDetails(String leagueID, String teamID, String playerID, boolean print) 
	{
		JSONObject playerData = null;
		Document teamDocument = dbHelper.getTeamDocumentByID(leagueID, teamID);
		
		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
						
			JSONObject teamData = (JSONObject) teamDataArray.get(0);
			
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
			
			
			JSONArray playerDataArray = (JSONArray) teamData.get("players");

			for (int i = 0; i < playerDataArray.size(); i++)
			{
				JSONObject currentPlayerData = (JSONObject) playerDataArray.get(i);
				String oid = currentPlayerData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(playerID)) // if this is the id searched for...
				{
					playerData = currentPlayerData; // save this data.
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (print)
		{
			System.out.println(playerData.toString());
		}
		
		return playerData;
	}
	
	public void updatePlayerStatistics(String leagueID, String teamID, String playerID, String statName, int newStatValue)
	{
		dbHelper.updatePlayerStatisticByName(leagueID, teamID, playerID, statName, newStatValue);
	}
	
	public void createPlayer(String leagueID, String teamID, String firstName, String lastName)
	{
		dbHelper.createPlayer(leagueID, teamID, firstName, lastName);
	}
	
	public void updatePlayer(String leagueID, String teamID, String playerID, String firstName, String lastName)
	{
		dbHelper.updatePlayer(leagueID, teamID, playerID, firstName, lastName);
	}

	public void deletePlayer(String leagueID, String teamID, String playerID)
	{
		dbHelper.deletePlayer(leagueID, teamID, playerID);
	}
}
