package bzb.gwt.taxishare.shared;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class Destination {

	private int destinationId;
	private String name;
	private String postcode;
	
	public static Destination getDestination (JSONObject obj) {
		Destination d = new Destination();
		JSONString objS = obj.get("required").isObject().get("name").isString();
		String name = objS.stringValue();
		d.setName(name);
		return d;
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
