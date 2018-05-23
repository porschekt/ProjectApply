package logic;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected double x, y, hp, speed;
	protected int z;
	protected boolean visible, destroyed;

	protected Entity() {//make all class that can collide like bullet enermy player
		visible = true;
		destroyed = false;
		this.hp = 100;
		this.speed = 20;

	}

	protected Entity(double hp, double speed) {
		visible = true;
		destroyed = false;
		this.hp = hp;
		this.speed = speed;

	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getZ() {
		return z;
	}

	public abstract void update();

}
