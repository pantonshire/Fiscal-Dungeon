package com.game.spells;

import static com.game.spells.SpellUpgrade.SPEED;
import static com.game.spells.SpellUpgrade.HEAVY;
import static com.game.spells.SpellUpgrade.HOMING;

import com.game.audio.SoundEffects;
import com.game.entities.MagicMissile;
import com.game.entities.Player;
import com.game.level.Level;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class MagicMissileSpell extends Spell {

	public MagicMissileSpell() {
		super(60, 7, SPEED, HOMING, HEAVY);
	}

	protected void onUsed(Level level, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		level.spawn(new MagicMissile(level, spawnPos.x, spawnPos.y, armRotation - Math.toRadians(5) + RandomUtils.randDouble(Math.toRadians(10)), getSpeed(), getUpgradeLevel(HOMING)));
		SoundEffects.instance.play("bow", 1, 1, 0);
	}
	
	private double getSpeed() {
		return 12 + (3 * getUpgradeLevel(SPEED)) - (4 * getUpgradeLevel(HEAVY));
	}
}
