package match;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.DatabaseHelper;

public class MatchDBInterator implements MatchDBInteratorInterface {
	
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper;
	
	public MatchDBInterator(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public JSONObject getMatchDetails(String leagueID, String matchID) {
		
		JSONObject matchData = null;
		Document matchDocument = dbHelper.getMatchDocumentByID(leagueID, matchID);

		try 
		{
			Object obj = parser.parse(matchDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray matchDataArray = (JSONArray) leagueData.get("matches");
									
			for (int i = 0; i < matchDataArray.size(); i++)
			{
				JSONObject currentMatchData = (JSONObject) matchDataArray.get(i);
				String oid = currentMatchData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(matchID)) // if this is the id searched for...
				{
					matchData = currentMatchData; // save this data.
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
//			matchData = new JSONObject();
		}
		
		return matchData;
	}

	public void updateMatchData(String leagueID, String matchID, int homeScore, int awayScore, Calendar matchDate) {
		// Make int score values into string and send into DB
		String home = "" + homeScore;
		String away = "" + awayScore;
		dbHelper.updateMatchScore(leagueID, matchID, home, away);
//		System.out.println("home: " + home);
//		System.out.println("away: " + away);
		
		// Make calendar date into string date then send into DB
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		Date newDate = new Date();
		newDate = matchDate.getTime();
		date = sdf.format(newDate);
		
		dbHelper.updateMatchDate(leagueID, matchID, date);
//		System.out.println("date: " + date);
	}

	public ArrayList<ChatMessage> getChat(String leagueID, String matchID)
	{
		ArrayList<ChatMessage> chat = new ArrayList<ChatMessage>();
		Document chatDocument = dbHelper.getChatDocumentByMatchID(leagueID, matchID);

		try 
		{
			Object obj = parser.parse(chatDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			JSONObject matchData = null;

			
			JSONArray matchDataArray = (JSONArray) leagueData.get("matches");
									
			for (int i = 0; i < matchDataArray.size(); i++)
			{
				JSONObject currentMatchData = (JSONObject) matchDataArray.get(i);
				String oid = currentMatchData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(matchID)) // if this is the id searched for...
				{
					matchData = currentMatchData; // save this data.
					break;
				}
			}
			
			JSONArray ChatDataArray = (JSONArray) matchData.get("chat");
			
			ArrayList<String> chatUsernames = new ArrayList<String>();
			ArrayList<String> chatMessages = new ArrayList<String>();
			ArrayList<String> chatTimes = new ArrayList<String>();
			
			// separating chat values to make the chat messages
			for (int i = 0; i < ChatDataArray.size(); i++)
			{
				JSONObject message = (JSONObject) ChatDataArray.get(i);
				String chatUsername = message.get("username").toString(); 
				chatUsernames.add(chatUsername.split("\"")[0]); //  go to the next ", name is stored in element 1.
				
				String chatMessage = message.get("message").toString(); 
				chatMessages.add(chatMessage.split("\"")[0]); //  go to the next ", value is stored in element 1.
				
				String messageTime = message.get("time").toString(); 
				chatTimes.add(messageTime.split("\"")[0]); //  go to the next ", value is stored in element 1.
			}
			
			// adding values to chat arraylist.
			for (int i = 0; i < chatUsernames.size(); i++)
			{
				// create a new ChatMessage and add it to the arraylist.
				ChatMessage chatMessage = new ChatMessage(leagueID, matchID, chatUsernames.get(i), chatMessages.get(i), chatTimes.get(i) );
				chat.add( chatMessage ); 
			}
			
			return chat;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
//			matchData = new JSONObject();
		}
		
		return chat;
	}
	
	public void postMessageToChat(String leagueID, String matchID, String username, String message, String time)
	{
		dbHelper.addMessageToChat(leagueID, matchID, username, message, time);
	}
	
	public void deleteMessageFromChat(String leagueID, String matchID, String chatID)
	{
		dbHelper.deleteMessageFromChat(leagueID, matchID, chatID);
	}
	
	public void createMatch(String leagueID, String homeTeamID, String awayTeamID, String date)
	{
		dbHelper.createMatch(leagueID, homeTeamID, awayTeamID, date);
	}
	
	public void deleteMatch(String leagueID, String matchID)
	{
		dbHelper.deleteMatch(leagueID, matchID);
	}
	
}
