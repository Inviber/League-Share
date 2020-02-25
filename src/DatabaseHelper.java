import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;


public class DatabaseHelper {
	
	private MongoClientURI uri; // (URI) is a string of characters that unambiguously identifies a particular resource.
	private MongoClient mongoClient;
	private MongoDatabase database;
	
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
	
	public MongoCollection<Document> getCollection(String collectionName)
	{
		//collections -- will be Users and Leagues
		return this.database.getCollection(collectionName);
	}
	
	public void addDocument(String collectionName, Document newDocument)
	{
		this.database.getCollection(collectionName).insertOne(newDocument);
	}
	
	public Document getDocument(String collectionName, String uniqueID)
	{
		System.out.println("Retrieving document...");
		
		return this.database.getCollection(collectionName).find(eq("_id", new ObjectId(uniqueID))).first();
	}
	
	public Document createDocument()
	{
		return new Document();
	}
	
	public String createNewUser() 
	{
		//return unique ID
		return "";
	}
	
	
	
	
	

	public static void main(String[] args)
	{
		DatabaseHelper dbHelper = new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
		
//		Do not need to uncomment this because the collections already exist on the database
//		dbHelper.createCollection("Users");
//		dbHelper.createCollection("Leagues");
		
		
		MongoCollection<Document> users = dbHelper.getCollection("Users");
		MongoCollection<Document> leagues = dbHelper.getCollection("Leagues");
		
		/*
		 * User Document
		 * 		- _id
		 * 
		 * 		- username
		 * 		- password
		 * 		- firstName
		 * 		- lastName
		 * 		
		 * 		- followedLeagues[] string
		 * 		- followedTeams[] string
		 * 
		 * 		- ownedLeagues[] string
		 * 		- ownedTeams[] string
		 * 
		 * 		- teamsManaged[] string
		 * 		- leaguesCasted[] string
		 */
		
		
		/*
		 * League Document
		 * 		- _id
		 * 
		 * 		- name
		 * 		- ownerID
		 * 		- description
		 *		 
		 * 		- casterIDs[]
		 *		
		 * 		- teams[]
		 *			- teamName
		 *			- zipcode
		 *			- players[]
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
		
		
		
		//CREATING NEW USER
		Document newUserDocument = dbHelper.createDocument();
		
		newUserDocument.put("username", "grasseater421");
		newUserDocument.put("password", "eatbeans");
		newUserDocument.put("firstName", "Tot");
		newUserDocument.put("lastName", "The Cat");
		
		
		ArrayList<String> followedLeagueIDs = new ArrayList<String>();
		ArrayList<String> followedTeamIDs = new ArrayList<String>();
		ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
		ArrayList<String> ownedTeamIDs = new ArrayList<String>();
		ArrayList<String> managedTeamIDs = new ArrayList<String>();
		ArrayList<String> leaguesCastedIDs = new ArrayList<String>();
		
		
		followedLeagueIDs.add("029384oe098hi234");
		followedLeagueIDs.add("203948aod234oeduh2234");
		followedLeagueIDs.add("ai7u7e6d9e7u6e7eui43567");
		
		
		newUserDocument.put("followedLeagueIDs", followedLeagueIDs);
		newUserDocument.put("followedTeamIDs", followedTeamIDs);
		newUserDocument.put("ownedLeagueIDs", ownedLeagueIDs);
		newUserDocument.put("ownedTeamIDs", ownedTeamIDs);
		newUserDocument.put("managedTeamIDs", managedTeamIDs);
		newUserDocument.put("leaguesCastedIDs", leaguesCastedIDs);
					

		//adding new user
//		dbHelper.addDocument("Users", newUserDocument);
//		//printing new user
//		System.out.println(newUserDocument.toString());
//		//example of how to get the value by using the key
//		System.out.println(newUserDocument.get("username"));
//		//example of how to get the users unique id
//		System.out.println(newUserDocument.get("_id"));
		
		Document searchedDocument = dbHelper.getDocument("Users", "5e558fa2db3ad400422015af"); 
		
		System.out.println(searchedDocument.toJson());
	
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}
}
