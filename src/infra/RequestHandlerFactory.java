package infra;

import domain.CustomerManager;

public class RequestHandlerFactory {
    private final String urlSession = "session";
    private final String urlStack = "stake";
    private final String urlHighstakes = "highstakes";

    private static RequestHandlerFactory requestHandlerFactory;

    private RequestHandlerFactory() {

    }
    public static RequestHandlerFactory getInstance()
    {
        if(requestHandlerFactory == null)
            requestHandlerFactory = new RequestHandlerFactory();
        return requestHandlerFactory;
    }

    public RequestHandlerStrategy getRequestHandlerStrategy(String url)
    {

        String[] strings = url.split("/");

        if(strings[2].contains(urlSession))
            return httpExchange -> {
                CustomerManager customerManager = CustomerManager.getInstance();
                String response = customerManager.createCustomer(Integer.valueOf(strings[1]));
                httpExchange.sendResponseHeaders(200, response.length());
                return response;
            };
        else if(strings[2].contains(urlStack))
            return httpExchange -> {
            return null;
            };
        else if(strings[2].contains(urlHighstakes))
            return httpExchange -> {
            return null;
            };
        return null;
    }
}
