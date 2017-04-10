package engine.game.gameloop;

import java.util.ResourceBundle;

import engine.game.LevelManager;
import engine.graphics.GraphicsEngine;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class TransitionStepStrategy implements StepStrategy {
	private static final String RESOURCES_NAME =  "resources/TransitionScreen";
	private static final int FRAME_DURATION = 150;
	
	private int frameNumber = 1;
	private String resourceFileTextName;
	
	private ObservableBundle observableBundle;   //TODO These are just being used to pass to next level - too many parameters in method names; find a better way!
	private LevelManager levelManager;
	private Scene gameScene;
	private GraphicsEngine graphicsEngine;
	private Screen screen;
	
	public TransitionStepStrategy(String resourceFileTextName){
		 this.resourceFileTextName = resourceFileTextName;
	}
	
	@Override
	public void setup(ObservableBundle newObservableBundle, LevelManager levelManager, Scene gameScene, Screen screen,
			GraphicsEngine graphicsEngine) {
		this.observableBundle = newObservableBundle;   //TODO These are just being used to pass to next level - too many parameters in method names; find a better way!
		this.levelManager = levelManager;
		this.gameScene = gameScene;
		this.graphicsEngine = graphicsEngine;
		this.screen = screen;
		//TODO Also display number of lives left for lose a life transition
		graphicsEngine.fillScreenWithText(ResourceBundle.getBundle(RESOURCES_NAME).getString(resourceFileTextName));
	}
	
	@Override
	public void step() {
		System.out.println("Step in Transition Screen");
		//TODO Could add animation here
		if(frameNumber == FRAME_DURATION){
			System.out.println("move to next screen");
			moveToNextScreen();
		}
		frameNumber++;
	}
	
	//protected abstract String getSubclassSpecificText();
	protected abstract StepStrategy getNextStepStrategy(LevelManager levelManager);
	protected abstract int nextLevelNumber(LevelManager levelManager);
	
	private void moveToNextScreen(){
		screen.getTimeline().stop();
		levelManager.setLevelNumber(nextLevelNumber(levelManager));
		Screen nextScreen = new Screen(getNextStepStrategy(levelManager), observableBundle, levelManager, gameScene, graphicsEngine);
		nextScreen.getTimeline().play();
		
	}
}