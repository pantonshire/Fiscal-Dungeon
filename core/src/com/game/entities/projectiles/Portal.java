package com.game.entities.projectiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.game.entities.Enemy;
import com.game.entities.Entity;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.utils.AngleHelper;
import com.game.utils.RandomUtils;
import com.game.utils.RayCaster;
import com.game.vector.Vector;

public class Portal extends Entity implements LightSource {

	private Texture texture;
	private int time;
	private int numShots;
	private int maxShots;
	private double speed;
	private double range;
	private int rate;
	private double innacuracy;
	private int homingLevel;

	public Portal(Level level, double x, double y, double speed, int rate, double innacuracy, int homingLevel, int maxShots, double range) {
		super(level, x, y);
		texture = Textures.instance.getTexture("portal");
		this.speed = speed;
		this.rate = rate;
		this.innacuracy = innacuracy;
		this.homingLevel = homingLevel;
		this.maxShots = maxShots;
		this.range = range;
		
		level.getLightManager().addDynamicLight(this);
	}

	protected void updateEntity() {
		if(++time > 1000) { destroy(); }
		if(time % rate == 0) {
			ArrayList<Enemy> enemies = level.getEnemies();
			double closestDist = 0;
			Enemy closestEnemy = null;
			
			for(Enemy enemy : enemies) {
				double dist = position.distBetween(enemy.getPosition());
				if(dist < range && (closestEnemy == null || dist < closestDist) && !enemy.invulnerable() && RayCaster.canSee(level, position, enemy.getPosition(), -1)) {
					closestEnemy = enemy;
					closestDist = dist;
				}
			}
			
			if(closestEnemy != null) {
				double angle = AngleHelper.estimateAim(position, speed, closestEnemy.getPosition(), closestEnemy.getVelocity()) + RandomUtils.randInnacuracyDegrees(innacuracy);
				level.spawn(new PortalProjectile(level, position.x, position.y, angle, speed, homingLevel));
				if(++numShots > maxShots) { destroy(); }
			}
		}
	}

	public void render(LayerRenderer renderer) {
		int width = texture.getWidth(), height = texture.getHeight();
		renderer.getSpriteBatch().draw(texture, (float)position.x - width / 2, (float)position.y - height / 2);
	}
	
	public Vector lightPosition() {
		return position;
	}
	
	public int numLightRays() {
		return LevelLightManager.DEFAULT_RADIAL_SOURCE;
	}
	
	public float lightStrength() {
		return 64;
	}
	
	public Color lightColor() {
		return new Color(0.75F, 0.05F, 0.9F, 0.25F);
	}
}
