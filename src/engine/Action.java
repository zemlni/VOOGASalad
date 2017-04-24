package engine;

/**
 * Abstract class for actions. Actions must implement the act method.
 * 
 * @author nikita
 */
public abstract class Action extends GameObject implements ActionInterface {

	public Action() {
		super("Action");
	}

	/**
	 * carry out the action represented by this object. is called when the event
	 * this action is held by is triggered
	 */
	@Override
	public abstract void act();

}