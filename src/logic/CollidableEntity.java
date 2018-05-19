package logic;

import javafx.scene.shape.Shape;

public abstract class CollidableEntity extends Entity {
	protected double width, height, collideDamage;
	public int side;

	protected CollidableEntity(double hp, double speed) {
		super(hp, speed);
	}

	protected boolean collideWith(CollidableEntity other) {
		if (this instanceof Bullet && other instanceof Bullet) {
			return false;
		}
		if ((this instanceof Bullet && other instanceof Items) || (this instanceof Items && other instanceof Bullet)) {
			return false;
		}
		if (this.side != other.side) {
			Shape intersect = Shape.intersect(this.getBoundary(), other.getBoundary());
			return (intersect.getBoundsInLocal().getWidth() != -1);
		}
		return false;
	}

	public abstract void onCollision(CollidableEntity others);

	public abstract Shape getBoundary();
}
