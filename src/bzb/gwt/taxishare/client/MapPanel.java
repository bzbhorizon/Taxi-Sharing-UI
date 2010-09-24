package bzb.gwt.taxishare.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MapPanel extends VerticalPanel {

	public MapPanel () {
		setWidth((Window.getClientWidth() - 40) + "px");
		setStyleName("mapPanel");
		Maps.loadMapsApi("", "2", false, new Runnable() {
		      public void run() {
		    	    LatLng currentLocation = LatLng.newInstance(52.939138, -1.203244);

		    	    final MapWidget map = new MapWidget(currentLocation, 12);
		    	    map.setSize("100%", "100%");
		    	    // Add some controls for the zoom level
		    	    map.addControl(new LargeMapControl());

		    	    // Add a marker
		    	    map.addOverlay(new Marker(currentLocation));

		    	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
		    	    dock.addNorth(map, 500);

		    	    // Add the map to the HTML host page
		    	    add(dock);
		      }
		    });
	}
}
