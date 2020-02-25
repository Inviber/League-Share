import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.simple.JSONObject;



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
	
	public Document createDocument()
	{
		return new Document();
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
		 * User
		 * 		- _id
		 * 
		 * 		- username
		 * 		- password
		 * 		- firstName
		 * 		- lastName
		 * 		
		 * 		- followedLeagues[]
		 * 		- ownedLeagues[]
		 * 		- ownedTeams[]
		 * 
		 */
		
		
		/*
		 * League
		 * 		- _id
		 * 
		 * 		- name
		 * 		- description
		 * 		- zipcode
		 * 		- adminIDs[]
		 *		
		 * 		- teams[]
		 *			- teamName
		 *			- zipcode
		 *			- casterIDs[]
		 *			- players[]
		 *				- stats[]
		 *			- matchDates
		 * 
		 */
		
		
		
		//CREATING NEW USER
		Document newUserDocument = dbHelper.createDocument();
		
		newUserDocument.put("username", "grasseater421");
		newUserDocument.put("password", "eatbeans");
		newUserDocument.put("firstName", "Tot");
		newUserDocument.put("lastName", "The Cat");
		
		
		ArrayList<String> followedLeagueIDs = new ArrayList<String>();
		followedLeagueIDs.add("029384oe098hi234");
		followedLeagueIDs.add("203948aod234oeduh2234");
		
		newUserDocument.put("followedLeagueIDs", followedLeagueIDs);
		
		
		dbHelper.addDocument("Users", newUserDocument);
		System.out.println(newUserDocument.toString());
			
			
		
//		newDocument.forEach
		
		
//		collection.insertOne(newDocument);

//		//collection to be iterated over
//		FindIterable<Document> iterDoc = collection.find(); 
//
//		MongoCursor<Document> it = iterDoc.iterator(); 
//
//		while(it.hasNext())
//		{  
//			System.out.println(it.next());  
//		}

		
		
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}

}
