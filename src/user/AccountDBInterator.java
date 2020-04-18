package user;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class AccountDBInterator implements AccountDBInteratorInterface {
	
	private DatabaseHelper dbHelper;
	private JSONParser parser = new JSONParser();
	
	public AccountDBInterator(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public String getUserIDByUsername(String userName)
	{
		return dbHelper.getUserIDByUsername(userName);
	}
	
	public JSONObject getAccountDetails(String userID)
	{
		JSONObject accountData = null;
		Document accountDocument = dbHelper.getDocument("Users", userID); 	
				
		try
		{
			Object obj = parser.parse(accountDocument.toJson());
			accountData = (JSONObject) obj;
		}
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}
		
		return accountData;
	}
	
	public void createUser(String username, String password)
	{
		dbHelper.createUser(username, password, "firstName", "lastName");
	}
	
	public boolean existingAccount(String username)
	{
		if (!dbHelper.getUserIDByUsername(username).equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addOwnedLeagueID(String userID, String leagueID)
	{
		dbHelper.addOwnedLeagueID(userID, leagueID);
	}
	
	public void addLeagueCastedID(String userID, String leagueID)
	{
		dbHelper.addLeagueCastedID(userID, leagueID);
	}
	
	public void addFollowedLeagueID(String userID, String leagueID)
	{
		dbHelper.addFollowedLeagueID(userID, leagueID);
	}
	
	public void removeOwnedLeagueID(String userID, String leagueID)
	{
		dbHelper.removeOwnedLeagueID(userID, leagueID);
	}
	
	public void removeLeagueCastedID(String userID, String leagueID)
	{
		dbHelper.removeLeagueCastedID(userID, leagueID);
	}
	
	public void removeFollowedLeagueID(String userID, String leagueID)
	{
		dbHelper.removeFollowedLeagueID(userID, leagueID);
	}
	

	public void addOwnedTeamID(String userID, String teamID)
	{
		dbHelper.addOwnedTeamID(userID, teamID);
	}
	
	public void addManagedTeamID(String userID, String teamID)
	{
		dbHelper.addManagedTeamID(userID, teamID);
	}
	
	public void addFollowedTeamID(String userID, String teamID)
	{
		dbHelper.addFollowedTeamID(userID, teamID);
	}
	
	public void removeOwnedTeamID(String userID, String teamID)
	{
		dbHelper.removeOwnedTeamID(userID, teamID);
	}
	
	public void removeManagedTeamID(String userID, String teamID)
	{
		dbHelper.removeManagedTeamID(userID, teamID);
	}
	
	public void removeFollowedTeamID(String userID, String teamID)
	{
		dbHelper.removeFollowedTeamID(userID, teamID);
	}
}
