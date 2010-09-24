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
		String testStatus = new String(
				"{\"TaxiStatus\" : [{" +
			"\"key\" : {" +
			"\"requestID\" : 1" +
			"}," +
			"\"required\" : {" +
			"\"ownerID\" : \"string\"," +
			"\"destination\" : \"string\"," +
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
			"}]}"
		);
		return testStatus;
	}
}
