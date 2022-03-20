package database;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.MongoDatabase;
import entity.Item;
import entity.User;
import org.bson.Document;
import rx.Observable;

public class Server {

    private static final MongoDatabase database = createMongoClient()
            .getDatabase("webCatalog");

    public static MongoCollection<Document> getAllUsers() {
        MongoCollection<Document> answer = database.getCollection("users");
        Observable<User> users = answer.find().toObservable().map(User::new);
        users.subscribe(Server::getPrintln);
        return answer;
    }


    public static MongoCollection<Document> getAllItems() {
        MongoCollection<Document> answer = database.getCollection("items");
        Observable<Item> items = answer.find().toObservable().map(Item::new);
        items.subscribe(Server::getPrintln);
        return answer;
    }

    private static void getPrintln(User user) {
        System.out.println(user);
    }

    private static void getPrintln(Item item) {
        System.out.println(item);
    }

    private static MongoClient createMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
