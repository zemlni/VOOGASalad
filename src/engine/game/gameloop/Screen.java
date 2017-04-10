package engine.game.gameloop;

import java.util.List;

import engine.game.LevelManager;
import engine.graphics.GraphicsEngine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Screen class deals more with transistions between Screens and Timeline. Specific step algorithm in
 * StepStrategy subclasses in the Strategy Design Pattern (composition).
 * 
 * @author Matthew Barbano
 */
public class Screen{
	public static final int FRAME_TIME_MILLISECONDS = 10;
	private StepStrategy currentStepStrategy; //immutable
	private Timeline timeline;
	
	public Screen(StepStrategy currentStepStrategy, ObservableBundle observableBundle, LevelManager levelManager, Scene gameScene, GraphicsEngine graphicsEngine){
		this.currentStepStrategy = currentStepStrategy;
		setupTimeline();
		currentStepStrategy.setup(observableBundle, levelManager, gameScene, this, graphicsEngine);
	}
	
	private void setupTimeline(){
		// From ExampleBounce.java
		KeyFrame frame = new KeyFrame(Duration.millis(FRAME_TIME_MILLISECONDS), e -> step());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
	}
	
	public void start(){
		timeline.play();
	}
	
	private void step(){
		currentStepStrategy.step();
		//Make sure to call start() for the next screen when implement in StepStrategy subclasses! - no need for step() in GameLoop anymore
	}
	
	public StepStrategy getCurrentStepStrategy(){
		return currentStepStrategy;
	}
	
	public Timeline getTimeline(){
		return timeline;
	}
}
