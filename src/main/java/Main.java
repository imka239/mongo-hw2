
import controller.Controller;
import controller.ReactiveWork;
import io.reactivex.netty.protocol.http.server.HttpServer;

public class Main {
    public static void main(String[] args) {
        var controller = new Controller(new ReactiveWork());
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    var result = controller.process(req.getDecodedPath().substring(1), req.getQueryParameters());

                    resp.setStatus(result.getHttpStatus());
                    return resp.writeString(result.getMessage());
                })
                .awaitShutdown();
    }
}
