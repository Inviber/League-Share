package user;

import database.DatabaseHelper;

public class AccountDBInterator implements AccountDBInteratorInterface {
	
	private DatabaseHelper dbHelper;
	
	public AccountDBInterator(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
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

}
