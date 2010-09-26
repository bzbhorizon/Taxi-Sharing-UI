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
		
		String testStatus = new String("{\"TaxiStatus\" : [");
		for (int i = 0; i < 3; i++) {
			testStatus += "{" +
			"\"key\" : {" +
			"\"requestID\" : " + i +
			"}," +
			"\"required\" : {" +
			"\"ownerID\" : 1," +
			"\"destinationName\" : \"Ben's house\"," +
			"\"destinationPostcode\" : \"ng162qa\"," +
			"\"requestTime\" : 1," +
			"\"arrivalTime\" : 1," +
			"\"status\" : \"unconfirmed\"" +
			"}," +
			"\"optional\" : {" +
			"\"company\" : \"dg\"," +
			"\"pickupTime\" : 1," +
			"\"predictedCost\" : 10," +
			"\"totalSpace\" : 6," +
			"\"spaceLeft\" : 0" +
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
		testStatus += "]}";
		
		return testStatus;
	}
}
