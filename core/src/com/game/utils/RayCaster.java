package com.game.utils;

import com.game.level.Level;
import com.game.vector.Vector;

public class RayCaster {

	public static boolean canSee(Level level, Vector start, Vector end, double cutoffDistance) {
		int tileSize = level.getTileMap().getTileSize();
		double startX = start.x / tileSize, startY = start.y / tileSize, endX = end.x / tileSize, endY = end.y / tileSize;
		
		double deltaX = Math.abs(endX - startX);
		double deltaY = Math.abs(endY - startY);

		if(cutoffDistance > 0 && (deltaX * tileSize > cutoffDistance || deltaY * tileSize > cutoffDistance)) {
			return false;
		}

		int x = (int)startX;
		int y = (int)startY;

		double inverseDeltaX = 1.0D / deltaX;
		double inverseDeltaY = 1.0D / deltaY;

		int n = 1;
		int xIncrement, yIncrement;
		double tNextVertical, tNextHorizontal;

		if(deltaX == 0) {
			xIncrement = 0;
			tNextHorizontal = inverseDeltaX;
		}
		
		else if(endX > startX) {
			xIncrement = 1;
			n += (int)endX - x;
			tNextHorizontal = ((int)startX + 1 - startX) * inverseDeltaX;
		}
		
		else {
			xIncrement = -1;
			n += x - (int)endX;
			tNextHorizontal = (startX - (int)startX) * inverseDeltaX;
		}

		if(deltaY == 0) {
			yIncrement = 0;
			tNextVertical = inverseDeltaY;
		}
		
		else if(endY > startY) {
			yIncrement = 1;
			n += (int)endY - y;
			tNextVertical = ((int)startY + 1 - startY) * inverseDeltaY;
		}
		
		else {
			yIncrement = -1;
			n += y - (int)endY;
			tNextVertical = (startY - (int)startY) * inverseDeltaY;
		}

		for(; n > 0; n--) {
			if(level.getTileMap().isTileCollidable(x, y)) {
				return false;
			}

			if(tNextVertical < tNextHorizontal) {
				y += yIncrement;
				tNextVertical += inverseDeltaY;
			}
			
			else {
				x += xIncrement;
				tNextHorizontal += inverseDeltaX;
			}
		}

		return true;
	}
}
