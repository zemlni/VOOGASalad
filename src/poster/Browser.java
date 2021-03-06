package poster;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Nikita Zemlevskiy naz7. This is a simplistic browser that will be
 *         used to log the user into Facebook. And retrieve the access code and
 *         the user code. The typical javafx setup is made to create a simple
 *         browser.
 */
public class Browser {
	private Stage primaryStage;
	private static final int BROWSER_WIDTH = 640;
	private static final int BROWSER_HEIGHT = 480;
	private FacebookPoster poster;

	/**
	 * Create a new browser. It will be used to log the user into Facebook in
	 * order to authorize posting on the user's behalf.
	 * 
	 * @param oauthRequestUrl
	 *            the Facebook url to which to point the browser.
	 * @param poster
	 *            the FacebookPoster that this browser belongs to
	 */
	public Browser(String oauthRequestUrl, FacebookPoster poster) {
		this.poster = poster;
		setup(oauthRequestUrl);
	}

	/**
	 * Close the browser. Called from FacebookPoster when the post is complete.
	 */
	public void close() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				primaryStage.close();
			}
		});
	}

	/**
	 * Setup this simple browser and launch it.
	 * 
	 * @param oauthRequestUrl
	 *            the initial URL of the browser which will direct the user to a
	 *            Facebook signin page
	 */
	private void setup(String oauthRequestUrl) {
		this.primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		ResourceFinder finder = new ResourceFinder("resources/Strings");
		primaryStage.setTitle(finder.getResource("FacebookBrowserTitle"));
		WebView myBrowser = new WebView();
		WebEngine myWebEngine = myBrowser.getEngine();
		myWebEngine.load(oauthRequestUrl);
		StackPane root = new StackPane();
		root.getChildren().add(myBrowser);
		primaryStage.setScene(new Scene(root, BROWSER_WIDTH, BROWSER_HEIGHT));
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> poster.finish(false));
		primaryStage.show();
	}
}
