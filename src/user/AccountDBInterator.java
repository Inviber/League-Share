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
}
