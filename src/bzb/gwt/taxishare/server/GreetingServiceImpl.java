package bzb.gwt.taxishare.server;

import bzb.gwt.taxishare.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	public String greetServer(String input) {
		// order taxistatus by arrival time
		// only pass on taxistatus unconfirmed | confirmed, not left
		
		String testStatus = new String("{\"TaxiStatus\" : [");
		for (int i = 0; i < 7; i++) {
			testStatus += "{" +
			"\"key\" : {" +
			"\"requestID\" : " + i +
			"}," +
			"\"required\" : {" +
			"\"ownerID\" : 1," +
			"\"destinationName\" : \"Ben's house\"," +
			"\"destinationPostcode\" : \"ng162qa\"," +
			"\"requestTime\" : 1," +
			"\"arrivalTime\" : 1000000," +
			"\"status\" : \"confirmed\"" +
			"}," +
			"\"optional\" : {" +
			"\"company\" : \"dg\"," +
			"\"pickupTime\" : 1," +
			"\"predictedCost\" : 15," +
			"\"totalSpace\" : 4," +
			"\"spaceLeft\" : 3" +
			"}" +
			"},";
		}
		testStatus += "], \"Destination\" : [";
		for (int i = 0; i < 10; i++) {
			testStatus += "{" +
			"\"key\" : {" +
			"\"destinationID\" : " + i +
			"}," +
			"\"required\" : {" +
			"\"name\" : \"dest" + i + "\"," +
			"\"postcode\" : \"NG162QA\"}},";
		}
		testStatus += "], \"Instance\" : {" +
			"\"key\" : {" +
			"\"instanceID\" : " + input + 
			"}," +
			"\"required\" : {" +
			"\"destinationID\" : " + 1 + "," +
			"\"name\" : \"emcc\"," +
			"\"phone\" : \"07786202578\"," +
			"\"postcode\" : \"ng72rj\"}}}";
		
		return testStatus;
	}
}
