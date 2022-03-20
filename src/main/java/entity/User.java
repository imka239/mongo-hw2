package entity;

import org.bson.Document;

public class User {
    public String id;
    public final String name;
    public final Currency.CurrencyName currency;


    public User(Document doc) {
        this(doc.getString("id"), doc.getString("name"), doc.getString("currency"));
    }

    public User(String id, String name, String currency) {
        this.id = id;
        this.name = name;
        this.currency = Currency.CurrencyName.valueOf(currency);
    }

    public User(String name, String currency) {
        this.name = name;
        this.currency = Currency.CurrencyName.valueOf(currency);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency'" + currency + '\'' +
                '}';
    }

    public static Document toDocument(final User user) {
        final Document document = new Document();
        if (user.id != null) {
            document.append("id", user.id);
        }
        document.append("name", user.name);
        document.append("currency", user.currency.toString());
        return document;
    }
}