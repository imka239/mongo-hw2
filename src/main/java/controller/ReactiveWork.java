package controller;

import com.mongodb.client.model.Filters;
import com.mongodb.rx.client.MongoCollection;
import database.Server;
import entity.Currency;
import entity.Item;
import entity.User;
import org.bson.Document;
import rx.Observable;

public class ReactiveWork {
    private final MongoCollection<Document> users = Server.getAllUsers();
    private final MongoCollection<Document> items = Server.getAllItems();

    private Observable<Boolean> toCollection(
            Document doc,
            MongoCollection<Document> collection
    ) {
        var eq = Filters.eq(
                "name",
                doc.getString("name")
        );
        return collection.find(
                        eq
                ).toObservable()
                .singleOrDefault(null)
                .flatMap(foundDoc -> {
                    if (foundDoc == null) {
                        return collection.insertOne(doc)
                                .asObservable().isEmpty().map(x -> !x);
                    } else {
                        return Observable.just(false);
                    }
                });
    }

    public Observable<Boolean> addUser(User user) {
        return toCollection(User.toDocument(user), users);
    }

    public Observable<Boolean> addItem(Item item) {
        return toCollection(Item.toDocument(item), items);
    }

    public void clear() {
        users.deleteMany(new Document());
        items.deleteMany(new Document());
    }

    public Observable<String> getItems(String name) {
        var eq = Filters.eq(
                "name",
                name
        );
        return users
                .find(
                        eq
                ).toObservable()
                .map(user -> Currency.CurrencyName.valueOf(user.getString("currency")))
            .flatMap(currency -> items
                    .find()
                    .toObservable()
                    .map(it ->
                Item.fromDocument(it).changeCurrency(currency).toString()));
    }
}
