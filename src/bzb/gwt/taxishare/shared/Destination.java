package bzb.gwt.taxishare.shared;

import com.google.gwt.json.client.JSONObject;

public class Destination {

	private int destinationId;
	private String name;
	private String postcode;
	
	public static Destination getDestination (JSONObject obj) {
		int destinationId = (int) obj.get("key").isObject().get("destinationID").isNumber().doubleValue();
		String name = obj.get("required").isObject().get("name").isString().stringValue();
		String postcode = obj.get("required").isObject().get("postcode").isString().stringValue();
		return new Destination(destinationId, name, postcode);
	}
	
	public Destination (int destinationId, String name, String postcode) {
		this.destinationId = destinationId;
		this.name = name;
		this.postcode = postcode;
	}
	
	/**
	 * @param destinationId the destinationId to set
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	/**
	 * @return the destinationId
	 */
	public int getDestinationId() {
		return destinationId;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	
}
