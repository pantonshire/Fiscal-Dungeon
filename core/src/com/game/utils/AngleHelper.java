package com.game.utils;

import com.game.vector.Vector;

public class AngleHelper {

	public static final double MAX_RAD = Math.PI;
	
	public static float correctAngleDegrees(float degrees) {
		while(degrees > 180) { degrees -= 360; }
		while(degrees <= -180) { degrees += 360; }
		return degrees;
	}
	
	public static boolean isValidAngleDegrees(float degrees) {
		return degrees <= 180 && degrees > -180;
	}
	
	public static double correctAngleRadians(double radians) {
		while(radians > MAX_RAD) { radians -= MAX_RAD * 2; }
		while(radians <= -MAX_RAD) { radians += MAX_RAD * 2; }
		return radians;
	}
	
	public static boolean isValidAngleRadians(double radians) {
		return radians <= MAX_RAD && radians > -MAX_RAD;
	}
	
	public static double angleDifferenceRadians(double angleA, double angleB) {
		double diffA = correctAngleRadians(angleA - angleB);
		double diffB = correctAngleRadians(angleB - angleA);
		return diffB < diffA ? diffB : diffA;
	}
	
	public static int getQuickestRotationDirection(double radians, double targetRadians) {
		double clockwiseAngle = correctAngleRadians(targetRadians - radians);
		double anticlockwiseAngle = correctAngleRadians(radians - targetRadians);
		return anticlockwiseAngle < clockwiseAngle ? 1 : -1;
	}
	
	public static double estimateAim(Vector position, double projectileSpeed, Vector targetPosition, Vector targetVelocity) {
		int heuristicTime = (int)Math.ceil(position.distBetween(targetPosition) / projectileSpeed);
		Vector estimatedFuturePosition = targetPosition.copy().add(targetVelocity.copy().mply(heuristicTime));
		double angle = position.angleBetween(estimatedFuturePosition);
		return angle;
	}
}
