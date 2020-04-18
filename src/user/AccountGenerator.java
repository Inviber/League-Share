package user;

import org.json.simple.JSONObject;

import database.DatabaseHelper;

public class AccountGenerator {

	private AccountDBInterator accountDBInterator;
	private AccountParser accountParser;
	private Account loggedInAccount = null;
	
	public AccountGenerator(DatabaseHelper dbHelper)
	{
		this.accountDBInterator = new AccountDBInterator(dbHelper);
		this.accountParser = new AccountParser(accountDBInterator);
	}
	
	public Account generateAccount(String username, String password)
	{
		String userID = accountDBInterator.getUserIDByUsername(username);
		JSONObject accountData = accountDBInterator.getAccountDetails(userID);
				
		if (password.compareTo((String) accountData.get("password")) == 0)
		{
			accountParser.parseAccount(userID, accountData);
			  
			loggedInAccount = new Account(userID, accountParser.getUsername(),  accountParser.getFirstName(), accountParser.getLastName(),
					 accountParser.getOwnedLeagueIDs(), accountParser.getLeagueCastedIDs(), accountParser.getFollowedLeagueIDs(), 
					 accountParser.getOwnedTeamIDs(), accountParser.getManagedTeamIDs(), accountParser.getManagedTeamLeagueIDs(),
					 accountParser.getFollowedTeamIDs(), accountDBInterator);
		}
		
		
		return loggedInAccount;
	}
	
	public AccountDBInterator getAccountDBInterator()
	{
		return accountDBInterator;
	}
	
	public Account getLoggedInAccount()
	{
		return loggedInAccount;
	}
	
}
