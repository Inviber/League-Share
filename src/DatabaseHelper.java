import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.elemMatch;
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
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(teamID));
		
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.pull("teams", query));
	}
	
	public String createPlayer(String leagueID, String teamID, String firstName, String lastName)
	{
		
		Document newPlayerDocument = new Document();
		
		newPlayerDocument.put("_id", new ObjectId());
		newPlayerDocument.put("firstName", firstName);
		newPlayerDocument.put("lastName", lastName);
		
		ArrayList<Document> statistics = new ArrayList<Document>();
			
		newPlayerDocument.put("statistics", statistics);
		
		
		
		
		Document leagueQuery = new Document();
		leagueQuery.append("_id", new ObjectId(leagueID));
		System.out.println("League query: " + leagueQuery.toString());
		
		Document teamQuery = new Document();
		teamQuery.append("_id", new ObjectId(teamID));
		System.out.println("Team query: " + teamQuery.toString());
		
		
		// ---- JOSH's VERSION
		//System.out.println(this.database.getCollection(LEAGUES).find(where).first());
		
		Bson where = new Document().append("_id", new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID));

		Bson update = new Document().append("teams.$.players", newPlayerDocument);
		
		Bson set = new Document().append("$set", update);
				
		this.database.getCollection(LEAGUES).updateOne(where , set);
		
		//System.out.println(this.database.getCollection(LEAGUES).find(where).first());
		
		/*
		 // Longer version with catch error. This really helped with figuring out where it was going wrong. I removed these imports too.
		 try {

		    UpdateResult result = this.database.getCollection(LEAGUES).updateOne(where , set, new UpdateOptions());

		    if(result.getModifiedCount() > 0){
		        System.out.println("updated");
		    }else {
		        System.out.println("failed");
		    }
		} catch (MongoWriteException e) {
		  e.printStackTrace();
		}
		 */
		
		// ---- END JOSH'S VERSION
		
		
		
		
//		this.database.getCollection(LEAGUES).findOneAndUpdate(arg0, arg1)
		
		
		//this.database.getCollection(LEAGUES).updateOne(elemMatch("teams", eq("_id", new ObjectId(teamID))), Updates.addToSet("players", newPlayerDocument));
//		this.database.getCollection(LEAGUES).updateOne(eq("teams._id", new ObjectId(teamID)), Updates.addToSet("players", newPlayerDocument));
		
		
		//Document leagueDoc = this.database.getCollection(USERS).find(eq("firstName", "Jasper")).first();
		//System.out.println(leagueDoc);
		
//		Docu teams = leagueDoc.get("teams");
//		System.out.println(teams.);
		
		
		
//		BasicDBObject query = new BasicDBObject();
//		query.put("_id", new ObjectId(leagueID));
//		
//		//find league to update
//		FindIterable<Document> documents = this.database.getCollection(LEAGUES).find(query);
//		
//		//find team to update
//		documents = documents.filter(eq("teams._id", new ObjectId(teamID)));
//		
//		Document foundLeague = documents.first();
//		
//		foundLeague.get("players").
//	
////		
////		foundLeague.append("players", Updates.addToSet("players", newPlayerDocument));
////		
//		System.out.println(foundLeague.toString());
		
//		MongoCursor<Document> cursor = leagueDocuments.iterator();
//		
		
		return newPlayerDocument.get("_id").toString();
	}
	

	
	/*
	 * createStatistic()
	 */
	
	
	public static void main(String[] args)
	{
		// -- ESTABLISHING CONNECTION TO DATABASE --
		DatabaseHelper dbHelper = new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
		
		
		// -- CREATING NEW COLLECTIONS ON MONGO-- 
//		dbHelper.createCollection("Users");
//		dbHelper.createCollection("Leagues");
		
		
		// -- CREATING NEW USER -- 
//		String newUserID = dbHelper.createUser("leaf_consumer", "herbivore1993", "Jasper", "Jellington");
//		System.out.println(newUserID);
		
		
		// -- CREATING NEW LEAGUE -- 
//		String newLeagueID = dbHelper.createLeague("Major League Doge Dodgeball", id, "Dodgeball", "A league designed with good boyes in mind");
//		System.out.println(newLeagueID);
		
		// -- CREATING AND DELETING NEW TEAMS -- 
//		dbHelper.createTeam("5e59763368ec36619a66bfdc", "Boxer Bruisers", "41015");
//		dbHelper.deleteTeam("5e59763368ec36619a66bfdc", "5e59763368ec36619a66bfdd");
		
		// -- CREATING AND DELETING NEW PLAYERS -- 
		//System.out.println(dbHelper.getUserIDByUsername("WhiteWolf"));
		
		
		
		System.out.println(dbHelper.createPlayer("5e597b0b1b4ecc0001db20cc", "5e597b0b1b4ecc0001db20cd", "Naomi", "Fluffington"));
		
		//dbHelper.createUser("WhiteWolf", "Yennifer", "Geralt", "Of Rivia");

		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
