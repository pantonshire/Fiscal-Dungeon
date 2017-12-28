package com.game.spells;

import com.game.audio.SoundEffects;
import com.game.entities.Player;
import com.game.entities.projectiles.Fireball;
import com.game.level.Level;
import com.game.vector.Vector;
import static com.game.spells.SpellUpgrade.*;

public class FireballSpell extends Spell {

	public FireballSpell() {
		super(500, 60, HOT, SPEED, HOMING, HEAVY);
	}

	protected void onUsed(Level level, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		level.spawn(new Fireball(level, spawnPos.x, spawnPos.y, armRotation, getSpeed(), getUpgradeLevel(HOMING)));
		SoundEffects.instance.play("magic", 1, 1, 0);
	}
	
	private double getSpeed() {
		return 5 + (2 * getUpgradeLevel(SPEED)) - (3 * getUpgradeLevel(HEAVY));
	}
}
