package infra;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.stream.Collectors;

/**
 * This is our controller. This is a Singleton class
 */
public class Controller {
    private static Controller controller;

    private Controller() {

    }

    /**
     *
     * @return a singleton instance of Controller
     */
    public static Controller getInstance()
    {
        if(controller == null)
            controller = new Controller();
        return controller;
    }
    public void setOnHttpServer(HttpServer server)
    {

        HttpContext context = server.createContext("/");
        context.setHandler((he) -> {

            InputStream inputStream = he.getRequestBody();
            String readedBody = null;
            /**
             * Getting the body if it is existed
             */
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
                readedBody= buffer.lines().collect(Collectors.joining("\n"));
            }

            /**
             * Getting an instance of RequestHandlerStrategy from RequestHandlerFactory
             */
            RequestHandlerStrategy requestHandlerStrategy =
                    RequestHandlerFactory.getInstance().getRequestHandlerStrategy(he.getRequestURI().getPath(),readedBody,he.getRequestURI().getQuery());

            String returnedString = null;
             OutputStream output = he.getResponseBody();
            try {
                returnedString = requestHandlerStrategy.handleRequest(he);
            } catch (Exception e) {
                /**
                 * Returning error message
                 */
                e.printStackTrace();
                he.sendResponseHeaders(400,e.getMessage().length());
                returnedString = e.getMessage();
            }
            finally {
                output.write(returnedString.getBytes());
                output.flush();
                he.close();
            }

        });
    }
}
