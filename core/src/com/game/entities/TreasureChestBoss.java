package com.game.entities;

import java.awt.Point;
import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.utils.RandomUtils;
import com.game.world.World;

public class TreasureChestBoss extends Enemy {

	private Animation animation;
	private ArrayList<Point> path;
	private int timer;
	private int phase;

	public TreasureChestBoss(World world, double x, double y) {
		super(world, x, y, 30, 30, 2, 80);
		animation = new Animation(Textures.instance.getTexture("treasure_chest_boss"), Sequence.formatSequences(
				new Sequence(32, 32, 0, 1),
				new Sequence(32, 32, 0, 1),
				new Sequence(32, 32, 4, 2)));
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
		int tileSize = world.getTileMap().getTileSize();
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

		if(timer == 0) {
			if(phase == 0) {
				timer = 10;
				phase = 1;
			}

			else if(phase == 1) {
				if(targetPlayer != null) {
					phase = 2;
					timer = 45;

					double angleBetween = position.angleBetween(targetPlayer.position);

					switch(RandomUtils.randInt(3)) {
					case 0:
						phase = 5;
						timer = 120;
						break;
					case 1:
						for(int i = 0; i <= 5; i++) {
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.5));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.25));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.0));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.75));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.5));
						}
						break;
					case 2:
						for(int i = 0; i <= 10; i++) {
							world.spawn(new RedGemProjectile(world, position.x, position.y, angleBetween - Math.toRadians(120) + (Math.toRadians(240) / 10 * i), 3.5));
							world.spawn(new RedGemProjectile(world, position.x, position.y, angleBetween - Math.toRadians(120) + (Math.toRadians(240) / 10 * i), 3));
						}
						break;
					case 3:
						for(int i = 0; i <= 20; i++) {
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween + (Math.PI * 2 / 20 * i), 3.5));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween + (Math.PI * 2 / 20 * i), 3.25));
							world.spawn(new CoinProjectile(world, position.x, position.y, angleBetween + (Math.PI * 2 / 20 * i), 3.0));
						}
						break;
					}
				}

				else {
					timer = 30;
					phase = 0;
				}
			}

			else if(phase == 2) {
				timer = 30;
				phase = 3;
			}

			else if(phase == 3) {
				if(targetPlayer != null) {
					timer = 90;
					phase = 4;
					path = world.getTileMap().findPath(position, targetPlayer.position, 38, false);
				}
			}

			else if(phase == 4) {
				path = null;
				setStopped();
				timer = 10;
				phase = 0;
			}

			else if(phase == 5) {
				phase = 3;
				timer = 30;
			}
		}

		if(phase == 4) {
			followPath();
		}

		if(phase == 5 && timer % 4 == 0) {
			world.spawn(new CoinProjectile(world, position.x, position.y, (Math.PI * 2 / 480 * timer), 3));
			world.spawn(new CoinProjectile(world, position.x, position.y, (Math.PI * 2 / 480 * timer) + Math.PI / 2, 3));
			world.spawn(new CoinProjectile(world, position.x, position.y, (Math.PI * 2 / 480 * timer) - Math.PI / 2, 3));
			world.spawn(new CoinProjectile(world, position.x, position.y, (Math.PI * 2 / 480 * timer) + Math.PI, 3));
		}
	}

	public void render(LayerRenderer renderer) {
		if(phase == 4 && isWalking()) {
			animation.setSequence(2, true);
		}

		else if(phase == 2 || phase == 5) {
			animation.setSequence(1, true);
		}

		else {
			animation.setSequence(0, true);
		}

		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}

	protected void onDeath() {
		SoundEffects.instance.play("boom", 1, 1, 0);
		for(int i = 0; i <= 15; i++) {
			double angle = Math.PI * 2 / 15 * i;
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 1.25));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 1));
		}

		int x = world.getTileMap().getMapCoordinate(position.x), y = world.getTileMap().getMapCoordinate(position.y);
		world.getTileMap().setTile(x, y, (byte)-9);
		world.spawn(new Trapdoor(world, world.getTileMap().getWorldCoordinate(x), world.getTileMap().getWorldCoordinate(y)));
	}
}
