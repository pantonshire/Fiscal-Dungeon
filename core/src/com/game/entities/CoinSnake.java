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

public class CoinSnake extends Enemy {

	private int PATH_FIND_UPDATE_RATE = 60;
	private int DROP_COIN_RATE = 20;
	
	private Animation animation;
	private ArrayList<Point> path;
	private int pathFindTimer;
	private int dropCoinTimer;
	
	public CoinSnake(Level world, double x, double y) {
		super(world, x, y, 14, 14, 1, 1);
		animation = new Animation(Textures.instance.getTexture("coin_snake"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		path = new ArrayList<Point>();
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
		if(pathFindTimer > 0) { --pathFindTimer; }
		Player targetPlayer;
		if(pathFindTimer == 0 && atTarget(path != null && path.size() > 0 ? path.get(0) : null) && (targetPlayer = getNearestPlayer()) != null) {
			path = world.getTileMap().findPath(position, targetPlayer.position, 30, false);
			pathFindTimer = PATH_FIND_UPDATE_RATE;
		}
		
		followPath();
		
		if(dropCoinTimer > 0) { --dropCoinTimer; }
		if(isWalking() && dropCoinTimer == 0) {
			dropCoinTimer = DROP_COIN_RATE;
			world.spawn(new GoldCoin(world, position.x, position.y));
		}
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}
	
	protected void onDeath() {
		SoundEffects.instance.play("coin_snake_die", 1, 1, 0);
		for(int i = 0; i < 5; i++) {
			Coin coin = new CoinProjectile(world, position.x, position.y, 2 * Math.PI / 5 * i, RandomUtils.randDouble(0.5, 1.0));
			world.spawn(coin);
		}
		
		if(RandomUtils.randDouble() < 0.05) {
			world.spawn(new Tax(world, position.x, position.y));
		}
	}
}
