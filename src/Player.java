import java.util.ArrayList;

public class Player {
	private String playerID;
	private String firstName;
	private String lastName;
	private ArrayList<String> statistics = new ArrayList<String>();
	
	Player(String playerID)
	{
		this.playerID = playerID;
	}
}
