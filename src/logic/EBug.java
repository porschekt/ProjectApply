package logic;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;

public class EBug extends Enemy {

	public EBug(double x) {
		// TODO Auto-generated constructor stub
		super(100, 2);
		this.width = RenderableHolder.eBug.getWidth();
		this.height = RenderableHolder.eBug.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = x;
		this.y = -this.height - ThreadLocalRandom.current().nextDouble(500);
		this.collideDamage = 10;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.eBug, x, y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		y += this.speed;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}
	}

	public Shape getBoundary() {
		Rectangle bound = new Rectangle();
		bound.setX(x);
		bound.setY(y);
		bound.setWidth(width);
		bound.setHeight(height);
		return bound;
	}

}
