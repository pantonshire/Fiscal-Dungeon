package com.game.run;

import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.entities.Player;
import com.game.level.Level;
import com.game.spells.Spell;

public class Run {

	public static Run currentRun;

	public static void newRun(Spell spell) {
		currentRun = new Run(spell);
	}
	
	public Spell spell;
	
	private int coins;
	private int maxCoins;
	private boolean dead;

	private Run(Spell spell) {
		coins = 0;
		maxCoins = 100;
		dead = false;
		this.spell = spell;
	}

	public void collectCoins(Level world, int amount) {
		coins += amount;
		if(coins >= maxCoins) {
			killPlayers(world);
		}

		if(coins < 0) {
			coins = 0;
		}
	}
	
	public int getCoins() {
		return coins;
	}
	
	public int getMaxCoins() {
		return maxCoins;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void killPlayers(Level world) {
		dead = true;
		coins = 0;
		world.startGameOverTimer();
		SoundEffects.instance.play("blast", 1, 1, 0);
		ArrayList<Player> players = world.getPlayers();
		for(Player player : players) {
			player.explode();
		}
	}
}
