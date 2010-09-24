package bzb.gwt.taxishare.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.Highlight;
import org.adamtacy.client.ui.effects.impl.NShow;

import bzb.gwt.taxishare.shared.TaxiStatus;
import bzb.gwt.taxishare.shared.TaxiStatus.TaxiPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TaxiShareUI implements EntryPoint {
	/*
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private static final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private static final String INSTANCE_ID = "emcc";
	
	private static String response;
	
	private static int pageNum = 0;
	
	private static final int PAGE_TIMEOUT = 10000;
	private static final int TIMEOUT_INDICATOR = 1000;
	private static final int CLOCK_UPDATE = 1000;
	
	private static Fade f = new Fade();
	private static NShow ns = new NShow();
	private static Fade fPage = new Fade();
	private static Highlight mf;
	
	private static final Image busyIcon = new Image("busy.gif");
	
	private static ArrayList<ArrayList<TaxiPanel>> pages = new ArrayList<ArrayList<TaxiPanel>>();
	private static MapPanel mp = new MapPanel();
	
	public TaxiShareUI () {
		mf = new Highlight(vPanel.getElement());
		f.setEffectElement(vPanel.getElement());
		ns.setEffectElement(vPanel.getElement());
		ns.setDuration(1);
		f.addEffectCompletedHandler(new EffectCompletedHandler(){
			public void onEffectCompleted(EffectCompletedEvent evt){
				vPanel.clear();
				
				if (pages.size() == 0) {
					vPanel.add(new Label("System is currently experiencing issues"));
				} else if (pageNum < pages.size() - 1) {
					Iterator<TaxiPanel> it = pages.get(pageNum).iterator();
					while (it.hasNext()) {
						vPanel.add(it.next());
					}
				} else {
					vPanel.add(mp);					
					mf.play();
				}
				
				ns.play();
			}
		});
		fPage.addEffectElement(pageLabel.getElement());
		fPage.setDuration(PAGE_TIMEOUT / 1000);
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("activePanel").add(vPanel);
				
		requestUpdate();
		
		loadAttsPanel();
		loadCurrentPage();
		f.play();
		
		Timer nextPageTimer = new Timer () {
			public void run () {
				pageNum++;
				if (pageNum >= pages.size()) {
					pageNum = 0;
				}
				loadCurrentPage();
				fPage.setPosition(0);
			}
		};
		nextPageTimer.scheduleRepeating(PAGE_TIMEOUT);
	}
	
	private static void requestUpdate() {
		RootPanel.get("progress").add(busyIcon);
		greetingService.greetServer(INSTANCE_ID,
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("progress").clear();
					}

					public void onSuccess(String result) {
						response = result;
						parseResponse(response);
						RootPanel.get("progress").clear();
					}
				});
	}

	private static VerticalPanel vPanel = new VerticalPanel();
	
	private static Timer countdownTimer = new Timer() {
		public void run () {
			f.play();
		}
	};
	
	private static void loadCurrentPage () {
		pageLabel.setText("Page " + (pageNum + 1) + "/" + pages.size());
		countdownTimer.schedule(PAGE_TIMEOUT - TIMEOUT_INDICATOR);
		fPage.play();
		if (pageNum == 0) {
			requestUpdate();
		}
	}
	
	static final Label timeLabel = new Label();
	static final Label pageLabel = new Label();
	
	private static void loadAttsPanel () {
		RootPanel.get("page").add(pageLabel);
		RootPanel.get("pageAtts").add(timeLabel);
		Timer pageAttsTimer = new Timer() {
			public void run () {
				timeLabel.setText(DateTimeFormat.getFormat("h:mm:ss a").format(new Date()));
				timeLabel.setStyleName("timeLabel");
			}
		};
		pageAttsTimer.scheduleRepeating(CLOCK_UPDATE);
	}
	
	private static void parseResponse (String response) {
		try {
			JSONValue value = JSONParser.parse(response);
			if (value != null) {
				int heightRemaining = (int) ((double)Window.getClientHeight() * 0.5);
				ArrayList<TaxiPanel> thisPage = new ArrayList<TaxiPanel>();
				int currPage = 0;
				JSONArray taxiStatusArray = value.isObject().get("TaxiStatus").isArray();
				for (int i = 0; i < taxiStatusArray.size(); i++) {
					if (heightRemaining < 0) {
						heightRemaining = (int) ((double)Window.getClientHeight() * 0.5);
						currPage++;
					}
					TaxiStatus thisTaxi = TaxiStatus.getTaxiStatus(taxiStatusArray.get(i).isObject());
					TaxiPanel p = thisTaxi.getPanel();
					if (pages.size() <= currPage) {
						pages.add(currPage, new ArrayList<TaxiPanel>());
					}
					pages.get(currPage).add(p);
					heightRemaining -= p.getElement().getClientHeight();
				}
				
				Window.alert("Panels built");
			} else {
				Window.alert("No json");
			}
		} catch (JSONException e) {
			Window.alert("Bad json: " + response);
		}
	}
}
