package entity;

import org.bson.Document;

import static entity.Currency.money;

public class Item {
    public static Item fromDocument(final Document document) {
        return new Item(
                document.getString("name"),
                new Currency(
                        Currency.CurrencyName.valueOf(document.getString("currency")),
                            Double.parseDouble(document.getString("cost"))
                )
        );
    }

    public String id;
    public final String name;
    public final Currency currency;


    public Item(Document doc) {
        this(doc.getString("id"), doc.getString("name"), new Currency(
                Currency.CurrencyName.valueOf(doc.getString("currency")),
                Double.parseDouble(doc.getString("cost"))
        ));
    }

    public Item(String id, String name, Currency currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public Item(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency='" + currency.currencyName + '\'' +
                ", cost='" + currency.valueToRub + '\'' +
                '}';
    }

    public static Document toDocument(final Item item) {
        final Document document = new Document();
        if (item.id != null) {
            document.append("id", item.id);
        }
        document.append("name", item.name);
        document.append("currency", item.currency.currencyName.toString());
        document.append("cost", String.valueOf(item.currency.valueToRub));
        return document;
    }

    public Item changeCurrency(Currency.CurrencyName currency) {
        return new Item(this.name, new Currency(currency, this.currency.valueToRub / money.get(currency.toString())));
    }
}
