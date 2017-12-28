package com.game.upgrades;

import java.util.ArrayList;

import com.game.run.Run;

public class UpgradeProperties {

	public static final int BASE_BOW_RATE = 24;
	public static final double BASE_ARROW_SPEED = 9.0;
	public static final double BASE_ARROW_DAMAGE = 2.0;
	
	public static int getBowRate() {
		ArrayList<Upgrade> upgrades = Run.currentRun.getUpgrades();
		double rate = BASE_BOW_RATE;
		for(Upgrade upgrade : upgrades) {
			if(upgrade == UpgradeList.SPLITTING_ARROW) { rate *= 1.5; }
			if(upgrade == UpgradeList.QUICK_QUIVER) { rate *= 0.85; }
			if(upgrade == UpgradeList.KLOBB_QUIVER) { rate *= 0.35; }
		}
		return (int)Math.max(rate, 1);
	}
	
	public static double getArrowSpeed() {
		ArrayList<Upgrade> upgrades = Run.currentRun.getUpgrades();
		double speed = BASE_ARROW_SPEED;
		for(Upgrade upgrade : upgrades) {
			if(upgrade == UpgradeList.CELERITY_ARROW) { speed += 5.0; }
			if(upgrade == UpgradeList.HOMING_ARROW) { speed -= 2.0; }
			if(upgrade == UpgradeList.KLOBB_QUIVER) { speed -= 4.0; }
		}
		return Math.max(speed, 3);
	}
	
	public static double getArrowDamage() {
		ArrayList<Upgrade> upgrades = Run.currentRun.getUpgrades();
		double damage = BASE_ARROW_DAMAGE;
		for(Upgrade upgrade : upgrades) {
//			if(upgrade == UpgradeList.DAMAGE) { damage += 3; }
			//Only increase damage
		}
		return Math.max(damage, 0.1);
	}
	
	public static double getArrowInnacuracy() {
		ArrayList<Upgrade> upgrades = Run.currentRun.getUpgrades();
		double innacuracy = 0;
		for(Upgrade upgrade : upgrades) {
			if(upgrade == UpgradeList.KLOBB_QUIVER) { innacuracy += 45.0; }
		}
		return Math.max(innacuracy, 3);
	}
}
