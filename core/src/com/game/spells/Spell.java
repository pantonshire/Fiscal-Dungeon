package com.game.spells;

import java.util.HashMap;

import com.game.entities.Player;
import com.game.level.Level;

public abstract class Spell {

	protected int manaCost, cooldown;
	protected SpellUpgrade[] validUpgrades;
	protected HashMap<SpellUpgrade, Integer> upgrades;
	
	public Spell(int mana, int cooldown, SpellUpgrade... validUpgrades) {
		this.manaCost = mana;
		this.cooldown = cooldown;
		this.validUpgrades = validUpgrades;
		upgrades = new HashMap<SpellUpgrade, Integer>();
	}
	
	public SpellUpgrade[] getValidUpgrades() {
		return validUpgrades;
	}
	
	public boolean isValidUpgrade(SpellUpgrade upgrade) {
		for(int i = 0; i < validUpgrades.length; i++) {
			if(validUpgrades[i] == upgrade) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasUpgrade(SpellUpgrade upgrade) {
		return upgrades.containsKey(upgrade) && upgrades.get(upgrade) > 0;
	}
	
	public int getUpgradeLevel(SpellUpgrade upgrade) {
		return upgrades.containsKey(upgrade) ? upgrades.get(upgrade) : 0;
	}
	
	public void use(Level world, Player player) {
		if(player.getMana() > manaCost) {
			player.useMana(manaCost);
			player.setMagicCooldown(cooldown);
			onUsed(world, player);
		}
	}
	
	public void upgrade(SpellUpgrade upgrade) {
		if(!upgrades.containsKey(upgrade)) {
			upgrades.put(upgrade, 1);
		}
		
		else {
			int prevLevel = upgrades.get(upgrade);
			upgrades.replace(upgrade, prevLevel + 1);
		}
	}
	
	protected abstract void onUsed(Level world, Player player);
}
