package bzb.gwt.taxishare.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
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
			
			DockPanel idPanel = new DockPanel();
			Label idLabel = new Label("TAXI" + String.valueOf(getRequestId() + 1));
			idLabel.addStyleName("idLabel");
			idPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
			idPanel.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
			idPanel.add(idLabel, DockPanel.CENTER);
			
			Panel spacesPanel;
			if (getSpaceLeft() == 0) {
				spacesPanel = new DockPanel();
				((DockPanel) spacesPanel).setHorizontalAlignment(DockPanel.ALIGN_CENTER);
				((DockPanel) spacesPanel).setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
				Label fullLabel = new Label("FULL");
				fullLabel.addStyleName("idLabel");
				((DockPanel) spacesPanel).add(fullLabel, DockPanel.CENTER);
			} else {
				spacesPanel = new VerticalPanel();
				spacesPanel.add(new Label(String.valueOf(getSpaceLeft()) + " seat(s) available"));
				FlowPanel spacesIcons = new FlowPanel();
				for (int i = 0; i < getTotalSpace() - getSpaceLeft(); i++) {
					if (getStatus().equals("unconfirmed")) {
						Image fadedIcon = new Image("freespace.png");
						fadedIcon.addStyleName("fadedIcon");
						spacesIcons.add(fadedIcon);
					} else {
						spacesIcons.add(new Image("freespace.png"));
					}
				}
				for (int i = 0; i < getSpaceLeft(); i++) {
					Image fadedIcon = new Image("freespace.png");
					if (getStatus().equals("unconfirmed")) {
						fadedIcon.addStyleName("veryFadedIcon");
					} else {
						fadedIcon.addStyleName("fadedIcon");
					}
					spacesIcons.add(fadedIcon);
				}
				spacesPanel.add(spacesIcons);
			}
			
			DockPanel destinationPanel = new DockPanel();
			Label destinationLabel = new Label(getDestinationName());
			destinationLabel.addStyleName("destinationNameLabel");
			destinationPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
			destinationPanel.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
			destinationPanel.add(destinationLabel, DockPanel.CENTER);
			
			VerticalPanel timePanel = new VerticalPanel();
			timePanel.add(new Label("Pickup:"));
			Label departureTimeLabel;
			if (getStatus().equals("unconfirmed")) {
				departureTimeLabel = new Label("unconfirmed");
			} else {
				departureTimeLabel = new Label(DateTimeFormat.getFormat("h:mm a").format(new Date(getPickupTime())));
				departureTimeLabel.addStyleName("departureTimeLabel");
			}
			timePanel.add(departureTimeLabel);
			timePanel.add(new Label("Journey:"));
			Label arrivalTimeLabel;
			if (getStatus().equals("unconfirmed")) {
				arrivalTimeLabel = new Label("unconfirmed");
			} else {
				arrivalTimeLabel = new Label((int)Math.round(((double)(getArrivalTime() - getPickupTime()) / 60.0)) + " mins");
				arrivalTimeLabel.addStyleName("arrivalTimeLabel");
			}		
			timePanel.add(arrivalTimeLabel);
			
			VerticalPanel farePanel = new VerticalPanel();
			farePanel.add(new Label("Current fare:"));
			String priceEach = String.valueOf(((double)getPredictedCost() / (double)(getTotalSpace() - getSpaceLeft())));
			if (priceEach.indexOf('.') > -1) {
				if (priceEach.length() < priceEach.indexOf('.') + 3) {
					priceEach += "0";
				} else {
					priceEach = priceEach.substring(0, priceEach.indexOf('.') + 3);
				}
			}
			Label priceLabel = new Label("\u00A3" + priceEach);
			priceLabel.addStyleName("fareLabel");
			farePanel.add(priceLabel);
			farePanel.add(new Label("each; \u00A3" + getPredictedCost() + " total (est.)"));
			
			VerticalPanel savingPanel = new VerticalPanel();
			savingPanel.add(new Label("Fare when full:"));
			String savingEach = String.valueOf((double)getPredictedCost() / (double)getTotalSpace());
			if (savingEach.indexOf('.') > -1) {
				if (savingEach.length() < savingEach.indexOf('.') + 3) {
					savingEach += "0";
				} else {
					savingEach = savingEach.substring(0, savingEach.indexOf('.') + 3);
				}
			}
			Label savingLabel = new Label("\u00A3" + savingEach);
			savingLabel.addStyleName("fareLabel");
			savingPanel.add(savingLabel);
			savingPanel.add(new Label("each (estimated)"));
			
			add(idPanel);
			add(spacesPanel);
			add(destinationPanel);
			add(timePanel);
			add(farePanel);
			add(savingPanel);
			
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
				getWidget(getWidgetCount() - 1).setStyleName("taxiBoxGreenLast");
				setStyleName("taxiPanel");
			}
		}
		
	}
	
}
