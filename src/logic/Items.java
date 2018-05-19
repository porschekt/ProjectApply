package logic;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import window.SceneManager;

public abstract class Items extends CollidableEntity {
	private static int zCounter = -725; // z is between -725 to -750

	protected Items(double speed) {
		super(1, speed);
		// TODO Auto-generated constructor stub
		this.side = -1;
		this.z = zCounter;
		zCounter++;
		if (zCounter > -725) {
			zCounter = -750;
		}
	}

	@Override
	public Shape getBoundary() {
		Rectangle bound = new Rectangle();
		bound.setX(x);
		bound.setY(y);
		bound.setWidth(width);
		bound.setHeight(height);
		return bound;
	}

	@Override
	public void update() {
		this.y += speed;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}
	}

	public boolean isOutOfScreen() {
		if ((int) this.y > SceneManager.SCENE_HEIGHT) {
			return true;
		}
		return false;
	}
}
