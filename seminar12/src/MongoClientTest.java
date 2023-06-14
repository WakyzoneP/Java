import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoClientTest {

	public static void main(String[] args) {
		try {
			MongoClient objMongo = new MongoClient("127.0.0.1",27017);
			MongoDatabase db=objMongo.getDatabase("Nume");
			System.out.println("Connected!");
			
			//colectia este echivalentul tabelei
			if(db.getCollection("myCollection") != null) {
				db.getCollection("myCollection").drop();
			}else
				db.createCollection("myCollection");
			
			//document din org.bson, NU din swing
			MongoCollection<Document> myCol=db.getCollection("myCollection");
			Document tuple01 = new Document().append("TitlePost", "myArticle").append("Description", "How to connecto to mongo").append("likes","3").append("URL:", "https:...").append("by", "gigel");
			myCol.insertOne(tuple01);
			
			FindIterable<Document> iterableFind = myCol.find();
			MongoCursor<Document> cursor = iterableFind.iterator();
			int i =1;
			while(cursor.hasNext()) {
				
				System.out.println("Inserted doc "+i); 
				System.out.println(cursor.next());
				i++;
			}
			
			objMongo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
