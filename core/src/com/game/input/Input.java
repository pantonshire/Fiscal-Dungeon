package com.game.input;

import com.game.vector.Vector;

public abstract class Input {

	public static Input instance = new KeyboardInput();
	
	public abstract boolean up();
	public abstract boolean down();
	public abstract boolean left();
	public abstract boolean right();
	public abstract Vector getTargetPos();
	public abstract boolean isPerformingAction(Action action);
	public abstract boolean isJustPerformingAction(Action action);
}
