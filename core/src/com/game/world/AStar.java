package com.game.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.game.vector.Vector;

public class AStar {

	private TileMap tiles;
	
	public AStar(TileMap tiles) {
		this.tiles = tiles;
	}
	
	public ArrayList<Point> findPath(Vector startVec, Vector endVec, int maxSearchDistance, boolean diagonal) {
		Point start = new Point((int)(startVec.x / tiles.getTileSize()), (int)(startVec.y / tiles.getTileSize()));
		Point end = new Point((int)(endVec.x / tiles.getTileSize()), (int)(endVec.y / tiles.getTileSize()));
		
		if(tiles.isTileCollidable(start.x, start.y) || !tiles.isInMapBounds(start.x, start.y) || !tiles.isInMapBounds(end.x, end.y)) {
			return null;
		}
		
		ArrayList<Point> path = new ArrayList<Point>();
		HashMap<Point, Node> nodes = new HashMap<Point, Node>();
		ArrayList<Node> open = new ArrayList<Node>(), closed = new ArrayList<Node>();
		
		Node nStart = new Node(start.x, start.y);
		nodes.put(start, nStart);
		open.add(nStart);
		
		Node nEnd = new Node(end.x, end.y);
		nodes.put(end, nEnd);
		
		int maxDepth = 0;
		while((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			Node current = open.get(0);
			if(current == nEnd) {
				break;
			}
			
			open.remove(0);
			closed.add(current);
			
			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					if((x == 0) && (y == 0)) {
						continue;
					}
					
					if(!diagonal) {
						if((x != 0) && (y != 0)) {
							continue;
						}
					}
					
					int xp = x + current.x;
					int yp = y + current.y;
					
					if(isValidLocation(start.x, start.y, xp, yp)) {
						float nextStepCost = current.cost + 1;
						Node neighbour = getNode(nodes, new Point(xp, yp));
						
						if(nextStepCost < neighbour.cost) {
							if(open.contains(neighbour)) {
								open.remove(neighbour);
							}
							
							if(closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						
						if(!open.contains(neighbour) && !closed.contains(neighbour)) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getCost(xp, yp, end.x, end.y);
							maxDepth = Math.max(maxDepth, neighbour.setLast(current));
							open.add(neighbour);
						}
					}
				}
			}
		}

		if(nEnd.last == null) {
			return null;
		}
		
		Node targetNode = nEnd;
		while(targetNode != nStart) {
			path.add(0, new Point(targetNode.x, targetNode.y));
			targetNode = targetNode.last;
		}
		
		path.add(0, new Point(nStart.x, nStart.y));
		
		return path;
	}
	
	private Node getNode(HashMap<Point, Node> nodes, Point point) {
		if(!nodes.containsKey(point)) {
			Node node = new Node(point.x, point.y);
			nodes.put(point, node);
		}
		
		return nodes.get(point);
	}
	
	private float getCost(int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx * dx) + (dy * dy)));
		
		return result;
	}
	
	private boolean isValidLocation(int sx, int sy, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= tiles.getWidth()) || (y >= tiles.getHeight());
		
		if ((!invalid) && ((sx != x) || (sy != y))) {
			invalid = tiles.isTileCollidable(x, y);
		}
		
		return !invalid;
	}
}
