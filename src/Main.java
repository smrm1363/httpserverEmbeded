import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import domain.CustomerManager;
import infra.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        String payload = "duke";
        HttpServer server = HttpServer.create(new InetSocketAddress(4250), 0);
        Controller controller=Controller.getInstance();
        controller.setOnHttpServer(server);

        server.start();
        System.out.println("111");
    }
}
