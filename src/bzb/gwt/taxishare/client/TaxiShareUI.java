package bzb.gwt.taxishare.client;

import java.util.Date;

import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.Highlight;
import org.adamtacy.client.ui.effects.impl.NShow;
import org.adamtacy.client.ui.effects.impl.SlideDown;
import org.adamtacy.client.ui.effects.impl.SlideRight;
import org.adamtacy.client.ui.effects.transitionsphysics.EaseInTransitionPhysics;
import org.adamtacy.client.ui.effects.transitionsphysics.EaseOutTransitionPhysics;
import org.adamtacy.client.ui.effects.transitionsphysics.ElasticTransitionPhysics;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TaxiShareUI implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private static int pageNum = 0;
	private static int totalPages = 3;
	
	private static final int PAGE_TIMEOUT = 5000;
	private static final int TIMEOUT_INDICATOR = 1000;
	private static final int CLOCK_UPDATE = 1000;
	
	private static Fade f = new Fade();
	private static NShow ns = new NShow();
	private static Fade fPage = new Fade();
	private static SlideDown sd = new SlideDown();
	private static Highlight mf;
	
	public TaxiShareUI () {
		sd.setTransitionType(new EaseOutTransitionPhysics());
		mf = new Highlight(vPanel.getElement());
		sd.setEffectElement(vPanel.getElement());
		f.setEffectElement(vPanel.getElement());
		ns.setEffectElement(vPanel.getElement());
		ns.setDuration(1);
		f.addEffectCompletedHandler(new EffectCompletedHandler(){
			public void onEffectCompleted(EffectCompletedEvent evt){
				vPanel.clear();
				
				if (pageNum < totalPages - 1) {
					TaxiPanel t1 = new TaxiPanel("asdf " + pageNum);
					TaxiPanel t2 = new TaxiPanel("asdf " + pageNum);
					TaxiPanel t3 = new TaxiPanel("asdf " + pageNum);
					
					vPanel.add(t1);
					vPanel.add(t2);
					vPanel.add(t3);
					
					sd.play();
				} else {
					MapPanel mp = new MapPanel();
					vPanel.add(mp);
					
					mf.addEffectCompletedHandler(new EffectCompletedHandler() {
						public void onEffectCompleted(EffectCompletedEvent event) {
							
						}
					});
					
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
		
		loadAttsPanel();
		loadCurrentPage();
		f.play();
		
		Timer nextPageTimer = new Timer () {
			public void run () {
				pageNum++;
				if (pageNum >= totalPages) {
					pageNum = 0;
				}
				loadCurrentPage();
				fPage.setPosition(0);
			}
		};
		nextPageTimer.scheduleRepeating(PAGE_TIMEOUT);
		
		/*final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			//Fired when the user clicks on the sendButton.
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			//Fired when the user types in the nameField.
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			//Send the name from the nameField to the server and wait for a response.
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);*/
	}
	
	private static VerticalPanel vPanel = new VerticalPanel();
	
	private static Timer countdownTimer = new Timer() {
		public void run () {
			f.play();
		}
	};
	
	private void loadCurrentPage () {
		pageLabel.setText("Page " + (pageNum + 1) + "/" + totalPages);
		pageLabel.setStyleName("pageLabel");
		countdownTimer.schedule(PAGE_TIMEOUT - TIMEOUT_INDICATOR);
		fPage.play();
	}
	
	static final Label timeLabel = new Label();
	static final Label pageLabel = new Label();
	
	private void loadAttsPanel () {
		RootPanel.get("pageAtts").add(timeLabel);
		RootPanel.get("pageAtts").add(pageLabel);
		
		Timer pageAttsTimer = new Timer() {
			public void run () {
				timeLabel.setText(DateTimeFormat.getFormat("h:mm:ss a").format(new Date()));
				timeLabel.setStyleName("timeLabel");
			}
		};
		pageAttsTimer.scheduleRepeating(CLOCK_UPDATE);
	}
}
