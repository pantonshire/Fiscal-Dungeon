package com.game.spells;

import com.game.entities.Player;
import com.game.level.Level;

public abstract class Spell {

	protected int manaCost, cooldown;
	
	public Spell(int mana, int cooldown) {
		this.manaCost = mana;
		this.cooldown = cooldown;
	}
	
	public void use(Level world, Player player) {
		if(player.getMana() > manaCost) {
			player.useMana(manaCost);
			player.setMagicCooldown(cooldown);
			onUsed(world, player);
		}
	}
	
	protected abstract void onUsed(Level world, Player player);
}
