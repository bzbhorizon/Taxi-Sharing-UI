package bzb.gwt.taxishare.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MapPanel extends VerticalPanel {

	public MapPanel () {
		setWidth((Window.getClientWidth() - 40) + "px");
		setHeight(Window.getClientHeight() + "px");
		Maps.loadMapsApi("", "2", false, new Runnable() {
		      public void run() {
		    	    LatLng currentLocation = LatLng.newInstance(39.509, -98.434);

		    	    final MapWidget map = new MapWidget(currentLocation, 3);
		    	    map.setSize("100%", "100%");
		    	    // Add some controls for the zoom level
		    	    map.addControl(new LargeMapControl());

		    	    // Add a marker
		    	    map.addOverlay(new Marker(currentLocation));

		    	    // Add an info window to highlight a point of interest
		    	    map.getInfoWindow().open(map.getCenter(),
		    	        new InfoWindowContent("World's Largest Ball of Sisal Twine"));

		    	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
		    	    dock.addNorth(map, 500);

		    	    // Add the map to the HTML host page
		    	    add(dock);
		      }
		    });
	}
}
