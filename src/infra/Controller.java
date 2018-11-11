package infra;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import domain.CustomerManager;
import jdk.Exported;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class Controller {
    private static Controller controller;

    private Controller() {

    }

    public static Controller getInstance()
    {
        if(controller == null)
            controller = new Controller();
        return controller;
    }
    public void setOnHttpServer(HttpServer server)
    {
        String payload = "duke";
        HttpContext context = server.createContext("/");
        context.setHandler((he) -> {
//            String[] strings = he.getRequestURI().getPath().split("/");

//            RequestHandlerStrategy requestHandlerStrategy = httpExchange -> {
//                httpExchange.sendResponseHeaders(200, 6);
//                return "OOOmad";
//            };
            InputStream inputStream = he.getRequestBody();
            String readedBody = null;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
                readedBody= buffer.lines().collect(Collectors.joining("\n"));
            }
            RequestHandlerStrategy requestHandlerStrategy =
                    RequestHandlerFactory.getInstance().getRequestHandlerStrategy(he.getRequestURI().getPath(),readedBody,he.getRequestURI().getQuery());

//            final OutputStream output = he.getResponseBody();
            String returnedString = null;
            try {
                returnedString = requestHandlerStrategy.handleRequest(he);
            } catch (Exception e) {
                e.printStackTrace();
            }
            final OutputStream output = he.getResponseBody();

            output.write(returnedString.getBytes());
            output.flush();
            he.close();
//            CustomerManager customerManager = CustomerManager.getInstance();
//
//            customerManager.createCustomer(Integer.valueOf(strings[strings.length-1]));

        });
    }
}
