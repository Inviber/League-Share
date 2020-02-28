import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import org.bson.Document;
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
		
	public String createNewUser(String username, String password, String firstName, String lastName) 
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
	
	
	public String getUserIDByUsername(String username)
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
	
	public String createNewLeague(String leagueName, String ownerID, String sport, String description) 
	{
		//creating document that will be passed into DB
		Document newLeagueDocument = new Document();
		
		newLeagueDocument.put("leagueName", leagueName);
		newLeagueDocument.put("ownerID", ownerID);
		newLeagueDocument.put("sport", sport);
		newLeagueDocument.put("description", description);

		//initializing arrays
		ArrayList<String> casterIDs = new ArrayList<String>();
		ArrayList<String> teams = new ArrayList<String>();
								
		newLeagueDocument.put("casterIDs", casterIDs);
		newLeagueDocument.put("teams", teams);
	
		//adding new league to leagues collection
		this.database.getCollection(LEAGUES).insertOne(newLeagueDocument);
		
		//return unique ID
		return newLeagueDocument.get("_id").toString();
	}
	
	public void addLeagueCasterID(String leagueID, String leagueCasterID)
	{
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("casterIDs", leagueCasterID));
	}
	
	public void removeLeagueCasterID(String leagueID, String leagueCasterID)
	{
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.pull("casterIDs", leagueCasterID));
	}
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		DatabaseHelper dbHelper = new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
		
//		Do not need to uncomment this because the collections already exist on the database
//		dbHelper.createCollection("Users");
//		dbHelper.createCollection("Leagues");
		
		
//		//creating new user and returning the new unique ID
//		String newUserID = dbHelper.createNewUser("leaf_consumer", "herbivore1993", "Jasper", "Jellington");
//		
//		//searching for a specific document by userID
//		Document searchedDocument = dbHelper.getDocument("Users", newUserID); 
//		
//		//printing found document
//		System.out.println(searchedDocument.toJson());
	
		
		String id = dbHelper.getUserIDByUsername("leaf_consumer");
		Document searchedDocument = dbHelper.getDocument("Users", id); 
		System.out.println(searchedDocument.toJson());
		
//		String newLeagueID = dbHelper.createNewLeague("Major League Doge Dodgeball", id, "Dodgeball", "A league designed with good boyes in mind");
//		System.out.println(newLeagueID);
		
		dbHelper.addLeagueCasterID("5e59612b284ef9642dd7c652", id);
//		dbHelper.removeLeagueCasterID("5e59612b284ef9642dd7c652", id);
		
		
	
//		dbHelper.addFollowedLeagueID(id, "aoeua123eu34098akdsank");
//		dbHelper.addFollowedTeamID(id, "02934ha123okb");
//		dbHelper.addOwnedLeagueID(id, "asoenu123thbx90ou70");
//		dbHelper.addOwnedTeamID(id, "rcg123xbroe98234");
//		dbHelper.addManagedTeamID(id, "d92123347897oeu00");
//		dbHelper.addLeagueCastedID(id, "hdm123bngf234871duht");
	
//		dbHelper.removeFollowedLeagueID(id, "aoeua123eu34098akdsank");
//		dbHelper.removeFollowedTeamID(id, "02934ha123okb");
//		dbHelper.removeOwnedLeagueID(id, "asoenu123thbx90ou70");
//		dbHelper.removeOwnedTeamID(id, "rcg123xbroe98234");
//		dbHelper.removeManagedTeamID(id, "d92123347897oeu00");
//		dbHelper.removeLeagueCastedID(id, "hdm123bngf234871duht");
	

		

		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
