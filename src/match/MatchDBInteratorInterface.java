package match;

import org.json.simple.JSONObject;

public interface MatchDBInteratorInterface {
	
	public JSONObject getMatchDetails(String leagueID, String matchID);
	
}
