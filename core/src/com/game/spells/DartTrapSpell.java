package com.game.spells;

import com.game.audio.SoundEffects;
import com.game.entities.DartTrap;
import com.game.entities.Player;
import com.game.level.Level;
import com.game.vector.Vector;
import static com.game.spells.SpellUpgrade.*;

public class DartTrapSpell extends Spell {

	public DartTrapSpell() {
		super(750, 180, FAST, HEAVY, NUM_TRAPS, TRAP_RANGE);
	}

	protected void onUsed(Level world, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		int numDarts = getNumDarts();
		for(int i = 0; i < numDarts; i++) {
			world.spawn(new DartTrap(world, spawnPos.x, spawnPos.y, Math.PI * 2 / numDarts * i, 1.25, getSpeed(), 0.0125, 3600, getRange()));
		}
		
		SoundEffects.instance.play("bow", 1, 1, 0);
	}
	
	private double getSpeed() {
		return Math.max(5 + (3 * getUpgradeLevel(FAST)) - (4 * getUpgradeLevel(HEAVY)), 0.5);
	}
	
	private double getRange() {
		return 160 + (32 * getUpgradeLevel(TRAP_RANGE));
	}
	
	private int getNumDarts() {
		return 4 + getUpgradeLevel(NUM_TRAPS);
	}
}
