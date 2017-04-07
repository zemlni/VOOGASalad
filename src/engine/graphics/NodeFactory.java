package engine.graphics;

import engine.EntityInterface;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Jay Doherty
 * 
 * This class can convert GameEngine Entities into JavaFX Nodes.
 */
public class NodeFactory implements NodeFactoryInterface {

	public NodeFactory() {
		
	}

	@Override
	public Node getNodeFromEntity(EntityInterface entity) {
		ImageView node = new ImageView(new Image(entity.getImagePath()));
		node.setX(entity.getX() - entity.getWidth()/2);
		node.setY(entity.getY() - entity.getHeight()/2);
		node.setFitWidth(entity.getWidth());
		node.setFitHeight(entity.getHeight());
		return node;
	}
}
