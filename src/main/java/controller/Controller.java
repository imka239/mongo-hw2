package controller;

import entity.Currency;
import entity.Item;
import entity.User;
import io.netty.handler.codec.http.HttpResponseStatus;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class Controller {
    ReactiveWork reactiveWork;

    public Controller(ReactiveWork reactiveWork) {
        this.reactiveWork = reactiveWork;
    }

    public Result process(String path, Map<String, List<String>> query) {
        if ("new-user".equals(path)) {
            return addUser(query);
        } else if ("new-item".equals(path)) {
            return addItem(query);
        } else if ("get-items".equals(path)) {
            return getItems(query);
        } else if ("clear".equals(path)) {
            return cleanAll();
        } else {
            throw new IllegalArgumentException("incorrect path" + path);
        }
    }

    private Result cleanAll() {
        reactiveWork.clear();
        return new Result(HttpResponseStatus.OK, reactiveWork.getItems("no-name"));
    }

    private Result getItems(Map<String, List<String>> query) {
        return new Result(HttpResponseStatus.OK, reactiveWork.getItems(query.get("user").get(0)));
    }

    private Result addItem(Map<String, List<String>> query) {
        Item item;
        if (query.get("id") != null) {
            item = new Item(
                    query.get("id").get(0),
                    query.get("name").get(0),
                    new Currency(Currency.CurrencyName.valueOf(query.get("currency").get(0)),
                            Double.parseDouble(query.get("cost").get(0)))
            );
        } else {
            item = new Item(
                    query.get("name").get(0),
                    new Currency(Currency.CurrencyName.valueOf(query.get("currency").get(0)),
                            Double.parseDouble(query.get("cost").get(0)))
            );
        }
        return new Result(HttpResponseStatus.OK, reactiveWork.addItem(item).map(Object::toString));
    }

    private Result addUser(Map<String, List<String>> query) {
        User user;
        if (query.get("id") != null) {
            user = new User(
                query.get("id").get(0),
                query.get("name").get(0),
                query.get("currency").get(0)
            );
        } else {
            user = new User(
                query.get("name").get(0),
                query.get("currency").get(0)
            );
        }
        return new Result(HttpResponseStatus.OK, reactiveWork.addUser(user).map(Object::toString));
    }
}
