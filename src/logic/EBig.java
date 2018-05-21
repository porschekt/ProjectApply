package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBig extends Enemy {
	private int bulletDelayTick = 0;
	private double yOffset;
	private GameLogic gameLogic;

	public EBig(GameLogic gameLogic) {
		super(1000, 0.1);
		this.width = RenderableHolder.eBig.getWidth();
		this.height = RenderableHolder.eBig.getHeight();
		yOffset = 0;
		this.visible = true;
		this.destroyed = false;
		this.x = (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = -this.height;
		this.collideDamage = 1000;
		this.gameLogic = gameLogic;
		
		GameLogic.isBigAlive = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		yOffset += this.speed;
		long now = System.nanoTime();
		this.x = Math.sin(2 * now * 1e-9) * ((SceneManager.SCENE_WIDTH - this.width) / 2)
				+ (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = Math.cos(2 * now * 1e-9) * (200) + yOffset - 200 - this.height - this.speed;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}
		if (bulletDelayTick % 20 == 0) {
			gameLogic.addPendingBullet(new Bullet(x - 50, y - 20, 8, 15, -1, 4, this));
			gameLogic.addPendingBullet(new Bullet(x + 50, y - 20, -8, 15, -1, 4, this));
			gameLogic.addPendingBullet(new Bullet(x - 15, y, 4, 15, -1, 4, this));
			gameLogic.addPendingBullet(new Bullet(x + 15, y, -4, 15, -1, 4, this));
			RenderableHolder.laser.play();
		}
		bulletDelayTick++;

	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.eBig, x, y);
	}

	@Override
	public Shape getBoundary() {
		// TODO Auto-generated method stub
		Circle bound = new Circle();
		bound.setCenterX(x + width / 2);
		bound.setCenterY(y + width / 2);
		bound.setRadius(width / 2);
		return bound;
	}

}
