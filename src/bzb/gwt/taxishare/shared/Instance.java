package bzb.gwt.taxishare.shared;

import com.google.gwt.json.client.JSONObject;

public class Instance {

	private int id;
	private Destination destination;
	private String phone;
	
	public static Instance getInstance (JSONObject obj) {
		int id = (int) obj.get("key").isObject().get("instanceID").isNumber().doubleValue();
		int destinationId = (int) obj.get("required").isObject().get("destinationID").isNumber().doubleValue();
		String phone = obj.get("required").isObject().get("phone").isString().stringValue();
		String name = obj.get("required").isObject().get("name").isString().stringValue();
		String postcode = obj.get("required").isObject().get("postcode").isString().stringValue();
		
		return new Instance(id, new Destination(destinationId, name, postcode), phone);
	}
	
	public Instance (int id, Destination destination, String phone) {
		this.id = id;
		this.destination = destination;
		this.phone = phone;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	/**
	 * @return the destination
	 */
	public Destination getDestination() {
		return destination;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	
}
