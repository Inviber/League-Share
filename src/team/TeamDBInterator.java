package team;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class TeamDBInterator implements TeamDBInteratorInterface {

	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();
	
	public TeamDBInterator(DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
	}
	
	public JSONObject getTeamDetails(String leagueID, String teamID) 
	{		
		JSONObject teamData = null;
		Document teamDocument = dbHelper.getTeamDocumentByID(leagueID, teamID);

		try 
		{
			Object obj = parser.parse(teamDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
						
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
			
			return teamData;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void createTeam(String leagueID, String teamName, String zipcode)
	{
		dbHelper.createTeam(leagueID, teamName, zipcode);
	}
	
	public void updateTeam(String leagueID, String teamID, String teamName, String zipcode)
	{
		dbHelper.updateTeam(leagueID, teamID, teamName, zipcode);
	}
	
	public void deleteTeam(String leagueID, String teamID)
	{
		dbHelper.deleteTeam(leagueID, teamID);
	}
}
