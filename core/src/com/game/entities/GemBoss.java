package com.game.entities;

import java.awt.Point;
import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.utils.RandomUtils;

public class GemBoss extends Enemy {

	private Animation animation;
	private ArrayList<Point> path;
	private int timer;
	private int phase;

	public GemBoss(Level level, double x, double y) {
		super(level, x, y, 30, 30, 1, 175);
		animation = new Animation(Textures.instance.getTexture("gem_boss"), Sequence.formatSequences(new Sequence(32, 32, 6, 5)));
		path = new ArrayList<Point>();
		timer = 120;
	}

	private void followPath() {
		if(path == null) {
			return;
		}

		if(path.size() == 0) {
			setStopped();
			path = null;
			return;
		}

		Point targetTile = path.get(0);

		if(targetTile != null) {
			Point targetPos = getTargetPos(targetTile);

			if(atTarget(targetPos)) {
				if(path != null) {
					path.remove(0);
					targetTile = path.size() > 0 ? path.get(0) : null;
					targetPos = getTargetPos(targetTile);

					if(path.size() == 0) {
						timer = 0;
					}
				}
			}

			if(targetPos != null) {
				if(Math.abs(position.x - targetPos.x) >= walkSpeed) {
					if(position.x < targetPos.x) { setWalkingRight(); }
					else if(position.x > targetPos.x) { setWalkingLeft(); }
				}

				else {
					setStoppedHorizontal();
				}

				if(Math.abs(position.y - targetPos.y) >= walkSpeed) {
					if(position.y < targetPos.y) { setWalkingUp(); }
					else if(position.y > targetPos.y) { setWalkingDown(); }
				}

				else {
					setStoppedVertical();
				}
			}
		}
	}

	private Point getTargetPos(Point tile) {
		if(tile == null) { return null; }
		int tileSize = level.getTileMap().getTileSize();
		int targetX = tile.x * tileSize + (tileSize / 2);
		int targetY = tile.y * tileSize + (tileSize / 2);
		return new Point(targetX, targetY);
	}

	private boolean atTarget(Point targetPos) {
		if(targetPos == null) { return true; }
		boolean atTargetX = Math.abs(position.x - targetPos.x) < walkSpeed;
		boolean atTargetY = Math.abs(position.y - targetPos.y) < walkSpeed;
		return atTargetX && atTargetY;
	}

	protected void updateEntity() {
		if(timer > 0) { --timer; }
		Player targetPlayer = getNearestPlayer();

		if(health <= 75 && phase != 3) {
			phase = 3;
			setStopped();
			path = null;
			timer = 60;
		}

		if(timer == 0) {
			if(phase == 0) {
				if(targetPlayer != null) {
					timer = 60;
					phase = 1;
					path = level.getTileMap().findPath(position, targetPlayer.position, 38, true);
				}
			}

			else if(phase == 1) {
				if(targetPlayer != null) {
					double angleBetween = position.angleBetween(targetPlayer.position);

					switch(RandomUtils.randInt(2)) {
					case 0:
						for(int i = 0; i <= 10; i++) {
							level.spawn(new PurpleGemProjectile(level, position.x, position.y, Math.PI * 2 / 10 * i));
						}
						phase = 2;
						timer = 30;
						break;
					case 1:
						level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween, 4.5));
						level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween + Math.toRadians(4), 4.0));
						level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween + Math.toRadians(8), 3.5));
						level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween - Math.toRadians(4), 4.0));
						level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween - Math.toRadians(8), 3.5));
						phase = 0;
						timer = 40;
						break;
					case 2:
						for(int i = 0; i <= 6; i++) {
							level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween - Math.toRadians(90) + (Math.toRadians(180) / 6 * i), 1.75));
							level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween - Math.toRadians(90) + (Math.toRadians(180) / 6 * i), 1.5));
							level.spawn(new RedGemProjectile(level, position.x, position.y, angleBetween - Math.toRadians(90) + (Math.toRadians(180) / 6 * i), 1.25));
						}
						phase = 0;
						timer = 40;
						break;
					}
				}
			}

			else if(phase == 2) {
				for(int i = 0; i <= 16; i++) {
					level.spawn(new RedGemProjectile(level, position.x, position.y, Math.PI * 2 / 16 * i, 4));
				}
				phase = 0;
				timer = 90;
			}

			else if(phase == 3) {
				if(RandomUtils.randDouble() < 0.1) { level.spawn(new PurpleGemProjectile(level, position.x, position.y, RandomUtils.randAngle())); }
				else if(RandomUtils.randDouble() < 0.8) { level.spawn(new RedGemProjectile(level, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(0.5, 5))); }
				else { level.spawn(new CoinProjectile(level, position.x, position.y, RandomUtils.randAngle(), RandomUtils.randDouble(3, 7))); }
				timer = 3;
			}
		}

		if(phase != 3) {
			followPath();
		}
	}

	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}

	protected void onDeath() {
		SoundEffects.instance.play("boom", 1, 1, 0);
		for(int i = 0; i <= 15; i++) {
			double angle = Math.PI * 2 / 15 * i;
			level.spawn(new RedGemProjectile(level, position.x, position.y, angle, 1.25));
		}

		int x = level.getTileMap().getMapCoordinate(position.x), y = level.getTileMap().getMapCoordinate(position.y);
		level.getTileMap().setTile(x, y, (byte)-9);
		level.spawn(new Trapdoor(level, level.getTileMap().getWorldCoordinate(x), level.getTileMap().getWorldCoordinate(y)));
	}
}
