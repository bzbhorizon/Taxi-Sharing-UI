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
		String testStatus = new String("{\"TaxiStatus\" : [");
		for (int i = 0; i < 7; i++) {
			testStatus += "{" +
			"\"key\" : {" +
			"\"requestID\" : " + i +
			"}," +
			"\"required\" : {" +
			"\"ownerID\" : \"string\"," +
			"\"destinationID\" : \"1\"," +
			"\"requestTime\" : 1," +
			"\"arrivalTime\" : 1," +
			"\"status\" : \"string\"" +
			"}," +
			"\"optional\" : {" +
			"\"company\" : \"string\"," +
			"\"pickupTime\" : 1," +
			"\"predictedCost\" : 1," +
			"\"spaceLeft\" : \"number\"" +
			"}" +
			"},";
		}
		testStatus += "], \"Destination\" : [";
		for (int i = 0; i < 30; i++) {
			testStatus += "{" +
			"\"key\" : {" +
			"\"destinationID\" : " + i +
			"}," +
			"\"required\" : {" +
			"\"name\" : \"dest" + i + "\"," +
			"\"postcode\" : \"string\"}},";
		}
		testStatus += "]}";
		
		return testStatus;
	}
}
