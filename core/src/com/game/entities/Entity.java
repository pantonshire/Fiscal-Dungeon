package com.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.game.graphics.LayerRenderer;
import com.game.level.Level;
import com.game.vector.Vector;

public abstract class Entity {

	protected Level level;
	protected Vector position, velocity;
	private boolean shouldRemove;
	
	public Entity(Level level, double x, double y) {
		position = new Vector(x, y);
		velocity = new Vector();
		this.level = level;
	}
	
	/** Returns the position vector associated with this entity, allowing you to get or set the position. */
	public Vector getPosition() {
		return position;
	}
	
	/** Returns the velocity vector associated with this entity, allowing you to get or set the velocity. */
	public Vector getVelocity() {
		return velocity;
	}
	
	/** Returns true if the entity will be removed from the game world at the end of the next tick. */
	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	/** Schedules the entity to be removed from the game world at the end of the next tick. */
	public void destroy() {
		shouldRemove = true;
	}
	
	/** Called each frame to update the position and behaviour of the entity. */
	public void update() {
		updateMotion();
		updateEntity();
	}
	
	/** Moves the entity based on its current velocity. */
	protected void updateMotion() {
		if(!velocity.isZero()) {
			position.add(velocity);
		}
	}
	
	public boolean isOnScreen(LayerRenderer renderer) {
		float zoom = renderer.getCamera().zoom;
		float halfWidth = Gdx.graphics.getWidth() / 2 * zoom, halfHeight = Gdx.graphics.getHeight() / 2 * zoom;
		Vector3 cameraPos = renderer.getCamera().position;
		float minX = cameraPos.x - halfWidth, maxX = cameraPos.x + halfWidth, minY = cameraPos.y - halfHeight, maxY = cameraPos.y + halfHeight;
		return position.x + 32 >= minX && position.x <= maxX + 32 && position.y + 32 >= minY && position.y <= maxY + 32;
	}
	
	/** Used by subclasses of entity to update their behaviour. */
	protected abstract void updateEntity();
	
	/** Used by subclasses of entity to draw sprites to the screen at their location. */
	public abstract void render(LayerRenderer renderer);
}
