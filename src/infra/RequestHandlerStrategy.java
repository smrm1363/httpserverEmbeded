package infra;


import com.sun.net.httpserver.HttpExchange;

/**
 * This is a Strategy pattern interface for handling requests in server
 */
@FunctionalInterface
public interface RequestHandlerStrategy {
    String handleRequest(HttpExchange httpExchange) throws Exception;
}
