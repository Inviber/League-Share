package match;

public class ChatMessage {
	
	private String leagueID;
	private String matchID;
	private String username;
	private String message;
	private String time;
	
	public ChatMessage(String leagueID, String matchID, String username, String message, String time) {
		this.leagueID = leagueID;
		this.matchID = matchID;
		this.username = username;
		this.message = message;
		this.time = time;
	}
	
	public String getLeagueID() 
	{
		return leagueID;
	}
	
	public String getMatchID()
	{
		return matchID;
	}

	public String getUsername() 
	{
		return username;
	}

	public String getMessage() 
	{
		return message;
	}

	public String getTime()
	{
		return time;
	}
	
	
}