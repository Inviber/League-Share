package match;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface MatchDBInteratorInterface {
	
	public JSONObject getMatchDetails(String leagueID, String matchID);
	public ArrayList<ChatMessage> getChat(String leagueID, String matchID);
	public void postMessageToChat(String leagueID, String matchID, String username, String message, String time);
	public void deleteMessageFromChat(String leagueID, String matchID, String chatID);
	public void createMatch(String leagueID, String homeTeamID, String awayTeamID, String date);
	public void updateMatch(String leagueID, String matchID, String homeTeamID, String awayTeamID, String date, String homeScore, String awayScore);
	public void deleteMatch(String leagueID, String matchID);
}
