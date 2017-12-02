package com.game.utils;

public class AngleHelper {

	public static float correctAngle(float angle) {
		while(angle > 180) { angle -= 360; }
		while(angle <= -180) { angle += 360; }
		return angle;
	}
	
	public static boolean isValidAngle(float angle) {
		return angle <= 180 && angle > -180;
	}
}
