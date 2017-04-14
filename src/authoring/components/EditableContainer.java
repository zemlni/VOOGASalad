package authoring.components;

import authoring.Workspace;
import authoring.views.View;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class EditableContainer extends View {
	
	private Workspace workspace;
	private Object currentlyEditing;

	/**
	 * 
	 */
	public EditableContainer(Workspace workspace, String title) {
		super(title);
		this.workspace = workspace;
		setup();
	}
	
	private void setup() {
		createContainer();
		createButtons();
	}
	
	protected Workspace getWorkspace() {
		return workspace;
	}
	
	private void createButtons() {
		ComponentMaker maker = new ComponentMaker(workspace.getResources());
		VBox buttonBox = new VBox();
		Button newButton = maker.makeButton("New", e -> createNew(), true);
		Button editButton = maker.makeButton("Edit", e -> edit(), true);
		Button deleteButton = maker.makeButton("Delete", e -> delete(), true);
		HBox modificationButtons = new HBox(editButton, deleteButton);
		buttonBox.getChildren().addAll(newButton, modificationButtons);
		setBottom(buttonBox);
	}
	
	public boolean selectionExists(Object object) {
		if (object == null) {
			ComponentMaker maker = new ComponentMaker(workspace.getResources());
			Alert alert = maker.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", workspace.getResources().getString("PickSomething"));
			alert.show();
		}
		return object != null;
	}
	
	public void setCurrentlyEditing(Object currentlyEditing) {
		this.currentlyEditing = currentlyEditing;
	}
	
	public Object getCurrentlyEditing() {
		return currentlyEditing;
	}
	
	public <E> void setOnClick(ListView<E> list, Runnable single) {
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 1) {
					if (single != null) {
						single.run();
					}
				} else if (mouseEvent.getClickCount() == 2) {
					edit();
				}
			}
		});
	}

	public abstract void createContainer();
	
	public abstract void createNew();
	
	public abstract void edit();
	
	public abstract void delete();
			
}
