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

	public boolean up() {
		return Gdx.input.isKeyPressed(Keys.W);
	}
	
	public boolean down() {
		return Gdx.input.isKeyPressed(Keys.S);
	}
	
	public boolean left() {
		return Gdx.input.isKeyPressed(Keys.A);
	}
	
	public boolean right() {
		return Gdx.input.isKeyPressed(Keys.D);
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
