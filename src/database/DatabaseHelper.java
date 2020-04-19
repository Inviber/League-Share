package database;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class DatabaseHelper {
	
	private MongoClientURI uri; // (URI) is a string of characters that unambiguously identifies a particular resource.
	private MongoClient mongoClient;
	private MongoDatabase database;
	
	private static final String USERS = "Users";
	private static final String LEAGUES = "Leagues";
	
	public DatabaseHelper(String uri, String databaseName)
	{
		//unique string that is used to access a database made on a cluster on mongoDB
		this.uri = new MongoClientURI(uri);
		//initializing mongoDB connection
		this.mongoClient = new MongoClient(this.uri);
		//database name -- will be 'LeagueShare'
		this.database = mongoClient.getDatabase(databaseName);
	}

	public MongoClient getClient()
	{
		return this.mongoClient;
	}
	
	public MongoDatabase getDatabase()
	{
		return this.database;
	}
	
	public void createCollection(String collectionName)
	{
		this.database.createCollection(collectionName);
	}
	
	public Document getDocument(String collectionName, String uniqueID)
	{
		try
		{
			return this.database.getCollection(collectionName).find(eq("_id", new ObjectId(uniqueID))).first();
		}
		catch (java.lang.IllegalArgumentException e)
		{
			return null; // bad input, not in database.
		}
	}
	
	
//	public MongoCollection<Document> getCollection(String collectionName)
//	{
//		//collections -- will be Users and Leagues
//		return this.database.getCollection(collectionName);
//	}
	
//	public void addDocument(String collectionName, Document newDocument)
//	{
//		//example use
//		//dbHelper.addDocument("Users", newUserDocument);
//		this.database.getCollection(collectionName).insertOne(newDocument);
//	}
	
//	public Document createDocument()
//	{
//		//example use
//		//Document newUserDocument = dbHelper.createDocument();
//		//example of how to get the value by using the key
//		System.out.println(newUserDocument.get("username"));
//		//example of how to get the users unique id - will be null unless added to or is in DB 
//		System.out.println(newUserDocument.get("_id").toString());
//		return new Document();
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	//USER METHODS
		
	public String createUser(String username, String password, String firstName, String lastName) 
	{
		//creating document that will be passed into DB
		Document newUserDocument = new Document();
		
		newUserDocument.put("username", username);
		newUserDocument.put("password", password);
		newUserDocument.put("firstName", firstName);
		newUserDocument.put("lastName", lastName);
		
		//initializing ID arrays
		ArrayList<String> followedLeagueIDs = new ArrayList<String>();
		ArrayList<String> followedTeamIDs = new ArrayList<String>();
		ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
		ArrayList<String> ownedTeamIDs = new ArrayList<String>();
		ArrayList<String> managedTeamIDs = new ArrayList<String>();
		ArrayList<String> managedTeamLeagueIDs = new ArrayList<String>();
		ArrayList<String> leagueCastedIDs = new ArrayList<String>();
				
		newUserDocument.put("followedLeagueIDs", followedLeagueIDs);
		newUserDocument.put("followedTeamIDs", followedTeamIDs);
		newUserDocument.put("ownedLeagueIDs", ownedLeagueIDs);
		newUserDocument.put("ownedTeamIDs", ownedTeamIDs);
		newUserDocument.put("managedTeamIDs", managedTeamIDs);
		newUserDocument.put("managedTeamLeagueIDs", managedTeamLeagueIDs);
		newUserDocument.put("leagueCastedIDs", leagueCastedIDs);
		
		//adding new user to Users collection
		this.database.getCollection(USERS).insertOne(newUserDocument);
		
		//return unique ID
		return newUserDocument.get("_id").toString();
	}
	
	/*
	 * deleteUser()
	 */
	
	
	public String getUserIDByUsername(String username)
	{		 
        // Performing a read operation on the collection.
        FindIterable<Document> fi = this.database.getCollection(USERS).find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) 
            {      
            	
            	Document userDocument = cursor.next();
            	
        		JSONParser parser = new JSONParser();
        		
        		try
        		{
        			Object obj = parser.parse(userDocument.toJson());
        			JSONObject userData = (JSONObject) obj;
        			        						
    				String currentUsername = userData.get("username").toString();
    				
    				//System.out.println(currentLeagueName.split("\"")[0]); // all leagues that this search matches.
    				
    				if (currentUsername.split("\"")[0].toLowerCase().equals(username.toLowerCase())) //  go to the next ", name is stored in element 1.
    				{        				
    					String oid = userData.get("_id").toString(); 
    					String[] id = oid.split("\""); // removing oid from string.
        				return id[3];
    				}
        		}
        		catch (Exception e) 
        		{
        			// do nothing, move on.
        		}
            }
        }
        catch (NullPointerException e)
		{
            cursor.close();
			return "error";
		}
        
        return "";
		
	}
	
	public void addFollowedLeagueID(String userID, String followedLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("followedLeagueIDs", followedLeagueID));
	}
	
	public void addFollowedTeamID(String userID, String followedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("followedTeamIDs", followedTeamID));
	}
	
	public void addOwnedLeagueID(String userID, String ownedLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("ownedLeagueIDs", ownedLeagueID));
	}
	
	public void addOwnedTeamID(String userID, String ownedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("ownedTeamIDs", ownedTeamID));
	}
	
	public void addManagedTeamID(String userID, String managedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("managedTeamIDs", managedTeamID));
	}
	
	public void addManagedTeamLeagueID(String userID, String managedTeamLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.push("managedTeamLeagueIDs", managedTeamLeagueID));
	}
	
	public void addLeagueCastedID(String userID, String leagueCastedID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.addToSet("leagueCastedIDs", leagueCastedID));
	}
	
	public void removeFollowedLeagueID(String userID, String followedLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("followedLeagueIDs", followedLeagueID));
	}
	
	public void removeFollowedTeamID(String userID, String followedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("followedTeamIDs", followedTeamID));
	}
	
	public void removeOwnedLeagueID(String userID, String ownedLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("ownedLeagueIDs", ownedLeagueID));
	}
	
	public void removeOwnedTeamID(String userID, String ownedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("ownedTeamIDs", ownedTeamID));
	}
	
	public void removeManagedTeamID(String userID, String managedTeamID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("managedTeamIDs", managedTeamID));
	}
	
	public void removeManagedLeagueTeamID(String userID, String managedTeamLeagueID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("managedTeamLeagueIDs", managedTeamLeagueID));
	}
	
	public void removeLeagueCastedID(String userID, String leagueCastedID)
	{
		this.database.getCollection(USERS).updateOne(eq("_id", new ObjectId(userID)), Updates.pull("leagueCastedIDs", leagueCastedID));
	}
	

	
	
	/*
	 * League Document
	 * 		- _id
	 * 
	 * 		- name
	 * 		- ownerID
	 * 		- sport
	 * 		- description
	 *		 
	 * 		- casterIDs[]
	 *		
	 *
	 * 		- teams[]				createTeam(String leagueID, String teamName, String zipcode)
	 *			- teamName
	 *			- zipcode
	 *			- players[]			addPlayer(String leagueID, String teamName, String firstName, String lastName, ...) //each player will need a unique ID because people can have the same names
	 *				- firstName
	 *				- lastName
	 *				- stats[]	- completely unique to the league
	 * 
	 * 
	 *		- matches[]				createMatch(String leagueID, String homeTeamID, String awayTeamID, String date, String finalScore)
	 *			- homeTeamID
	 *			- awayTeamID
	 *			- date
	 *			- finalScore
	 *
	 *		- TrackedStatistics[]	createTrackedStatistic(String leagueID, String StatisticName)
	 *			- StatisticName
	 */
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	//LEAGUE METHODS
	
	public ArrayList<String> getLeagueIDByLeagueName(String leagueName)
	{
		ArrayList<String> matchingIDs = new ArrayList<String>();
				 
        // Performing a read operation on the collection.
        FindIterable<Document> fi = this.database.getCollection(LEAGUES).find();
        MongoCursor<Document> cursor = fi.iterator();
        try {
            while(cursor.hasNext()) 
            {      
            	
            	Document leagueDocument = cursor.next();
            	
        		JSONParser parser = new JSONParser();
        		
        		try
        		{
        			Object obj = parser.parse(leagueDocument.toJson());
        			JSONObject leagueData = (JSONObject) obj;
        			        						
    				String currentLeagueName = leagueData.get("leagueName").toString();
    				
    				//System.out.println(currentLeagueName.split("\"")[0]); // all leagues that this search matches.
    				
    				if (currentLeagueName.split("\"")[0].toLowerCase().contains(leagueName.toLowerCase())) //  go to the next ", name is stored in element 1.
    				{
    					String oid = leagueData.get("_id").toString(); 
    					String[] id = oid.split("\""); // removing oid from string.
        				matchingIDs.add(id[3]); // id is stored in element 3.
    					matchingIDs.add(currentLeagueName);
        				
        				//System.out.println(id[3]); // matched ID's
    				}
        		}
        		catch (Exception e) 
        		{
        			System.out.println(e.getMessage());
        			// do nothing, move on.
        		}
            }
        }
        catch (NullPointerException e)
		{
            cursor.close();
			return matchingIDs;
		}
        
        return matchingIDs;
	}
	
	public String createLeague(String leagueName, String ownerID, String sport, String description) 
	{
		//creating document that will be passed into DB
		Document newLeagueDocument = new Document();
		
		newLeagueDocument.put("leagueName", leagueName);
		newLeagueDocument.put("ownerID", ownerID);
		newLeagueDocument.put("sport", sport);
		newLeagueDocument.put("description", description);

		//initializing arrays
		ArrayList<String> casterIDs = new ArrayList<String>();
		ArrayList<Document> teams = new ArrayList<Document>();
		ArrayList<Document> matches = new ArrayList<Document>();
		ArrayList<Document> trackedStatistics = new ArrayList<Document>();
	
		
		newLeagueDocument.put("casterIDs", casterIDs);
		newLeagueDocument.put("teams", teams);
		newLeagueDocument.put("matches", matches);
		newLeagueDocument.put("trackedStatistics", trackedStatistics);
	
		//adding new league to leagues collection
		this.database.getCollection(LEAGUES).insertOne(newLeagueDocument);
		
		//return unique ID
		return newLeagueDocument.get("_id").toString();
	}
	
	public void updateLeague(String leagueID, String leagueName, String ownerID, String sport, String description)
	{
		BasicDBObject where = new BasicDBObject().append("_id",new ObjectId(leagueID));
								
		Document update = new Document();
		
		if (!leagueName.equals("")) // if not blank
		{
			update.append("leagueName", leagueName);
		}
		
		if (!ownerID.equals("")) // if not blank
		{
			update.append("ownerID", ownerID);
		}
		
		if (!sport.equals("")) // if not blank
		{
			update.append("sport", sport);
		}
		
		if (!description.equals("")) // if not blank
		{
			update.append("description", description);
		}

		
		Bson set = new Document().append("$set", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	/*
	 * deleteLeague
	 */
	
	public void addLeagueCasterID(String leagueID, String leagueCasterID)
	{
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("casterIDs", leagueCasterID));
	}
	
	public void removeLeagueCasterID(String leagueID, String leagueCasterID)
	{
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.pull("casterIDs", leagueCasterID));
	}
	
	public void deleteLeague(String leagueID)
	{
		this.database.getCollection(LEAGUES).deleteOne(eq("_id", new ObjectId(leagueID)));
	}
	
	public String getTeamIDByTeamName(String leagueID, String teamName)
	{
		try
		{
			MongoCollection<Document> collection = database.getCollection(LEAGUES);
			
			FindIterable<Document> projection = collection.find()
			    .projection( fields( include( "teams" ) ) ).projection( fields( include( "teams._id", "teams.teamName" ) ) );
			
		    Document doc = projection.first();
		    
		    String[] documents = doc.toString().split("Document");
		    
		    String[] ids = new String[documents.length];
		    String[] teamNames = new String[documents.length];
		    
		    for (int i = 2; i < documents.length; i++) // spliting out team names and ID's into arrays.
		    {
		    	ids[i-2] = documents[i].split("_id=")[1].split(",")[0];
		    	teamNames[i-2] = documents[i].split("teamName=")[1].split("}")[0];
		    }
		    
		    for (int i = 0; i < teamNames.length; i++)
		    {
		    	if (teamNames[i].contains(teamName))
		    	{
			    	return ids[i];
		    	}
		    }
		    
		    return "";
		}
		catch (NullPointerException e)
		{
			return "";
		}
	}
	
	
	public Document getTeamDocumentByID(String leagueID, String teamID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id", new ObjectId(teamID));
		
//		System.out.println(where);

		return this.database.getCollection(LEAGUES).find(where).first();
	}
		
	public String createTeam(String leagueID, String teamName, String zipcode) 
	{
		Document newTeamDocument = new Document();
		
		newTeamDocument.put("_id", new ObjectId());
		newTeamDocument.put("teamName", teamName);
		newTeamDocument.put("zipcode", zipcode);
		newTeamDocument.put("leagueID", leagueID);
		
		ArrayList<Document> players = new ArrayList<Document>();
		
		newTeamDocument.put("players", players);
		
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("teams", newTeamDocument));
		
		return newTeamDocument.get("_id").toString();
	}
	
	public void updateTeam(String leagueID, String teamID, String teamName, String zipcode)
	{
		BasicDBObject where = new BasicDBObject().append("_id",new ObjectId(leagueID)).append("teams._id", new ObjectId(teamID));
								
		Document update = new Document();
		
		if (!teamName.equals("")) // if not blank
		{
			update.append("teams.$.teamName", teamName);
		}
		
		if (!zipcode.equals("")) // if not blank
		{
			update.append("teams.$.zipcode", zipcode);
		}

		
		Bson set = new Document().append("$set", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	public void deleteTeam(String leagueID, String teamID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id", new ObjectId(teamID));

		Bson update = new Document().append("teams", new BasicDBObject("_id", new ObjectId(teamID)));
		
		Bson set = new Document().append("$pull", update);

		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	
	
	public String createPlayer(String leagueID, String teamID, String firstName, String lastName)
	{
		Document newPlayerDocument = new Document();
		
		newPlayerDocument.put("_id", new ObjectId());
		newPlayerDocument.put("firstName", firstName);
		newPlayerDocument.put("lastName", lastName);
		
		ArrayList<Document> statistics = new ArrayList<Document>();
			
		newPlayerDocument.put("statistics", statistics);
		
		ArrayList<String> trackedStatistics = getTrackedStatistics(leagueID); // get the tracked stats for this league
				
		for (int i = 0; i < trackedStatistics.size(); i++)	// add them.
		{
			statistics.add(makeStatisticForPlayer(statistics, trackedStatistics.get(i)));
		}
		
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID));
				
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$.players", newPlayerDocument));

		return newPlayerDocument.get("_id").toString();
	}
	
	public void updatePlayer(String leagueID, String teamID, String playerID, String firstName, String lastName)
	{
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID));
								
		Document update = new Document();
		
		Document leagueDocument = this.database.getCollection(LEAGUES).find(where).first();
		
		JSONParser parser = new JSONParser();
		
		try
		{	
			int playerDocNum = 0;
			
			Object obj = parser.parse(leagueDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
			
			JSONObject teamData = (JSONObject) teamDataArray.get(0);
			
			for (int i = 0; i < teamDataArray.size(); i++) // get the team data, matching the ID so that we can get the number needed to be modified.
			{
				JSONObject currentTeamData = (JSONObject) teamDataArray.get(i);
				String oid = currentTeamData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(teamID)) // if this is the id searched for...
				{
					teamData = currentTeamData; // save this data.
					break;
				}
			}
			

			JSONArray playerDataArray = (JSONArray) teamData.get("players");
			
			for (int i = 0; i < playerDataArray.size(); i++)  // get the player data, matching the ID so that we can get the number needed to be modified.
			{
				JSONObject currentPlayerData = (JSONObject) playerDataArray.get(i);
				String oid = currentPlayerData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(playerID)) // if this is the id searched for...
				{
					playerDocNum = i;
					break;
				}
			}
			
			if (!firstName.equals("")) // if not blank
			{
				update.append("teams." + playerDocNum + ".players.$.firstName", firstName);
			}
			
			if (!lastName.equals("")) // if not blank
			{
				update.append("teams." + playerDocNum + ".players.$.lastName", lastName);
			}

			
			Bson set = new Document().append("$set", update);
			
			this.database.getCollection(LEAGUES).updateOne(where, set);
		}
		catch (Exception e) 
		{
			System.out.println("Error -- Document not found, Update Player");
			return; // its not here.
		}
	}
	
	public void deletePlayer(String leagueID, String teamID, String playerID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID));

		Bson update = new Document().append("teams.$.players", new BasicDBObject("_id", new ObjectId(playerID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	private Document makeStatisticForPlayer(ArrayList<Document> statistics, String statisticName) // used
	{
		Document newStatisticDocument = new Document();
		
		newStatisticDocument.put("_id", new ObjectId());
		newStatisticDocument.put("statisticName", statisticName);
		newStatisticDocument.put("statisticValue", 0);
		
		return newStatisticDocument;
	}
		
	public void updatePlayerStatisticByName(String leagueID, String teamID, String playerID, String statName, int newStatValue)
	{
		BasicDBObject where = new BasicDBObject().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID))
				.append("teams.players._id",new ObjectId(playerID)).append("teams.players.statistics.statisticName", statName);

		Document leagueDocument = this.database.getCollection(LEAGUES).find(where).first();
						
		JSONParser parser = new JSONParser();
		
		try
		{	
			int teamDocNum = 0;
			int playerDocNum = 0;
			int statDocNum = 0;
			
			
			Object obj = parser.parse(leagueDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray teamDataArray = (JSONArray) leagueData.get("teams");
			
			JSONObject teamData = (JSONObject) teamDataArray.get(0);
			
			for (int i = 0; i < teamDataArray.size(); i++) // get the team data, matching the ID so that we can get the number needed to be modified.
			{
				JSONObject currentTeamData = (JSONObject) teamDataArray.get(i);
				String oid = currentTeamData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(teamID)) // if this is the id searched for...
				{
					teamData = currentTeamData; // save this data.
					teamDocNum = i;
					break;
				}
			}
			

			JSONArray playerDataArray = (JSONArray) teamData.get("players");

			JSONObject playerData = (JSONObject) playerDataArray.get(0);
			
			for (int i = 0; i < playerDataArray.size(); i++)  // get the player data, matching the ID so that we can get the number needed to be modified.
			{
				JSONObject currentPlayerData = (JSONObject) playerDataArray.get(i);
				String oid = currentPlayerData.get("_id").toString(); 
				String[] id = oid.split("\""); // removing oid from string.
				if (id[3].equals(playerID)) // if this is the id searched for...
				{
					playerData = currentPlayerData;
					playerDocNum = i;
					break;
				}
			}
			
			
			JSONArray statisticDataArray = (JSONArray) playerData.get("statistics");

			for (int i = 0; i < statisticDataArray.size(); i++)  // get the player data, matching the ID so that we can get the number needed to be modified.
			{
				JSONObject currentPlayerData = (JSONObject) statisticDataArray.get(i);
				String currentStatName = currentPlayerData.get("statisticName").toString(); 
				if (currentStatName.split("\"")[0].equals(statName)) // if this is the id searched for...
				{
					statDocNum = i;
					break;
				}
			}
			
//			System.out.println("teams." + teamDocNum + ".players." + playerDocNum + ".statistics." + statDocNum + ".statValue"); // for checking where the update document is going.
			
			Bson update = new Document().append("teams." + teamDocNum + ".players." + playerDocNum + ".statistics." + statDocNum 
					+ ".statisticValue", newStatValue);
			
			Bson set = new Document().append("$set", update);
			
			this.database.getCollection(LEAGUES).updateOne(where, set);
		}
		catch (Exception e) 
		{
			System.out.println("Error -- Document not found, Update Player Statistics");
			return; // its not here.
		}
	}
	
	
	public Document getMatchDocumentByID(String leagueID, String matchID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("matches._id", new ObjectId(matchID));

		return this.database.getCollection(LEAGUES).find(where).first();
	}
	
	public String createMatch(String leagueID, String homeTeamID, String awayTeamID, String date)
	{
		Document newMatchDocument = new Document();
		
		newMatchDocument.put("_id", new ObjectId());
		newMatchDocument.put("homeTeamID", homeTeamID);
		newMatchDocument.put("awayTeamID", awayTeamID);
		newMatchDocument.put("date", date);
		newMatchDocument.put("homeScore", "0");
		newMatchDocument.put("awayScore", "0");
		
		ArrayList<Document> chat = new ArrayList<Document>();
		
		newMatchDocument.put("chat", chat);
	
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("matches", newMatchDocument));
		
		return newMatchDocument.get("_id").toString();
	}
	
	public void updateMatch(String leagueID, String matchID, String homeTeamID, String awayTeamID, String date, String homeScore, String awayScore)
	{
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID));
								
		Document update = new Document();
		
		if (!homeTeamID.equals("")) // if not blank
		{
			update.append("matches.$.homeTeamID", homeTeamID);
		}
		
		if (!awayTeamID.equals("")) // if not blank
		{
			update.append("matches.$.awayTeamID", awayTeamID);
		}

		if (!date.equals("")) // if not blank
		{
			update.append("matches.$.date", date);
		}
		
		if (!homeScore.equals("")) // if not blank
		{
			update.append("matches.$.homeScore", homeScore);
		}

		if (!awayScore.equals("")) // if not blank
		{
			update.append("matches.$.awayScore", awayScore);
		}
		
		
		Bson set = new Document().append("$set", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	
	public void deleteMatch(String leagueID, String matchID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID));

		Bson update = new Document().append("matches", new BasicDBObject("_id", new ObjectId(matchID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	public void updateMatchScore(String leagueID, String matchID, String homeScore, String awayScore)
	{
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID));
		
		Bson update;
		
		if (homeScore.equals("") || awayScore.equals("")) // update one score
		{			
			if (awayScore.equals("")) // update home score.
			{
				update = new Document().append("matches.$.homeScore", homeScore);
			}
			else // update away score
			{
				update = new Document().append("matches.$.awayScore", awayScore);
			}
		}
		else // update both scores
		{
			update = new Document().append("matches.$.homeScore", homeScore).append("matches.$.awayScore", awayScore);
		}
				
		Bson set = new Document().append("$set", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	public void updateMatchDate(String leagueID, String matchID, String date)
	{
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID));
		
		Bson update = new Document().append("matches.$.date", date);
				
		Bson set = new Document().append("$set", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	 
	
	public Document getTrackedStatisticDocumentByID(String leagueID, String trackedStatisticID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("trackedStatistics._id", new ObjectId(trackedStatisticID));

		return this.database.getCollection(LEAGUES).find(where).first();
	}
	
	public String createTrackedStatistic(String leagueID, String statisticName)
	{
		Document newTrackedStatisticDocument = new Document();
		
		newTrackedStatisticDocument.put("_id", new ObjectId());
		newTrackedStatisticDocument.put("statisticName", statisticName);
	
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("trackedStatistics", newTrackedStatisticDocument));
		
		// Updating all players
		

		newTrackedStatisticDocument.put("statisticValue", 0);

		
		Bson where = new Document().append("_id",new ObjectId(leagueID));
		
		Bson update = new Document().append("teams.$[].players.$[].statistics", newTrackedStatisticDocument);
		
		Bson set = new Document().append("$addToSet", update);
		
		this.database.getCollection(LEAGUES).updateMany(where, set);
		
		return newTrackedStatisticDocument.get("_id").toString();
	}
	
	public void deleteTrackedStatistic(String leagueID, String trackedStatisticID)
	{	
		
		Bson where = new Document().append("_id",new ObjectId(leagueID));
		
		Bson update = new Document().append("teams.$[].players.$[].statistics", new BasicDBObject("_id", new ObjectId(trackedStatisticID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateMany(where, set);
		
		
		where = new Document().append("_id",new ObjectId(leagueID)).append("trackedStatistics._id",new ObjectId(trackedStatisticID));

		update = new Document().append("trackedStatistics", new BasicDBObject("_id", new ObjectId(trackedStatisticID)));
		
		set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}

	private ArrayList<String> getTrackedStatistics(String leagueID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID));

		Document leagueDocument = this.database.getCollection(LEAGUES).find(where).first();
				
		JSONParser parser = new JSONParser();
		
		try
		{
			Object obj = parser.parse(leagueDocument.toJson());
			JSONObject leagueData = (JSONObject) obj;
			
			JSONArray trackedStatistics = (JSONArray) leagueData.get("trackedStatistics");
						
			ArrayList<String> statisticNames = new ArrayList<String>();
			
			// separating stat name from value for hashmap
			for (int i = 0; i < trackedStatistics.size(); i++)
			{
				JSONObject stat = (JSONObject) trackedStatistics.get(i);
				String statName = stat.get("statisticName").toString(); 
				statisticNames.add(statName.split("\"")[0]); //  go to the next ", name is stored in element 1.
			}
			
			return statisticNames;
			
		}
		catch (Exception e) 
		{
			return new ArrayList<String>(); // nothing in array.
		}
	}

	public String addMessageToChat(String leagueID, String matchID, String username, String message, String time)
	{
		Document newChatDocument = new Document();
		
		newChatDocument.put("_id", new ObjectId());
		newChatDocument.put("username", username);
		newChatDocument.put("message", message);
		newChatDocument.put("time", time);		
			
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID));
		
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("matches.$.chat", newChatDocument));
		
		return newChatDocument.get("_id").toString();
	}
	
	public void  deleteMessageFromChat(String leagueID, String matchID, String chatID)
	{
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("matches._id",new ObjectId(matchID)).append("matches.chat._id",new ObjectId(chatID));

		Bson update = new Document().append("matches.$.chat", new BasicDBObject("_id", new ObjectId(chatID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	public Document getChatDocumentByMatchID(String leagueID, String matchID)
	{
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("matches._id", new ObjectId(matchID));

		return this.database.getCollection(LEAGUES).find(where).first();
	}
	
	
	
	
	void printAllUsers()
	{		
		MongoCursor<Document> cursor = database.getCollection(USERS).find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
	}
	
	void printUser(String UserID)
	{		
		Bson where = new Document().append("_id",new ObjectId(UserID));

		System.out.println(this.database.getCollection(USERS).find(where).first());
	}

	
	void printAllLeagues()
	{		
		MongoCursor<Document> cursor = database.getCollection(LEAGUES).find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
	}
	
	void printLeague(String leagueID)
	{		
		Bson where = new Document().append("_id",new ObjectId(leagueID));

		System.out.println(this.database.getCollection(LEAGUES).find(where).first());
	}
	
	public static void main(String[] args)
	{
		// -- ESTABLISHING CONNECTION TO DATABASE --
		DatabaseHelper dbHelper = new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 	5e7129f4b0f12336fb6ad64b	(Fast Finger Gang)
		5e7129f4b0f12336fb6ad64c	(Paceful Picassos)
		5e7129f4b0f12336fb6ad64d	(Slippery Sliders)
	 */
		
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64b", "Primrose", "Pineda");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64b", "Katya", "Spooner");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64b", "Sameha", "Chen");
//		
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64c", "Ozan", "Bishop");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64c", "Emanual", "Davies");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64c", "Leia", "Jordan");
//		
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64d", "Hilda", "Olson");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64d", "Zunaira", "McFadden");
//		dbHelper.createPlayer("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64d", "Ayva", "Holmes");
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
////		String newUserID = dbHelper.createUser("aaa", "aaa", "Aristotle", "Totsworth");
////		
//		String newLeagueID = dbHelper.createLeague("Speed Finger Painting League", "5e55dcdf8fe1f34ed9f230ed", "finger painting", "timed painting for quick artists");
//		
//		String newTeamID1 = dbHelper.createTeam(newLeagueID, "Dashing Dali Doodlers", "32501");
//		String newTeamID2 = dbHelper.createTeam(newLeagueID, "Running Rembrandts", "32502");
//		String newTeamID3 = dbHelper.createTeam(newLeagueID, "Fast Finger Gang", "32503");
//		String newTeamID4 = dbHelper.createTeam(newLeagueID, "Paceful Picassos", "32504");
//		String newTeamID5 = dbHelper.createTeam(newLeagueID, "Slippery Sliders", "32505");
//		
//		//NEW TEAM DOES RETURN NEW VALUES SO THE PROBLEM IS DEFINITELY IN CREATE PLAYER
////		System.out.println(newTeamID1);
////		System.out.println(newTeamID2);
////		System.out.println(newTeamID3);
////		System.out.println(newTeamID4);
////		System.out.println(newTeamID5);
//		
//		//THIS DOES NOT CREATE A PLAYER FOR A SPECIFIC TEAM BUT INSTEAD CREATES A PLAYER THAT IS ADDED TO EVERY TEAM IN THE LEAGUE
//		String newPlayer1 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Jasper", "Jellington");
//		String newPlayer2 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Rosie", "Husker");
//		String newPlayer3 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Naomi", "Meeow");
//			
//		String newPlayer4 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Rodger", "Dodger");
//		String newPlayer5 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Eugene", "Euler");
//		String newPlayer6 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Vincent", "Gogh");
//		
//		String newMatch1 = dbHelper.createMatch(newLeagueID, newTeamID1, newTeamID2, "3/9/20");
//		String newMatch2 = dbHelper.createMatch(newLeagueID, newTeamID4, newTeamID3, "3/20/20");
//		String newMatch3 = dbHelper.createMatch(newLeagueID, newTeamID5, newTeamID2, "3/24/20");
//		String newMatch4 = dbHelper.createMatch(newLeagueID, newTeamID3, newTeamID1, "3/30/20");
//		String newMatch5 = dbHelper.createMatch(newLeagueID, newTeamID5, newTeamID4, "4/2/20");
//		String newMatch6 = dbHelper.createMatch(newLeagueID, newTeamID2, newTeamID3, "4/7/20");
//		
//		//THIS DOES NOT BEHAVE AS EXPECTED.. IT MAY BE EASIER TO PASS IN EACH VALUE AT THE SAME TIME
////		dbHelper.updateMatchScore(newLeagueID, newMatch1, "7", false);
////		dbHelper.updateMatchScore(newLeagueID, newMatch1, "10", true);
//		
//		//THIS ONLY CREATES THE STATISTIC FOR 1 TEAM, NOT ALL TEAMS || WE WILL NEED AN UPDATE STATISTIC WHERE THE SPECIFIC PLAYER, THE STAT STRING TO UPDATE, AND NEW VALUE ARE PASSED IN
//		String newStatistic1 = dbHelper.createStatistic(newLeagueID, "Portraits Completed", "0");
//		String newStatistic2 = dbHelper.createStatistic(newLeagueID, "Most Colors Used", "0");
//		String newStatistic3 = dbHelper.createStatistic(newLeagueID, "Fastest Painting (seconds)", "0");
//		
//		dbHelper.printLeague(newLeagueID.toString());
//		
//		
//		dbHelper.addFollowedLeagueID(newUserID, newLeagueID);
//		dbHelper.addOwnedLeagueID(newUserID, newLeagueID);
//		
//		//adding connect team/league pair
//		dbHelper.addManagedTeamID(newUserID, newTeamID1);
//		dbHelper.addManagedTeamLeagueID(newUserID, newLeagueID);
//		
//		dbHelper.addManagedTeamID(newUserID, newTeamID2);
//		dbHelper.addManagedTeamLeagueID(newUserID, newLeagueID);
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		
////	String newUserID = dbHelper.createUser("aaa", "aaa", "Aristotle", "Totsworth");
////	
//	String newLeagueID = dbHelper.createLeague("Mario Kart League", "5e7129f3b0f12336fb6ad647", "Mario Kart Racing", "Racing League for Nintendo's Finest");
//	
//	String newTeamID1 = dbHelper.createTeam(newLeagueID, "The Toadstools", "23451");
//	String newTeamID2 = dbHelper.createTeam(newLeagueID, "The Plumbers", "54673");
//	String newTeamID3 = dbHelper.createTeam(newLeagueID, "The Big Boys", "23418");
//
//	
//	//NEW TEAM DOES RETURN NEW VALUES SO THE PROBLEM IS DEFINITELY IN CREATE PLAYER
////	System.out.println(newTeamID1);
////	System.out.println(newTeamID2);
////	System.out.println(newTeamID3);
////	System.out.println(newTeamID4);
////	System.out.println(newTeamID5);
//	
//	//THIS DOES NOT CREATE A PLAYER FOR A SPECIFIC TEAM BUT INSTEAD CREATES A PLAYER THAT IS ADDED TO EVERY TEAM IN THE LEAGUE
//	String newPlayer1 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Toad", "Stool");
//	String newPlayer2 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Toadette", "Stool");
//	
//	String newPlayer3 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Mario", "Mario");
//	String newPlayer4 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Luigi", "Mario");
//	
//	String newPlayer5 = dbHelper.createPlayer(newLeagueID, newTeamID3, "Bowser", "UNKNOWN");
//	String newPlayer6 = dbHelper.createPlayer(newLeagueID, newTeamID3, "Donkey", "Kong");
//	
//	String newMatch1 = dbHelper.createMatch(newLeagueID, newTeamID1, newTeamID2, "3/20/20");
//	String newMatch2 = dbHelper.createMatch(newLeagueID, newTeamID3, newTeamID1, "3/24/20");
//	String newMatch3 = dbHelper.createMatch(newLeagueID, newTeamID2, newTeamID3, "3/27/20");
//
//	
//	//THIS DOES NOT BEHAVE AS EXPECTED.. IT MAY BE EASIER TO PASS IN EACH VALUE AT THE SAME TIME
////	dbHelper.updateMatchScore(newLeagueID, newMatch1, "7", false);
////	dbHelper.updateMatchScore(newLeagueID, newMatch1, "10", true);
//	
//	//THIS ONLY CREATES THE STATISTIC FOR 1 TEAM, NOT ALL TEAMS || WE WILL NEED AN UPDATE STATISTIC WHERE THE SPECIFIC PLAYER, THE STAT STRING TO UPDATE, AND NEW VALUE ARE PASSED IN
//	String newStatistic1 = dbHelper.createStatistic(newLeagueID, "Distance Drifted", "0");
//	String newStatistic2 = dbHelper.createStatistic(newLeagueID, "Most Items Used", "0");
//	String newStatistic3 = dbHelper.createStatistic(newLeagueID, "Wins", "0");
//	String newStatistic4 = dbHelper.createStatistic(newLeagueID, "Losses", "0");
//	
//	dbHelper.printLeague(newLeagueID.toString());
//	
//	
//	dbHelper.addFollowedLeagueID("5e7129f3b0f12336fb6ad647", newLeagueID);
//	dbHelper.addOwnedLeagueID("5e7129f3b0f12336fb6ad647", newLeagueID);
//	
//	//adding connect team/league pair
//	dbHelper.addManagedTeamID("5e7129f3b0f12336fb6ad647", newTeamID1);
//	dbHelper.addManagedTeamLeagueID("5e7129f3b0f12336fb6ad647", newLeagueID);
//	
//	dbHelper.addManagedTeamID("5e7129f3b0f12336fb6ad647", newTeamID3);
//	dbHelper.addManagedTeamLeagueID("5e7129f3b0f12336fb6ad647", newLeagueID);
			
		
		/*  NEW FUNCTIONS  */
		
				
//		dbHelper.printLeague("5e8cc22649a7ee3fef1299d7");
		
//		dbHelper.printAllUsers();
				
		// -- CREATING NEW COLLECTIONS ON MONGO-- 
//		dbHelper.createCollection("Users");
//		dbHelper.createCollection("Leagues");
		
		
		// -- CREATING NEW USER -- 
//		String newUserID = dbHelper.createUser("leaf_consumer", "herbivore1993", "Jasper", "Jellington");
//		System.out.println(newUserID);
//		dbHelper.createUser("WhiteWolf", "Yennifer", "Geralt", "Of Rivia");
//		System.out.println(dbHelper.getUserIDByUsername("WhiteWolf"));
		
		// -- FINDING USER BY NAME -- 
//		System.out.println(dbHelper.getUserIDByUsername("leaf_consumer"));
		
		
		// -- CREATING, UPDATING, AND DELETING LEAGUES -- 
//		String newLeagueID = dbHelper.createLeague("DeleteLeague", "Fuck you thats who", "Her favorite sport", "A league designed with your mom in mind");
//		dbHelper.printLeague(newLeagueID);
		
//		dbHelper.updateLeague(newLeagueID, "Updated", "Who cares", "F", "z");

//		dbHelper.deleteLeague(newLeagueID);

		
		// -- FINDING LEAGUE BY NAME -- 
//		System.out.println(dbHelper.getLeagueByLeagueName("paint"));

		
		// -- CREATING, UPDATING, AND DELETING NEW TEAMS -- 
//		String newTeamID1 = dbHelper.createTeam(newLeagueID, "DeleteTeam1", "DeletedZipcode");
//		String newTeamID2 = dbHelper.createTeam(newLeagueID, "DeleteTeam2", "DeletedZipcode");
		
//		dbHelper.updateTeam(newLeagueID, newTeamID1, "WillBeDeletedTeam1", "55555");
//		dbHelper.updateTeam(newLeagueID, newTeamID2, "DeleteableTeam2", "12345");
		
//		dbHelper.deleteTeam(newLeagueID, newTeamID1);
		
		// -- CREATING, UPDATING, AND DELETING NEW PLAYERS -- 
//		String newPlayerID1 = dbHelper.createPlayer(newLeagueID, newTeamID1, "Team1Noob", "Jillette");  
//		String newPlayerID2 = dbHelper.createPlayer(newLeagueID, newTeamID2, "Team2Noob", "Teller");
//		dbHelper.createPlayer("5e597b0b1b4ecc0001db20cc", "5e5d08bdfc189e00cf8ae12f", "Naomi", "Fluffington");
//		dbHelper.createPlayer("5e59763368ec36619a66bfdc", "5e6ba620833bc36df92f85b9", "Fraila", "Dogington");

//		dbHelper.updatePlayer(newLeagueID, newTeamID1, newPlayerID1, "Team1Pro", "WhoCares");
//		dbHelper.updatePlayer(newLeagueID, newTeamID2, newPlayerID2, "Team2Butt", "Face");
		
//		dbHelper.deletePlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e600d8688302978a1ed1e52");
		
		
		// -- CREATING, UPDATAING, DELETING NEW MATCHES, AND TESTING FUNCTIONS --
//		String newMatchID = dbHelper.createMatch(newLeagueID, newTeamID1, newTeamID2, "04/18/2020");

//		dbHelper.updateMatch(newLeagueID, newMatchID, newTeamID1, newTeamID2, "04/19/2020", "0", "5");

//		dbHelper.updateMatchScore("5e7129f4b0f12336fb6ad648", "5e7247b449419c7c70020ed5", "5", "5");
//		dbHelper.updateMatchDate("5e59763368ec36619a66bfdc", "5e72424369db37222c784f01", "3/25/2020");
		
//		dbHelper.deleteMatch("5e59763368ec36619a66bfdc", "5e6ba423b657f9411f758eea");
		
		
		// -- CREATING AND DELETING CHAT MESSAGES --
//		dbHelper.addMessageToChat("5e8cc22649a7ee3fef1299d7", "5e99bf52a9db252d7f945a0b", "Brandon's mom", "FUC U.", "20:35");
//		dbHelper.deleteMessageFromChat("5e8cc22649a7ee3fef1299d7", "5e99bf52a9db252d7f945a0b", "5e99c0d99ff85804f922bee8");
		
		
		// -- CREATING, UPDATING, AND DELETING NEW TRACKED STATSTICS --
//		dbHelper.createTrackedStatistic("5e7129f4b0f12336fb6ad648", "Portraits Completed");
//		dbHelper.createTrackedStatistic("5e7129f4b0f12336fb6ad648", "Most Colors Used");
//		dbHelper.createTrackedStatistic("5e7129f4b0f12336fb6ad648", "Fastest Painting (seconds)");

//		dbHelper.updatePlayerStatisticByName("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad649", "5e7129f4b0f12336fb6ad64e", "Most Colors Used", 12);
				
//		dbHelper.deleteTrackedStatistic("5e7129f4b0f12336fb6ad648", "5e8e31bff0b91e51b70c4cb2");

		
//		dbHelper.printLeague("5e8cc22649a7ee3fef1299d7");
		
//		dbHelper.printAllLeagues();

		
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
