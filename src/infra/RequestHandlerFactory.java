package infra;

import domain.BetManager;
import domain.CustomerManager;

/**
 * This is a Factory of RequestHandlerStrategy. We have 3 different strategies in Lambdas
 */
public class RequestHandlerFactory {
    private final String urlSession = "session";
    private final String urlStack = "stake";
    private final String urlHighstakes = "highstakes";

    private static RequestHandlerFactory requestHandlerFactory;

    private RequestHandlerFactory() {

    }

    /**
     *
     * @return a singleton instance of RequestHandlerFactory
     */
    public static RequestHandlerFactory getInstance()
    {
        if(requestHandlerFactory == null)
            requestHandlerFactory = new RequestHandlerFactory();
        return requestHandlerFactory;
    }

    /**
     * It makes a true strategy dou to the Request
     * @param url is the URL of the HTTP request
     * @param body  is the body of Post Requests
     * @param quersyParameters is the parameters of the Request
     * @return a true strategy
     */
    public RequestHandlerStrategy getRequestHandlerStrategy(String url,String body,String quersyParameters)
    {

        String[] strings = url.split("/");
        /**
         * It makes when a GET /<customerid>/session is called
         */
        if(strings[2].equalsIgnoreCase(urlSession))
            return httpExchange -> {
                CustomerManager customerManager = CustomerManager.getInstance();
                String response = customerManager.createCustomer(Integer.valueOf(strings[1]));
                httpExchange.sendResponseHeaders(200, response.length());
                return response;
            };

        /**
         * It makes when a POST /<betofferid>/stake?sessionkey=<sessionkey> is called
         */
        else if(strings[2].equalsIgnoreCase(urlStack))
            return httpExchange -> {

                String sessionKey = quersyParameters.replace("sessionkey=","");
                BetManager betManager = BetManager.getInstance();
                betManager.createBet(sessionKey,Integer.valueOf(strings[1]),Integer.valueOf(body));
                httpExchange.sendResponseHeaders(200,0);


            return "";
            };

        /**
         * It makes when a GET /<betofferid>/highstakes is called
         */
        else if(strings[2].equalsIgnoreCase(urlHighstakes))
            return httpExchange -> {
                BetManager betManager = BetManager.getInstance();
                String response = betManager.getHighestStack(Integer.valueOf(strings[1]));
                httpExchange.sendResponseHeaders(200, response.length());
                return response;
            };
        /**
         * When no strategy found
         */
        return null;
    }
}
