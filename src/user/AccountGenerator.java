package user;

import org.json.simple.JSONObject;

import database.DatabaseHelper;

public class AccountGenerator {

	private AccountDBInterator accountDBInterator;
	private AccountParser accountParser;
	
	public AccountGenerator(DatabaseHelper dbHelper)
	{
		this.accountDBInterator = new AccountDBInterator(dbHelper);
		this.accountParser = new AccountParser(accountDBInterator);
	}
	
	public Account generateAccount(String username, String password)
	{
		String userID = accountDBInterator.getUserIDByUsername(username);
		JSONObject accountData = accountDBInterator.getAccountDetails(userID);
		
		Account account = null;
		
		if (password.compareTo((String) accountData.get("password")) == 0)
		{
			accountParser.parseAccount(userID, accountData);
			  
			account = new Account(userID, accountParser.getUsername(),  accountParser.getFirstName(), accountParser.getLastName(),
					 accountParser.getOwnedLeagueIDs(), accountParser.getLeagueCastedIDs(), accountParser.getFollowedLeagueIDs(), 
					 accountParser.getOwnedTeamIDs(), accountParser.getManagedTeamIDs(), accountParser.getManagedTeamLeagueIDs(),
					 accountParser.getFollowedTeamIDs(), accountDBInterator);
		}
		
		
		return account;
	}
	
	public AccountDBInterator getAccountDBInterator()
	{
		return accountDBInterator;
	}
	
}
