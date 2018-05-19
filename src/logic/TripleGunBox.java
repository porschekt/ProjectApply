package logic;

import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class TripleGunBox extends Items {

	public TripleGunBox(double x) {
		// TODO Auto-generated constructor stub
		super(ThreadLocalRandom.current().nextDouble(1, 5));
		this.width = RenderableHolder.tripleGunBox.getWidth();
		this.height = RenderableHolder.tripleGunBox.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = x;
		this.y = -this.height - ThreadLocalRandom.current().nextDouble(500);
		this.collideDamage = 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.tripleGunBox, x, y);
	}

	@Override
	public void onCollision(CollidableEntity others) {
		// TODO Auto-generated method stub
		this.hp = 0;
		this.destroyed = true;
		this.visible = false;
	}

}
