package bzb.gwt.taxishare.shared;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TaxiStatus {

	// key
	private int requestId;
	
	// required
	private String ownerId;
	private String destination;
	private long requestTime;
	private long arrivalTime;
	private String status;
	
	// optional
	private String company;
	private long pickupTime;
	private int predictedCost;
	private int spaceLeft;
	
	public static TaxiStatus getTaxiStatus (JSONObject obj) {
		TaxiStatus ts = new TaxiStatus();
		return ts;
	}
	
	public TaxiPanel getPanel () {
		TaxiPanel tp = new TaxiPanel();
		return tp;
	}
	
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	/**
	 * @return the requestTime
	 */
	public long getRequestTime() {
		return requestTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the arrivalTime
	 */
	public long getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param predictedCost the predictedCost to set
	 */
	public void setPredictedCost(int predictedCost) {
		this.predictedCost = predictedCost;
	}
	/**
	 * @return the predictedCost
	 */
	public int getPredictedCost() {
		return predictedCost;
	}
	/**
	 * @param pickupTime the pickupTime to set
	 */
	public void setPickupTime(long pickupTime) {
		this.pickupTime = pickupTime;
	}
	/**
	 * @return the pickupTime
	 */
	public long getPickupTime() {
		return pickupTime;
	}
	/**
	 * @param spaceLeft the spaceLeft to set
	 */
	public void setSpaceLeft(int spaceLeft) {
		this.spaceLeft = spaceLeft;
	}
	/**
	 * @return the spaceLeft
	 */
	public int getSpaceLeft() {
		return spaceLeft;
	}
	
	public class TaxiPanel extends HorizontalPanel {
		
		public TaxiPanel () {	
			setStyleName("taxiPanel");
			setWidth((Window.getClientWidth() - 40) + "px");
			
			VerticalPanel idPanel = new VerticalPanel();
			idPanel.setStyleName("taxiBox");
			idPanel.add(new Label("ID:"));
			idPanel.add(new Label("1"));
			
			HorizontalPanel spacesPanel = new HorizontalPanel();
			spacesPanel.setStyleName("taxiBox");
			spacesPanel.add(new Label("1"));
			VerticalPanel spacesSmallTextPanel = new VerticalPanel();
			spacesSmallTextPanel.add(new Label("SPACE"));
			spacesSmallTextPanel.add(new Label("REMAINING"));
			spacesPanel.add(spacesSmallTextPanel);
			
			Label destination = new Label("blah");
			destination.setStyleName("taxiBox");
			
			VerticalPanel timePanel = new VerticalPanel();
			timePanel.setStyleName("taxiBox");
			timePanel.add(new Label("Departure: blah"));
			timePanel.add(new Label("ETA: blah"));
			
			VerticalPanel farePanel = new VerticalPanel();
			farePanel.setStyleName("taxiBoxLast");
			farePanel.add(new Label("FARE APPROX.:"));
			HorizontalPanel farePriceTextPanel = new HorizontalPanel();
			farePriceTextPanel.add(new Label("£2.50"));
			farePriceTextPanel.add(new Label("each"));
			farePanel.add(farePriceTextPanel);
			
			add(idPanel);
			add(spacesPanel);
			add(destination);
			add(timePanel);
			add(farePanel);
		}
		
	}
	
}
