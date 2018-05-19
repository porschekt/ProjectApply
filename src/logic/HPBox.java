package logic;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class HPBox extends Items {
	private double HPStorage;

	public HPBox(double x) {
		super(ThreadLocalRandom.current().nextDouble(1, 5));
		this.HPStorage = ThreadLocalRandom.current().nextDouble(50, 500);
		this.width = RenderableHolder.hpBox.getWidth();
		this.height = RenderableHolder.hpBox.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = x;
		this.y = -this.height - ThreadLocalRandom.current().nextDouble(500);
		this.collideDamage = 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.hpBox, x, y);
	}

	@Override
	public void onCollision(CollidableEntity others) {
		// TODO Auto-generated method stub
		this.hp = 0;
		this.destroyed = true;
		this.visible = false;
	}

	protected double getHPStorage() {
		return HPStorage;
	}

}
