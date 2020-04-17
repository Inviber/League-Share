package user;

import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AccountParser implements AccountParserInterface {
	private String userID;
	private String username;
	private String firstName;
	private String lastName;
		
	private ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> leagueCastedIDs = new ArrayList<String>();
	private ArrayList<String> followedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> ownedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamLeagueIDs = new ArrayList<String>();
	private ArrayList<String> followedTeamIDs = new ArrayList<String>();

	
	// Database related variables
	private AccountDBInterator accountDBInterator;
	private JSONObject accountData;
	
	public AccountParser(AccountDBInterator accountDBInterator)
	{
		this.accountDBInterator = accountDBInterator;
	}
	
	public void parseAccount(String userID, JSONObject accountData) 
	{
		this.accountData = accountData;
		
		this.firstName = (String) accountData.get("firstName");
		this.lastName = (String) accountData.get("lastName");
		
		this.ownedLeagueIDs = (ArrayList<String>) accountData.get("ownedLeagueIDs");
		this.leagueCastedIDs = (ArrayList<String>) accountData.get("leagueCastedIDs");
		this.followedLeagueIDs = (ArrayList<String>) accountData.get("followedLeagueIDs");
		this.ownedTeamIDs = (ArrayList<String>) accountData.get("ownedTeamIDs");
		this.managedTeamIDs = (ArrayList<String>) accountData.get("managedTeamIDs");
		
		this.managedTeamLeagueIDs = (ArrayList<String>) accountData.get("managedTeamLeagueIDs");
//			System.out.println(this.managedTeamLeagueIDs.toString());
		
		this.followedTeamIDs = (ArrayList<String>) accountData.get("followedTeamIDs");
	
	}
	
	public void printAccountData()
	{
		System.out.println(accountData.toString());
	}

	public String getUserID() 
	{
		return userID;
	}

	public String getUsername() 
	{
		return username;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public ArrayList<String> getOwnedLeagueIDs() 
	{
		return ownedLeagueIDs;
	}

	public ArrayList<String> getLeagueCastedIDs() 
	{
		return leagueCastedIDs;
	}

	public ArrayList<String> getFollowedLeagueIDs() 
	{
		return followedLeagueIDs;
	}

	public ArrayList<String> getOwnedTeamIDs() 
	{
		return ownedTeamIDs;
	}

	public ArrayList<String> getManagedTeamIDs() 
	{
		return managedTeamIDs;
	}

	public ArrayList<String> getManagedTeamLeagueIDs() 
	{
		return managedTeamLeagueIDs;
	}

	public ArrayList<String> getFollowedTeamIDs() 
	{
		return followedTeamIDs;
	}
	
	
}
