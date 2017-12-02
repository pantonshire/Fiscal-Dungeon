package com.game.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.game.vector.Vector;

public class KeyboardInput extends Input {

	public static final int
	LEFT_BUTTON = -1,
	RIGHT_BUTTON = -2,
	MIDDLE_BUTTON = -3;

	private HashMap<Action, Integer> bindings;

	public KeyboardInput() {
		bindings = new HashMap<Action, Integer>();
	}

	public double getXDirection() {
		boolean left = Gdx.input.isKeyPressed(Keys.A),
				right = Gdx.input.isKeyPressed(Keys.D);
		if(left && !right) { return -1; }
		if(!left && right) { return 1; }
		return 0;
	}

	public double getYDirection() {
		boolean up = Gdx.input.isKeyPressed(Keys.W),
				down = Gdx.input.isKeyPressed(Keys.S);
		if(up && !down) { return 1; }
		if(!down && up) { return -1; }
		return 0;
	}

	public Vector getTargetPos() {
		return null;
	}

	public boolean isPerformingAction(Action action) {
		int binding = bindings.get(action);
		return binding < 0 ? Gdx.input.isButtonPressed(-binding - 1) : Gdx.input.isKeyPressed(binding); //This solution sucks but I HAVE NOOO TIME
		//Negative bindings are for mouse
	}

	public boolean isJustPerformingAction(Action action) {
		int binding = bindings.get(action);
		return binding < 0 ? Gdx.input.isButtonPressed(-binding) : Gdx.input.isKeyJustPressed(binding);
	}
}
