package league;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface LeagueDBInteratorInterface {
	
	public JSONObject getLeagueDetails(String leagueID, boolean print);
	public void addCasterIDs(String leagueID, String casterID);
	public void removeCasterIDs(String leagueID, String casterID);
	public ArrayList<String> getLeagueByName(String search);
	public void createTrackedStatistic(String leagueID, String statisticName);
	public void deleteTrackedStatistic(String leagueID, String trackedStatisticID);
	public void createLeague(String leagueName, String ownerID, String sport, String description);
	public void updateLeague(String leagueID, String leagueName, String ownerID, String sport, String description);
	public void deleteLeague(String leagueID);

}
