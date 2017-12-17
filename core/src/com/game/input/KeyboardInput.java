package com.game.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

public class KeyboardInput extends Input {

	public static final int
	LEFT_BUTTON = -1,
	RIGHT_BUTTON = -2,
	MIDDLE_BUTTON = -3;

	private HashMap<Action, Integer> bindings;

	public KeyboardInput() {
		bindings = new HashMap<Action, Integer>();
		bindings.put(Action.ATTACK, LEFT_BUTTON);
		bindings.put(Action.PAUSE, Keys.ESCAPE);
	}
	
	private Vector3 unprojectMousePos(LayerRenderer renderer, int x, int y) {
		return renderer.getCamera().unproject(new Vector3(x, y, 0));
	}
	
	public void update() {
		
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

	public Vector getTargetPos(Player player, LayerRenderer renderer) {
		int x = Gdx.input.getX(), y = Gdx.input.getY();
		Vector3 unprojected = unprojectMousePos(renderer, x, y);
		return new Vector(unprojected.x, unprojected.y);
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
