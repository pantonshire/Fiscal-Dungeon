package com.game.world;

public class Node implements Comparable<Node> {
	
	public int x, y;
	public float cost;
	public float heuristic;
	public int distance;
	public Node last;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int setLast(Node last) {
		distance = last.distance + 1;
		this.last = last;
		return distance;
	}

	public int compareTo(Node other) {
		float total = heuristic + cost;
		float otherTotal = other.heuristic + other.cost;
		if(total < otherTotal) { return -1; }
		else if(total > otherTotal) { return 1; }
		else { return 0; }
	}
}
