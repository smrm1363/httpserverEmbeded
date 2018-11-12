**Pure Java Beting test project**

In this small project I used Java 8 without any Framework.
The project starts from Main class, it starts an embedded HTTP serve. After running the program, bellow services works:

1- Request: GET /_customerid_/session
   
   Response: _sessionkey_
   
   Example : http://localhost:8001/1234/session --> 623ab4d2-f803-4b29-98a2-f1d558d07a96
   
  When this service called first time, a **SessionManager** starts. In the SessionManager, we have runRemoveExpiredSessions(), which Runs an infinitive Thread for removing timed out sessions.
   
   2- Request: POST /_betofferid_/stake?sessionkey=_sessionkey_
   
   Request body: _stake_
   
   Response: (empty)
   
   Example: http://localhost:8001/520/stake?sessionkey=5e0ad81f-352c-4256-9972-96aaacf970d1
   
   Body: 400
   
   3- Request: GET /_betofferid_/highstakes
 
   Response: CSV of _customerid_=_stake_
   
   Example: http://localhost:8001/1234/highstakes
   
   Response: 1=400,2=200
   
   Generally I used ConcurrentHashMap when I need Thread Safe scenario. In my source code I wrote some comments to show the way of my thinking. Meanwhile, I used some well known design patterns such as Singleton, Factory, and Strategy.
   
   Dou to we need only 20 top offers per each bet, I only keep the 20 for reducing memory usage.
   
   Nevertheless, as it is mentioned in the use case, I only support the main functionality, thus some exceptions may be happened in other scenarios.
   
   If there are any question about my solutions, I am ready to answer.
   
   I am thankful for your concentration.