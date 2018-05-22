package logic;

import sharedObject.RenderableHolder;
import window.SceneManager;

public abstract class Enemy extends CollidableEntity {

	private static int zCounter = -200; // to generate different z for each Enemy to prevent flashing when 2 or more
										// enemy are overlap.
										// Enemy z is between -200 and -100 inclusive.

	public Enemy(double hp, double speed) {
		super(hp, speed);
		this.side = -1;
		this.z = zCounter;
		zCounter++;
		if (zCounter > -100) {
			zCounter = -200;
		}


		GameLogic.currentEnemyNum++;

	}

	public void onCollision(CollidableEntity others) {
		this.hp -= others.collideDamage;
		if (this.hp <= 0) {
			if (!this.destroyed) {
				calculateScore(this);
				GameLogic.currentEnemyNum--;
				Explosion e = new Explosion(x, y, width, height, z);
				e.playSfx();
				RenderableHolder.getInstance().add(e);
			}
			this.destroyed = true;
			this.visible = false;
		}
		// System.out.println(this.getClass() + " is collided! by player " + this.hp);
	}

	public boolean isOutOfScreen() {
		if ((int) this.y > SceneManager.SCENE_HEIGHT) {
			GameLogic.currentEnemyNum--;
			return true;
		}
		return false;
	}

	private void calculateScore(Enemy e) {
		if (e instanceof EBoss) {
			Score.score += 30;
			GameLogic.isBossAlive = false;
			GameLogic.killedBoss = true;
		}
		if (e instanceof EBug) {
			Score.score += 1;
		}
		if (e instanceof EEyeball) {
			Score.score += 5;
		}
		if (e instanceof ESquid) {
			Score.score += 3;
		}
		if (e instanceof EWing) {
			Score.score += 10;
		}
		if (e instanceof EBig) {
			Score.score += 15;
		}
	}

}
