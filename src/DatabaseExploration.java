import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class DatabaseExploration {

	public static void main(String[] args) {
		
		
		//unique string that is used to access the database Alex made on a cluster on mongodb.com
		MongoClientURI uri = new MongoClientURI("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority");
		
		//initializing mongoDB connection
		MongoClient mongoClient = new MongoClient(uri);
		
		//database name -- will be 'LeagueShare'
		MongoDatabase database = mongoClient.getDatabase("test");
		
		//collections -- will be Users, Leagues, Teams
		MongoCollection<Document> collection = database.getCollection("stores"); 

		//collection to be iterated over
		FindIterable<Document> iterDoc = collection.find(); 

		MongoCursor<Document> it = iterDoc.iterator(); 

		while(it.hasNext())
		{  
			System.out.println(it.next());  
		}
		
		
		//shutting down mongoDB connection
		mongoClient.close();
	}

}
