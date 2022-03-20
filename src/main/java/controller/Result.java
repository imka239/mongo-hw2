package controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import rx.Observable;

public class Result {
    final HttpResponseStatus httpStatus;
    final Observable<String> message;

    public Result(HttpResponseStatus httpStatus, Observable<String> message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpResponseStatus getHttpStatus() {
        return httpStatus;
    }

    public Observable<String> getMessage() {
        return message;
    }
}
