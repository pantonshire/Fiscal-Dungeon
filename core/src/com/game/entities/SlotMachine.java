package com.game.entities;

import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.level.Level;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class SlotMachine extends Enemy {

	private Animation animation;
	private Animation[] slots;
	private int timer;
	private int phase;
	private double chargeAngle;
	private double chargeSpeed;
	private int chargeCooldown;
	private Vector spawnPos;
	private int[] slotResults;
	private int mouthYOffset;

	public SlotMachine(Level world, double x, double y) {
		super(world, x, y, 38, 54, 0.25, 300);
		animation = new Animation(Textures.instance.getTexture("slot_machine"), Sequence.formatSequences(
				new Sequence(39, 56, 0, 1),
				new Sequence(39, 56, 0, 1),
				new Sequence(39, 56, 4, 9).setNoLoop()));

		slots = new Animation[3];
		for(int i = 0; i < slots.length; i++) {
			slots[i] = new Animation(Textures.instance.getTexture("slot"), Sequence.formatSequences(
					new Sequence(8, 10, 0, 1),
					new Sequence(8, 10, 0, 1),
					new Sequence(8, 10, 0, 1),
					new Sequence(8, 10, 1, 9)));
		}

		slotResults = new int[3];
		mouthYOffset = -14;
		spawnPos = new Vector(x, y);
		timer = 120;
	}

	private int getSlotFrame(int slot) {
		switch(slot) {
		case 0:
			return 0;
		case 1:
			return 3;
		case 2:
			return 6;
		default:
			return 0;
		}
	}

	private void nextCycle() {
		phase = 0;
		timer = 20;
		velocity.set(0, 0);
		setVelocityLocked(false);
		ArrayList<Coin> coins = world.getCoins();
		for(Coin coin : coins) {
			coin.push(position.angleBetween(coin.position), 6.0, 2.0, 0.1);
		}
	}

	private void randomAttack(Player player, int attackNum) {
		if(slotResults[attackNum] == 0) {
			if(attackNum == 0) {
				++phase;
				timer = 140;
				double angle = position.copy().add(0, mouthYOffset).angleBetween(player.position);
				for(int i = 0; i < 40; i++) {
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - RandomUtils.randDouble(Math.toRadians(84)) + Math.toRadians(42), RandomUtils.randDouble(1, 4)));
				}
			}

			else if(attackNum == 1) {
				++phase;
				timer = 80;
				double angle = position.copy().add(0, mouthYOffset).angleBetween(player.position);
				for(int i = 0; i <= 5; i++) {
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 4.5));
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 4.25));
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 4.0));
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.75));
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(60) + (Math.toRadians(120) / 5 * i), 3.5));
				}
			}

			else if(attackNum == 2) {
				phase = 9;
				timer = 360;
			}
		}

		else if(slotResults[attackNum] == 1) {
			if(attackNum == 0) {
				++phase;
				timer = 80;
				for(int i = 0; i < 12; i++) {
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, Math.PI * 2 / 12 * i, 3.0));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, Math.PI * 2 / 12 * i + (Math.PI * 2 / 24), 2.25));
				}
			}

			else if(attackNum == 1) {
				++phase;
				timer = 100;
				double angle = position.copy().add(0, mouthYOffset).angleBetween(player.position);
				for(int i = 0; i <= 6; i++) {
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * i), 3.5));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * (i + 0.5)), 3.25));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * i), 3.0));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * (i + 0.5)), 2.75));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * i), 2.5));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * (i + 0.5)), 2.25));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * i), 2.0));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * (i + 0.5)), 1.75));
					world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(120) + (Math.toRadians(240) / 6 * i), 1.5));
				}
			}

			else if(attackNum == 2) {
				phase = 10;
				timer = 360;
			}
		}

		if(slotResults[attackNum] == 2) {
			if(attackNum == 0) {
				++phase;
				timer = 80;
				for(int i = 0; i < 8; i++) {
					world.spawn(new PurpleGemProjectile(world, position.x, position.y + mouthYOffset, Math.PI * 2 / 8 * i));
				}
			}

			else if(attackNum == 1) {
				++phase;
				timer = 80;
				double angle = position.copy().add(0, mouthYOffset).angleBetween(player.position);
				for(int i = 0; i < 6; i++) {
					world.spawn(new PurpleGemProjectile(world, position.x, position.y + mouthYOffset, angle - Math.toRadians(45) + (Math.toRadians(90) / 5 * i)));
				}
			}

			else if(attackNum == 2) {
				phase = 11;
				timer = 360;
			}
		}
	}

	protected void updateEntity() {
		if(timer > 0) { --timer; }
		if(chargeCooldown > 0) { --chargeCooldown; }
		Player targetPlayer = getNearestPlayer();

		if(timer == 0) {
			if(phase == 0) {
				if(targetPlayer != null) {
					phase = 1;
					timer = 20;
					animation.setSequence(2, true);
				}
			}

			else if(phase == 1) {
				phase = 2;
				timer = 120;

				for(int i = 0; i < slots.length; i++) {
					int lastSequence = slots[i].getSequence();
					int startFrame = getSlotFrame(lastSequence);
					slots[i].setSequence(3, true);
					slots[i].jumpToFrame(startFrame);
					slotResults[i] = RandomUtils.randInt(2);
				}
			}

			else if(phase < 5) {
				int slot = phase - 2;
				if(slots[slot].getFrameNumber() == getSlotFrame(slotResults[slot])) {
					++phase;
					timer = 40;
					slots[slot].setSequence(slotResults[slot], true);
				}
			}

			else if((phase == 5 || phase == 6 || phase == 7) && targetPlayer != null) {
				animation.setSequence(1, true);
				randomAttack(targetPlayer, phase - 5);
			}

			else if(phase == 8) {
				phase = 12;
				timer = 20;
				animation.setSequence(0, true);
			}

			else if(phase == 12) {
				if(targetPlayer != null) {
					phase = 13;
					timer = 600;
					chargeSpeed = 0;
					setVelocityLocked(true);
					chargeAngle = position.angleBetween(targetPlayer.position);
				}
			}

			else if(phase == 13) {
				phase = 14;
				timer = 120;
				animation.setSequence(0, true);
			}

			else if(phase == 14) {
				phase = 15;
				timer = 30;
				velocity.set(0, 0);
				setVelocityLocked(false);
			}

			else if(phase == 15) {
				phase = 16;
				timer = 300;
				velocity.setAngle(position.angleBetween(spawnPos), 4.0);
				setVelocityLocked(true);
			}

			else if(phase == 16) {
				nextCycle();
			}
		}

		if(phase == 9) {
			if(timer > 0 && timer % 4 == 0) {
				double angle = Math.toRadians(0.375) * (360 - timer);
				double speed = 3.0;
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle, speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(45), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(90), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(135), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(180), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(225), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(270), speed));
				world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, angle + Math.toRadians(315), speed));

				if(targetPlayer != null && timer % 16 == 0) {
					double coinSpeed = 3.0;
					int heuristicTravelTime = (int)Math.ceil(position.copy().add(0, mouthYOffset).distBetween(targetPlayer.position) / coinSpeed);
					Vector estimatedPosition = targetPlayer.position.copy().add(targetPlayer.velocity.copy().mply(heuristicTravelTime));
					double targetAngle = position.copy().add(0, mouthYOffset).angleBetween(estimatedPosition);
					world.spawn(new CoinProjectile(world, position.x, position.y + mouthYOffset, targetAngle, coinSpeed));
				}
			}

			else if(timer == 0) {
				phase = 8;
				timer = 30;
			}
		}

		else if(phase == 10) {
			if(timer > 0 && timer % 10 == 0) {
				world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, Math.toRadians(1.5) * (360 - timer), 3));
				world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, Math.toRadians(1.5) * (360 - timer) + Math.PI, 3));
			}

			else if(timer == 0) {
				phase = 8;
				timer = 30;
			}
		}

		else if(phase == 11) {
			if(timer > 0 && timer % 30 == 0) {
				for(int i = 0; i < 4; i++) {
					world.spawn(new PurpleGemProjectile(world, position.x, position.y + mouthYOffset, Math.PI * 2 / 4 * i + (timer / 30 * 2)));
				}
			}

			else if(timer == 0) {
				phase = 8;
				timer = 120;
			}
		}

		else if(phase == 13) {
			boolean updateVelocity = false;

			if(collided && targetPlayer != null) {
				if(chargeCooldown == 0) {
					chargeAngle = position.angleBetween(targetPlayer.position);
					updateVelocity = true;
				}

				else {
					velocity.setAngle(position.angleBetween(spawnPos), chargeSpeed);
				}
			}

			if(chargeSpeed < 12) {
				chargeSpeed += 0.1;
				updateVelocity = true;
			}

			if(updateVelocity) {
				velocity.setAngle(chargeAngle, chargeSpeed);
				chargeCooldown = 5;
			}

			if(chargeSpeed > 3.5) {
				animation.setSequence(1, true);
			}

			int rate = 18 - (int)chargeSpeed;
			if(timer % rate == 0) {
				world.spawn(new RedGemProjectile(world, position.x, position.y + mouthYOffset, chargeAngle + Math.PI, 1));
			}
		}

		else if(phase == 14) {
			chargeSpeed -= 0.1;
			chargeAngle = position.angleBetween(targetPlayer.position);
			if(collided) {
				velocity.set(0, 0);
				timer = 1;
			}
		}

		else if(phase == 16) {
			if(position.distBetween(spawnPos) < 10.0) {
				nextCycle();
			}
		}
	}

	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
		if(animation.getSequence() == 2 && animation.isDone()) {
			animation.setSequence(0, true);
		}

		for(int i = 0; i < slots.length; i++) {
			renderer.getSpriteBatch().draw(slots[i].getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)) + 6 + (i * 7), (float)(position.y - (animation.getFrameHeight() / 2)) + 21);
			slots[i].updateTimer();
		}
	}

	protected void onDeath() {
		SoundEffects.instance.play("boom", 1, 1, 0);
		for(int i = 0; i <= 18; i++) {
			double angle = Math.PI * 2 / 18 * i;
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 3.25));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle * 1.5, 3));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 2.75));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle * 1.5, 2.5));
			world.spawn(new CoinProjectile(world, position.x, position.y, angle, 2.25));
		}

		int x = world.getTileMap().getMapCoordinate(position.x), y = world.getTileMap().getMapCoordinate(position.y);
		world.getTileMap().setTile(x, y, (byte)-9);
		world.spawn(new Trapdoor(world, world.getTileMap().getWorldCoordinate(x), world.getTileMap().getWorldCoordinate(y)));
	}
}
