package com.game.vector;

import java.awt.Point;

public class Vector {

	public double x;
	public double y;

	public Vector() {
		this(0, 0);
	}
	
	public Vector(Point point) {
		this(point.x, point.y);
	}

	public Vector(double x, double y) {
		set(x, y);
	}

	public Vector copy() {
		return new Vector(x, y);
	}

	public Vector set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	/** Sets the vector based on the angle, in radians and relative to the x axis, and the distance from the origin. */
	public Vector setAngle(double angle, double distance) {
		return set(Math.cos(angle) * distance, Math.sin(angle) * distance);
	}

	public Vector setX(double x) {
		this.x = x;
		return this;
	}

	public Vector setY(double y) {
		this.y = y;
		return this;
	}

	public Vector add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector add(Vector vector) {
		add(vector.x, vector.y);
		return this;
	}
	
	/** Adds to the vector by the specified distance in the direction of the specified angle, in radians and relative to the x axis. */
	public Vector addAngle(double angle, double distance) {
		return add(Math.cos(angle) * distance, Math.sin(angle) * distance);
	}

	public Vector sub(double x, double y) {
		return add(-x, -y);
	}

	public Vector sub(Vector vector) {
		sub(vector.x, vector.y);
		return this;
	}

	public Vector round() {
		x = Math.round(x);
		y = Math.round(y);
		return this;
	}

	public Vector floor() {
		x = (int)x;
		y = (int)y;
		return this;
	}

	public double dist() {
		return Math.sqrt(x * x + y * y);
	}

	public double distSq() {
		return x * x + y * y;
	}
	
	public double distBetween(Vector other) {
		double deltaX = x - other.x;
		double deltaY = y - other.y;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	public double distSqBetween(Vector other) {
		double deltaX = x - other.x;
		double deltaY = y - other.y;
		return deltaX * deltaX + deltaY * deltaY;
	}
	
	public double angleBetween(Vector other) {
		return Math.atan2(other.y - y, other.x - x);
	}

	public boolean isZero() {
		return x == 0 && y == 0;
	}
}
