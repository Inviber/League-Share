import java.util.HashMap;

public class Player {
	private HashMap<String, String> stringData;
	private HashMap<String, Integer> intData;
	
	Player(HashMap<String, String> stringData, HashMap<String, Integer> intData)
	{
		this.stringData = stringData;
		this.intData = intData;
	}
	
	String getPlayerStringData(String key)
	{
		return stringData.get(key);
	}
	
	int getPlayerIntData(String key)
	{
		return intData.get(key);
	}
	
	void addPlayerStringData(String key, String value)
	{
		stringData.put(key,  value);
	}
	
	void addPlayerIntData(String key, Integer value)
	{
		intData.put(key,  value);
	}
	
	void removePlayerStringData(String key)
	{
		stringData.remove(key);
	}
	
	void removePlayerIntData(String key)
	{
		intData.remove(key);
	}
	
	void changePlayerStringData(String key, String newValue)
	{
		stringData.replace(key,  newValue);
	}
	
	void changePlayerIntData(String key, Integer newValue)
	{
		intData.replace(key, newValue);
	}
	
}
