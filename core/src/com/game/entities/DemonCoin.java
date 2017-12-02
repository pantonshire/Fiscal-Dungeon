package com.game.entities;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;
import com.game.world.World;

public class DemonCoin extends Enemy {

	private Animation animation;
	private boolean invisible;
	private int timer;
	private int phase;

	public DemonCoin(World world, double x, double y) {
		super(world, x, y, 30, 30, 0.25, 10);
		animation = new Animation(Textures.instance.getTexture("demon_coin"), Sequence.formatSequences(
				new Sequence(32, 32, 0, 1),
				new Sequence(32, 32, 0, 1),
				new Sequence(32, 32, 2, 6).setNoLoop(),
				new Sequence(32, 32, 2, 6).setNoLoop()));
	}

	protected void updateEntity() {
		if(timer > 0) { --timer; }

		if(timer == 0) {
			if(phase == 0) {
				if(canSee(world.getPlayer())) {
					phase = 1;
					timer = 30;
					for(int i = 0; i < 20; i++) {
						world.spawn(new CoinProjectile(world, position.x, position.y, position.angleBetween(world.getPlayer().position) + RandomUtils.randDouble(Math.PI / 4) - Math.PI / 4, RandomUtils.randDouble(1, 3)));
					}
				}
			}

			else if(phase == 1) {
				phase = 3;
				timer = 11;
			}

			else if(phase == 3 && !invisible) {
				invisible = true;
				timer = 60;
			}
			
			else if(phase == 3 && invisible) {
				Vector newPos = position.copy().add(RandomUtils.randVector(16, 160));
				int x = world.getTileMap().getMapCoordinate(newPos.x);
				int y = world.getTileMap().getMapCoordinate(newPos.y);
				if(!world.getTileMap().isTileCollidable(x, y) && world.getTileMap().isInMapBounds(x, y)) {
					phase = 2;
					invisible = false;
					timer = 11;
					position.set(newPos.x, newPos.y);
				}
			}
			
			else if(phase == 2) {
				phase = 0;
				timer = 30;
			}
		}
	}

	public void render(LayerRenderer renderer) {
		animation.setSequence(phase, true);

		if(!invisible) {
			renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
			animation.updateTimer();
		}
	}
	
	protected void onDeath() {
		SoundEffects.instance.play("boom", 1, 1, 0);
		for(int i = 0; i < 20; i++) {
			Coin coin = RandomUtils.randDouble() < 0.05 ? new RedGem(world, position.x, position.y) : new GoldCoin(world, position.x, position.y);
			coin.getVelocity().setAngle(RandomUtils.randAngle(), RandomUtils.randDouble(0.5, 2.5));
			world.spawn(coin);
		}
	}
}
