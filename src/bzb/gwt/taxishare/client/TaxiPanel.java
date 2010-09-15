package bzb.gwt.taxishare.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TaxiPanel extends HorizontalPanel {
	
	public TaxiPanel (String JSON) {	
		setStyleName("taxiPanel");
		setWidth((Window.getClientWidth() - 40) + "px");
		
		VerticalPanel idPanel = new VerticalPanel();
		idPanel.setStyleName("taxiBox");
		idPanel.add(new Label("Taxi"));
		idPanel.add(new Label("1"));
		
		VerticalPanel spacesPanel = new VerticalPanel();
		spacesPanel.setStyleName("taxiBox");
		spacesPanel.add(new Label("1 SPACE"));
		spacesPanel.add(new Label("stickmen"));
		
		Label destination = new Label("destination");
		destination.setStyleName("taxiBox");
		
		VerticalPanel timePanel = new VerticalPanel();
		timePanel.setStyleName("taxiBox");
		timePanel.add(new Label("Leaving: blah"));
		timePanel.add(new Label("ETA: blah"));
		
		VerticalPanel farePanel = new VerticalPanel();
		farePanel.setStyleName("taxiBoxLast");
		farePanel.add(new Label("FARE APPROX."));
		farePanel.add(new Label("£2.50 each"));
		
		add(idPanel);
		add(spacesPanel);
		add(destination);
		add(timePanel);
		add(farePanel);
	}
	
}
