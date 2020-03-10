import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.model.Updates;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


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
		return this.database.getCollection(collectionName).find(eq("_id", new ObjectId(uniqueID))).first();
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
		ArrayList<String> leagueCastedIDs = new ArrayList<String>();
				
		newUserDocument.put("followedLeagueIDs", followedLeagueIDs);
		newUserDocument.put("followedTeamIDs", followedTeamIDs);
		newUserDocument.put("ownedLeagueIDs", ownedLeagueIDs);
		newUserDocument.put("ownedTeamIDs", ownedTeamIDs);
		newUserDocument.put("managedTeamIDs", managedTeamIDs);
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
		try 
		{
			//creating query request to be searched for
			BasicDBObject query = new BasicDBObject();
		    query.put("username", username);
		        
		    //retrieving all documents that match query
		    FindIterable<Document> userDocuments = this.database.getCollection(USERS).find(query);
		    
		    //choosing the first document because it is known that all usernames are unique
		    Document userDocument = userDocuments.first();
		    
		    //retrieving id
		    ObjectId userID = (ObjectId) userDocument.get("_id");
		    
		    //toStringing it
		    return userID.toString();
		}
		catch (NullPointerException e)
		{
			return "";
		}
		
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
	 * 		- teams[]				createTeam(String leagueID, String teamName, String zipcode)
	 *			- teamName
	 *			- zipcode
	 *			- players[]			addPlayer(String leagueID, String teamName, String firstName, String lastName, ...) //each player will need a unique ID because people can have the same names
	 *				- firstName
	 *				- lastName
	 *				- stats[]
	 *					- completely unique to the league
	 *			- matches[]
	 *				- homeTeam
	 *				- awayTeam
	 *				- date
	 *				- finalScore
	 */
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	//LEAGUE METHODS
	
	public String getLeagueIDByLeagueName(String leagueName)
	{
		try
		{
		    //retrieving all documents that match query
		    FindIterable<Document> leagueDocuments = this.database.getCollection(LEAGUES).find(eq("leagueName", leagueName));
		    
		    //choosing the first document because currently the firstNames are unique... and Josh doesn't know what else to do currently. <3
		    Document leagueDocument = leagueDocuments.first();
		    
		    //retrieving id
		    ObjectId leagueID = (ObjectId) leagueDocument.get("_id");
		    
		    //toStringing it
		    return leagueID.toString();
		}
		catch (NullPointerException e)
		{
			return "";
		}
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
	
		newLeagueDocument.put("casterIDs", casterIDs);
		newLeagueDocument.put("teams", teams);
	
		//adding new league to leagues collection
		this.database.getCollection(LEAGUES).insertOne(newLeagueDocument);
		
		//return unique ID
		return newLeagueDocument.get("_id").toString();
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
	
	public String getTeamIDByTeamName(String leagueID, String teamName)
	{
		try
		{
			MongoCollection<Document> collection = database.getCollection(LEAGUES);
			
			String dotNotation = "teams";

			FindIterable<Document> projection = collection.find()
			    .projection( fields( include( dotNotation ) ) ).projection( fields( include( "teams._id", "teams.teamName" ) ) );
			
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
	
	public String createTeam(String leagueID, String teamName, String zipcode) 
	{
		Document newTeamDocument = new Document();
		
		newTeamDocument.put("_id", new ObjectId());
		newTeamDocument.put("teamName", teamName);
		newTeamDocument.put("zipcode", zipcode);
		
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<Document> players = new ArrayList<Document>();
		
		newTeamDocument.put("matches", matches);
		newTeamDocument.put("players", players);
		
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("teams", newTeamDocument));
		
		return newTeamDocument.get("_id").toString();
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
		
		
//		Document leagueQuery = new Document();
//		leagueQuery.append("_id", new ObjectId(leagueID));
//		System.out.println("League query: " + leagueQuery.toString());
//		
//		Document teamQuery = new Document();
//		teamQuery.append("_id", new ObjectId(teamID));
//		System.out.println("Team query: " + teamQuery.toString());
		
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID));
				
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$[].players", newPlayerDocument));

		return newPlayerDocument.get("_id").toString();
	}
	
	public void deletePlayer(String leagueID, String teamID, String playerID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID));

		Bson update = new Document().append("teams.$[].players", new BasicDBObject("_id", new ObjectId(playerID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	public String createMatch(String leagueID, String teamID, String homeTeam, String awayTeam, String date, String finalScore)
	{
		Document newMatchDocument = new Document();
		
		newMatchDocument.put("_id", new ObjectId());
		newMatchDocument.put("homeTeam", homeTeam);
		newMatchDocument.put("awayTeam", awayTeam);
		newMatchDocument.put("date", date);
		newMatchDocument.put("finalScore", finalScore);

		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID));
		
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$[].matches", newMatchDocument));
		
		return newMatchDocument.get("_id").toString();
	}
	
	public void deleteMatch(String leagueID, String teamID, String matchID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.matches._id",new ObjectId(matchID));

		Bson update = new Document().append("teams.$[].matches", new BasicDBObject("_id", new ObjectId(matchID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	
	public String createStatistic(String leagueID, String teamID, String playerID, String statisticName, String statistic)
	{
		
		Document newStatisticDocument = new Document();
		
		newStatisticDocument.put("_id", new ObjectId());
		newStatisticDocument.put("statName", statisticName);
		newStatisticDocument.put("statValue", statistic);
		
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID));
				
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$.players.$[].statistics", newStatisticDocument));

		return newStatisticDocument.get("_id").toString();
	}
	
	public void deleteStatistic(String leagueID, String teamID, String playerID, String statID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID)).append("teams.players.statistics._id", new ObjectId(statID));

		Bson update = new Document().append("teams.$[].players.$[].statistics", new BasicDBObject("_id", new ObjectId(statID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
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
		
		dbHelper.printLeague("5e59763368ec36619a66bfdc");
		
		// -- CREATING NEW COLLECTIONS ON MONGO-- 
//		dbHelper.createCollection("Users");
//		dbHelper.createCollection("Leagues");
		
		
		// -- CREATING NEW USER -- 
//		String newUserID = dbHelper.createUser("leaf_consumer", "herbivore1993", "Jasper", "Jellington");
//		System.out.println(newUserID);
//		dbHelper.createUser("WhiteWolf", "Yennifer", "Geralt", "Of Rivia");
//		System.out.println(dbHelper.getUserIDByUsername("WhiteWolf"));

		
		
		// -- CREATING NEW LEAGUE -- 
//		String newLeagueID = dbHelper.createLeague("Major League Doge Dodgeball", id, "Dodgeball", "A league designed with good boyes in mind");
//		System.out.println(newLeagueID);
		
		
		// -- CREATING AND DELETING NEW TEAMS -- 
//		dbHelper.createTeam("5e59763368ec36619a66bfdc", "Boxer Bruisers", "41015");
//		dbHelper.deleteTeam("5e59763368ec36619a66bfdc", "5e5fda4385ccc271daa47a41");
//		dbHelper.createTeam("5e597b0b1b4ecc0001db20cc", "SharpClaws", "12345");
		
		// -- CREATING AND DELETING NEW PLAYERS -- 
//		dbHelper.createPlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "Primp", "Doge");
//		dbHelper.createPlayer("5e597b0b1b4ecc0001db20cc", "5e5d08bdfc189e00cf8ae12f", "Naomi", "Fluffington");

//		dbHelper.deletePlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e600d8688302978a1ed1e52");

		
		// -- CREATING AND DELETING NEW MATCHES --
//		dbHelper.createMatch("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "RoughBoyes", "Boxer Bruisers", "TBA", "TBA");

//		dbHelper.deleteMatch("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e5fdc02518e952252e0609c");
		
		// -- CREATING AND DELETING NEW PLAYER STATSTICS --
//		dbHelper.createStatistic("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e5fddfa4dabc675c9788718", "Times pwnd", "-2");

//		dbHelper.deleteStatistic("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e5fddfa4dabc675c9788718", "5e600ea9ca5c042a95d71db6");

		
		dbHelper.printLeague("5e59763368ec36619a66bfdc");

		
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
