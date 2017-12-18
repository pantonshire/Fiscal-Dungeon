package com.game.currency;

import java.util.ArrayList;

import com.game.audio.SoundEffects;
import com.game.entities.Player;
import com.game.world.World;

public class Currency {

	public static Currency instance;

	public static void newInstance() {
		instance = new Currency();
	}
	
	private int coins;
	private int maxCoins;
	private boolean dead;

	private Currency() {
		coins = 0;
		maxCoins = 100;
		dead = false;
	}

	public void collectCoins(World world, int amount) {
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
	
	private void killPlayers(World world) {
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
