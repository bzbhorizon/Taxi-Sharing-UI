package bzb.gwt.taxishare.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TaxiStatus {

	// key
	private int requestId;
	
	// required
	private int ownerId;
	private String destinationName;
	private String destinationPostcode;
	private long requestTime;
	private long arrivalTime;
	private String status;
	
	// optional
	private String company;
	private long pickupTime;
	private int predictedCost;
	private int totalSpace;
	private int spaceLeft;
	
	public static TaxiStatus getTaxiStatus (JSONObject obj) {
		TaxiStatus ts = new TaxiStatus();
		ts.setRequestId((int) obj.get("key").isObject().get("requestID").isNumber().doubleValue());
		ts.setOwnerId((int) obj.get("required").isObject().get("ownerID").isNumber().doubleValue());
		ts.setDestinationName(obj.get("required").isObject().get("destinationName").isString().stringValue());
		ts.setDestinationPostcode(obj.get("required").isObject().get("destinationPostcode").isString().stringValue());
		ts.setRequestTime((long) obj.get("required").isObject().get("requestTime").isNumber().doubleValue());
		ts.setArrivalTime((long) obj.get("required").isObject().get("arrivalTime").isNumber().doubleValue());
		ts.setStatus(obj.get("required").isObject().get("status").isString().stringValue());
		ts.setCompany(obj.get("optional").isObject().get("company").isString().stringValue());
		ts.setPickupTime((long) obj.get("optional").isObject().get("pickupTime").isNumber().doubleValue());
		ts.setPredictedCost((int) obj.get("optional").isObject().get("predictedCost").isNumber().doubleValue());
		ts.setTotalSpace((int) obj.get("optional").isObject().get("totalSpace").isNumber().doubleValue());
		ts.setSpaceLeft((int) obj.get("optional").isObject().get("spaceLeft").isNumber().doubleValue());
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
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
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
	
	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}

	/**
	 * @param destinationPostcode the destinationPostcode to set
	 */
	public void setDestinationPostcode(String destinationPostcode) {
		this.destinationPostcode = destinationPostcode;
	}

	/**
	 * @return the destinationPostcode
	 */
	public String getDestinationPostcode() {
		return destinationPostcode;
	}

	/**
	 * @param totalSpace the totalSpace to set
	 */
	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}

	/**
	 * @return the totalSpace
	 */
	public int getTotalSpace() {
		return totalSpace;
	}

	public class TaxiPanel extends HorizontalPanel {
		
		public TaxiPanel () {
			setWidth((Window.getClientWidth() - 40) + "px");
			
			VerticalPanel idPanel = new VerticalPanel();
			idPanel.add(new Label("ID:"));
			idPanel.add(new Label(String.valueOf(getRequestId())));
			
			HorizontalPanel spacesPanel = new HorizontalPanel();
			spacesPanel.add(new Label(String.valueOf(getSpaceLeft())));
			VerticalPanel spacesSmallTextPanel = new VerticalPanel();
			spacesSmallTextPanel.add(new Label("SPACES"));
			spacesSmallTextPanel.add(new Label("REMAINING"));
			spacesPanel.add(spacesSmallTextPanel);
			
			Label destination = new Label(getDestinationName());
			
			VerticalPanel timePanel = new VerticalPanel();
			timePanel.add(new Label("Departure: " + DateTimeFormat.getFormat("h:mm a").format(new Date(getPickupTime()))));
			timePanel.add(new Label("ETA: " + DateTimeFormat.getFormat("h:mm a").format(new Date(getArrivalTime()))));
			
			VerticalPanel farePanel = new VerticalPanel();
			farePanel.add(new Label("FARE APPROX.:"));
			HorizontalPanel farePriceTextPanel = new HorizontalPanel();
			String priceEach = String.valueOf(((double)getPredictedCost() / (double)(getTotalSpace() - getSpaceLeft())));
			if (priceEach.length() > priceEach.indexOf('.') + 2) {
				priceEach = priceEach.substring(0, priceEach.indexOf('.') + 3);
			} else {
				priceEach += "0";
			}
			farePriceTextPanel.add(new Label("\u00A3" + priceEach));
			farePriceTextPanel.add(new Label("each"));
			farePanel.add(farePriceTextPanel);
			
			add(idPanel);
			add(spacesPanel);
			add(destination);
			add(timePanel);
			add(farePanel);
			
			if (getStatus().equals("unconfirmed")) {
				for (int i = 0; i < getWidgetCount() - 1; i++) {
					getWidget(i).setStyleName("taxiBoxUnconfirmed");
				}
				getWidget(getWidgetCount() - 1).setStyleName("taxiBoxUnconfirmedLast");
				setStyleName("taxiPanelUnconfirmed");
			} else if (getSpaceLeft() == 0) {
				for (int i = 0; i < getWidgetCount() - 1; i++) {
					getWidget(i).setStyleName("taxiBoxFull");
				}
				getWidget(getWidgetCount() - 1).setStyleName("taxiBoxFullLast");
				setStyleName("taxiPanelFull");
			} else {
				for (int i = 0; i < getWidgetCount() - 1; i++) {
					getWidget(i).setStyleName("taxiBox");
				}
				getWidget(getWidgetCount() - 1).setStyleName("taxiBoxLast");
				setStyleName("taxiPanel");
			}
		}
		
	}
	
}
