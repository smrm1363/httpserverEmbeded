package infra;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

@FunctionalInterface
public interface RequestHandlerStrategy {
    String handleRequest(HttpExchange httpExchange) throws Exception;
}
