package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBoss extends Enemy {

	private int originalHp;
	private GameLogic gameLogic;
	private int bulletDelayTick = 0;

	public EBoss(GameLogic gameLogic) {
		super(4000, 0.1);
		this.originalHp = 2500;
		this.width = RenderableHolder.eBoss.getWidth();
		this.height = RenderableHolder.eBoss.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = -this.height;
		this.collideDamage = 1000;
		this.gameLogic = gameLogic;

		GameLogic.isBossAlive = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		long now = System.nanoTime();
		this.x = Math.sin(5 * now * 1e-9 + Math.toRadians(90)) * ((SceneManager.SCENE_WIDTH - this.width) / 2)
				+ (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		if (this.y < 40) {
			this.y += this.speed;
		}
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
			GameLogic.isBossAlive = false;
		}
		if (bulletDelayTick % 30 == 0) {
			gameLogic.addPendingBullet(new Bullet(x, y, 0, 20, -1, 1, this));
			RenderableHolder.laser.play();
		}
		bulletDelayTick++;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.eBoss, x, y);
		drawHpBar(gc);
	}
	

	private void drawHpBar(GraphicsContext gc) {
		double percentHp = this.hp / this.originalHp;
		gc.setFill(Color.RED);
		gc.fillRect(this.x + this.width / 2 - this.width / 4 * percentHp, this.y - 20, this.width / 2 * percentHp, 10);

	}

	@Override
	public Shape getBoundary() {
		// TODO Auto-generated method stub
		Circle bound = new Circle();
		bound.setCenterX(x + width / 2);
		bound.setCenterY(y + height / 2);
		bound.setRadius(width / 2);
		return bound;
	}

}
