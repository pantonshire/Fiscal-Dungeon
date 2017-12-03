package com.game.utils;

import java.util.Random;

import com.game.vector.Vector;

public class RandomUtils {

	private static final Random random = new Random();
	
	public static boolean randBoolean() {
		return random.nextBoolean();
	}
	
	public static int randInt() {
		return random.nextInt();
	}
	
	public static int randInt(int max) {
		return random.nextInt(max + 1);
	}
	
	public static int randIntExclusive(int max) {
		return random.nextInt(max);
	}
	
	public static int randInt(int min, int max) {
		return min + random.nextInt(max - min + 1);
	}
	
	public static double randDouble() {
		return random.nextDouble();
	}
	
	public static double randDouble(double max) {
		return random.nextDouble() * max;
	}
	
	public static double randDouble(double min, double max) {
		return min + randDouble(max - min);
	}
	
	public static double randAngle() {
		return random.nextDouble() * Math.PI * 2;
	}
	
	public static Vector randVector(double minDistance, double maxDistance) {
		return new Vector().setAngle(randAngle(), randDouble(minDistance, maxDistance));
	}
}
