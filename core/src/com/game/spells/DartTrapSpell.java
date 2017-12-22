package com.game.spells;

import com.game.audio.SoundEffects;
import com.game.entities.DartTrap;
import com.game.entities.Player;
import com.game.level.Level;
import com.game.vector.Vector;

public class DartTrapSpell extends Spell {

	public DartTrapSpell() {
		super(750, 180);
	}

	protected void onUsed(Level world, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		int numDarts = 5;
		for(int i = 0; i < numDarts; i++) {
			world.spawn(new DartTrap(world, spawnPos.x, spawnPos.y, Math.PI * 2 / numDarts * i, 1.25, 9, 0.0125, 3600));
		}
		
		SoundEffects.instance.play("bow", 1, 1, 0);
	}
}
