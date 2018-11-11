package infra;

import domain.BetManager;
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

    public RequestHandlerStrategy getRequestHandlerStrategy(String url,String body,String quersyParameters)
    {

        String[] strings = url.split("/");

        if(strings[2].equalsIgnoreCase(urlSession))
            return httpExchange -> {
                CustomerManager customerManager = CustomerManager.getInstance();
                String response = customerManager.createCustomer(Integer.valueOf(strings[1]));
                httpExchange.sendResponseHeaders(200, response.length());
                return response;
            };
        else if(strings[2].equalsIgnoreCase(urlStack))
            return httpExchange -> {
//            if(strings[2].contains(urlStack))
//            {
                String sessionKey = quersyParameters.replace("sessionkey=","");
                BetManager betManager = BetManager.getInstance();
                betManager.createBet(sessionKey,Integer.valueOf(strings[1]),Integer.valueOf(body));
                httpExchange.sendResponseHeaders(200,0);

//            }
            return "";
            };
        else if(strings[2].equalsIgnoreCase(urlHighstakes))
            return httpExchange -> {
                BetManager betManager = BetManager.getInstance();
                String response = betManager.getHighestStack(Integer.valueOf(strings[1]));
                httpExchange.sendResponseHeaders(200, response.length());
                return response;
            };
        return null;
    }
}
