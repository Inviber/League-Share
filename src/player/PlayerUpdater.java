package player;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class PlayerUpdater implements PlayerUpdaterInterface {

	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();
	
	public PlayerUpdater(DatabaseHelper dbHelper)
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

}
