package com.game.spells;

import com.game.audio.SoundEffects;
import com.game.entities.Fireball;
import com.game.entities.Player;
import com.game.level.Level;
import com.game.vector.Vector;

public class FireballSpell extends Spell {

	public FireballSpell() {
		super(150, 120);
	}

	protected void onUsed(Level world, Player player) {
		double armRotation = player.getArmRotation();
		Vector spawnPos = (new Vector()).setAngle(armRotation, 8).add(player.getPosition()).add(-3, 5);
		world.spawn(new Fireball(world, spawnPos.x, spawnPos.y, armRotation, 5));
		SoundEffects.instance.play("magic", 1, 1, 0);
	}
}
