package league;

import org.json.simple.JSONObject;

public interface LeagueUpdaterInterface {
	
	public JSONObject getLeagueDetails(String leagueID, boolean print);
	public void addCasterIDs(String leagueID, String casterID);
	public void removeCasterIDs(String leagueID, String casterID);
}
