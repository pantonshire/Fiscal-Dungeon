package com.game.input;

import com.game.vector.Vector;

public abstract class Input {

	public abstract double getXDirection();
	public abstract double getYDirection();
	public abstract Vector getTargetPos();
	public abstract boolean isPerformingAction(Action action);
	public abstract boolean isJustPerformingAction(Action action);
}
