package user;

import org.json.simple.JSONObject;

public interface AccountDBInteratorInterface {
	public String getUserIDByUsername(String userName);
	public JSONObject getAccountDetails(String userID);
	public boolean existingAccount(String username);
	public void createUser(String username, String password);
	public void addOwnedLeagueID(String userID, String leagueID);
	public void addLeagueCastedID(String userID, String leagueID);
	public void addFollowedLeagueID(String userID, String leagueID);
	public void removeOwnedLeagueID(String userID, String leagueID);
	public void removeLeagueCastedID(String userID, String leagueID);
	public void removeFollowedLeagueID(String userID, String leagueID);
}
