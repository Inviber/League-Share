import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//import com.mongodb.MongoCredential;  


public class DatabaseExploration {

	public static void main(String[] args) {
		

		MongoClientURI uri = new MongoClientURI("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority");
		
		MongoClient mongoClient = new MongoClient(uri);
		
		//database name -- will be 'LeagueShare'
		MongoDatabase database = mongoClient.getDatabase("test");
		
		MongoCollection<Document> collection = database.getCollection("stores"); 


		System.out.println("Test");
		
		FindIterable<Document> iterDoc = collection.find(); 
//	      int i = 1; 

	      // Getting the iterator 
	      MongoCursor<Document> it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
//	      i++; 
	      }
		
		
		
		mongoClient.close();
	}

}
