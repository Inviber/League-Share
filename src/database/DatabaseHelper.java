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
				
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$.players", newPlayerDocument));

		return newPlayerDocument.get("_id").toString();
	}
	
	public void deletePlayer(String leagueID, String teamID, String playerID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID));

		Bson update = new Document().append("teams.$.players", new BasicDBObject("_id", new ObjectId(playerID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
	}
	
	
	public String createStatistic(String leagueID, String statisticName, String statistic)
	{
		
		Document newStatisticDocument = new Document();
		
		newStatisticDocument.put("_id", new ObjectId());
		newStatisticDocument.put("statName", statisticName);
		newStatisticDocument.put("statValue", statistic);
		
		Bson where = new Document().append("_id", new ObjectId(leagueID));
				
		this.database.getCollection(LEAGUES).updateOne(where, Updates.addToSet("teams.$[].players.$[].statistics", newStatisticDocument));

		return newStatisticDocument.get("_id").toString();
	}
	
	public void deleteStatistic(String leagueID, String teamID, String playerID, String statID)
	{	
		Bson where = new Document().append("_id",new ObjectId(leagueID)).append("teams._id",new ObjectId(teamID)).append("teams.players._id",new ObjectId(playerID)).append("teams.players.statistics._id", new ObjectId(statID));

		Bson update = new Document().append("teams.$[].players.$[].statistics", new BasicDBObject("_id", new ObjectId(statID)));
		
		Bson set = new Document().append("$pull", update);
		
		this.database.getCollection(LEAGUES).updateOne(where, set);
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
	
		this.database.getCollection(LEAGUES).updateOne(eq("_id", new ObjectId(leagueID)), Updates.addToSet("matches", newMatchDocument));
		
		return newMatchDocument.get("_id").toString();
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
		
		
//		String newUserID = dbHelper.createUser("aa", "aa", "Aristotle", "Totsworth");
//		
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
//		String newMatch6 = dbHelper.createMatch(newLeagueID, newTeamID2, newTeamID3, "3/9/20");
//		
//		//THIS DOES NOT BEHAVE AS EXPECTED.. IT MAY BE EASIER TO PASS IN EACH VALUE AT THE SAME TIME
////		dbHelper.updateMatchScore(newLeagueID, newMatch1, "7", false);
////		dbHelper.updateMatchScore(newLeagueID, newMatch1, "10", true);
//		
//		//THIS ONLY CREATES THE STATISTIC FOR 1 TEAM, NOT ALL TEAMS || WE WILL NEED AN UPDATE STATISTIC WHERE THE SPECIFIC PLAYER, THE STAT STRING TO UPDATE, AND NEW VALUE ARE PASSED IN
//		String newStatistic1 = dbHelper.createStatistic(newLeagueID, newTeamID1, newPlayer1, "Portraits Completed", "0");
//		String newStatistic2 = dbHelper.createStatistic(newLeagueID, newTeamID1, newPlayer1, "Most Colors Used", "0");
//		String newStatistic3 = dbHelper.createStatistic(newLeagueID, newTeamID1, newPlayer1, "Fastest Painting (seconds)", "0");
//		
//		dbHelper.printLeague(newLeagueID.toString());
//		
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
			
		
		
		
		// ---- TO DO ----
		
// DONE	//CREATE PLAYER - does not create a player for a specific team but instead creates a player that is added to every team in the league
// DONE	//UPDATE MATCH SCORE - does not behave as expected and couldnt get it to work.. it may be easier to refactor and pass in each value at the same time to update

// DONE //CREATE STATISTIC - only creates the statistic for 1 team, not all teams in the league
		  // -- RAN INTO ISSUE - Newly created players will not have theses stats automatically. Need something to add it to them.
		
		//UPDATE STATISTIC - (needs to be created) .. pass in the player id, the stat string to update, and the new value of the stat
		
		
		
//		dbHelper.printLeague("5e59763368ec36619a66bfdc");
		
//		dbHelper.printAllUsers();
		
//		dbHelper.printLeague("5e7129f4b0f12336fb6ad648");
		
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
		
		// -- CREATING AND DELETING NEW MATCHES, AND TESTING FUNCTIONS --
//		dbHelper.createMatch("5e7129f4b0f12336fb6ad648", "5e7129f4b0f12336fb6ad64d", "5e7129f4b0f12336fb6ad64c", "03/01/2020");
		
//		dbHelper.updateMatchScore("5e7129f4b0f12336fb6ad648", "5e7247b449419c7c70020ed5", "5", "5");
//		dbHelper.updateMatchDate("5e59763368ec36619a66bfdc", "5e72424369db37222c784f01", "3/25/2020");
		
//		dbHelper.deleteMatch("5e59763368ec36619a66bfdc", "5e6ba423b657f9411f758eea");
		
		// -- CREATING AND DELETING NEW TEAMS -- 
//		dbHelper.createTeam("5e59763368ec36619a66bfdc", "Quick Boyes", "12345");
//		dbHelper.deleteTeam("5e59763368ec36619a66bfdc", "5e6ba667266a35632f569097");
		
		// -- CREATING AND DELETING NEW PLAYERS -- 
//		dbHelper.createPlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "Primp", "Doge");
//		dbHelper.createPlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "Fur", "Boye");
//		dbHelper.createPlayer("5e597b0b1b4ecc0001db20cc", "5e5d08bdfc189e00cf8ae12f", "Naomi", "Fluffington");
//		dbHelper.createPlayer("5e59763368ec36619a66bfdc", "5e6ba620833bc36df92f85b9", "Fraila", "Dogington");

//		dbHelper.deletePlayer("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e600d8688302978a1ed1e52");
		
		
		// -- CREATING AND DELETING NEW PLAYER STATSTICS --
//		dbHelper.createStatistic("5e59763368ec36619a66bfdc", "Farts borked", "0");

//		dbHelper.deleteStatistic("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e5fddfa4dabc675c9788718", "5e600ea9ca5c042a95d71db6");

		
//		dbHelper.printLeague("5e59763368ec36619a66bfdc");
		
//		dbHelper.printAllLeagues();

		
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
