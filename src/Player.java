import java.util.ArrayList;

public class Player {
	private String playerID;
	private String firstName;
	private String lastName;
	private ArrayList<String> statistics = new ArrayList<String>();
	
	Player(String playerID, String firstName, String lastName)
	{
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	String getPlayerID() 
	{
		return playerID;
	}

	String getFirstName() 
	{
		return firstName;
	}

	String getLastName() 
	{
		return lastName;
	}

	ArrayList<String> getStatistics() 
	{
		return statistics;
	}
	
	
}
