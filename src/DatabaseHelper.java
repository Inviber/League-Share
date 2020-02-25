import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.bson.Document;



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
		//collections -- will be Users, Leagues, Teams, Players
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
//		dbHelper.createCollection("Teams");
//		dbHelper.createCollection("Players");
		
		
		
//		MongoCollection<Document> collection = dbHelper.getCollection("Users");
		
		Document newDocument = dbHelper.createDocument();
		
		newDocument.append("cats", 100);
		newDocument.put("smiles", 200);
			
		//adding newDocument to the Users collection
		dbHelper.addDocument("Users", newDocument);
		
			
		
		
		System.out.println(newDocument);
		
//		newDocument.forEach
//		
		
		
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
//		
//		
//		

		
		
		//shutting down mongoDB connection
		dbHelper.getClient().close();
	}

}
