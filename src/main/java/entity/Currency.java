package entity;

import java.util.HashMap;
import java.util.Map;

public class Currency {

    public Currency(CurrencyName currency, Double cost) {
        this.currencyName = currency;
        this.valueToRub = cost * money.get(currency.toString());
    }

    public enum CurrencyName {
        USD, EUR, RUB, UAH
    }
    public static Map<String, Double> money = Map.of("USD", 103.0, "EUR", 114.9, "RUB", 1.0, "UAH", 3.0);

    CurrencyName currencyName;
    double valueToRub;
}
