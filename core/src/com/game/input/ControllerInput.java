package com.game.input;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

public class ControllerInput extends Input implements ControllerListener {

	private HashMap<Integer, Action> bindings;
	private HashSet<Action> bindingsDown, bindingsJustDown;
	private Vector leftStick, rightStick;
	private Vector target;
	
	private static final double MIN_AXIS_MOVEMENT = 0.4;

	public ControllerInput() {
		bindings = new HashMap<Integer, Action>();
		bindingsDown = new HashSet<Action>();
		bindingsJustDown = new HashSet<Action>();
		leftStick = new Vector();
		rightStick = new Vector();
		target = new Vector();
		
		bindings.put(4, Action.ATTACK);
		bindings.put(8, Action.PAUSE);
	}
	
	public void update() {
		if(!bindingsJustDown.isEmpty()) {
			bindingsJustDown.clear();
		}
	}

	public boolean up() {
		return leftStick.y < -MIN_AXIS_MOVEMENT;
	}
	
	public boolean down() {
		return leftStick.y > MIN_AXIS_MOVEMENT;
	}
	
	public boolean left() {
		return leftStick.x < -MIN_AXIS_MOVEMENT;
	}
	
	public boolean right() {
		return leftStick.x > MIN_AXIS_MOVEMENT;
	}

	public Vector getTargetPos(Player player, LayerRenderer renderer) {
		if(Math.abs(rightStick.x) > MIN_AXIS_MOVEMENT || Math.abs(rightStick.y) > MIN_AXIS_MOVEMENT) {
			target.x = rightStick.x * 50;
			target.y = rightStick.y * -50;
		}
		return player.getPosition().copy().add(target);
	}

	public boolean isPerformingAction(Action action) {
		return bindingsDown.contains(action);
	}

	public boolean isJustPerformingAction(Action action) {
		return bindingsJustDown.contains(action);
	}
	
	public void connected(Controller controller) {
		Input.setControllerInput(true);
		System.out.println("Connected " + controller.getName());
	}
	
	public void disconnected(Controller controller) {
		Input.setControllerInput(false);
		System.out.println("Disconnected " + controller.getName());
	}
	
	public boolean buttonDown(Controller controller, int buttonCode) {
		System.out.println(buttonCode + " down");
		Action action = bindings.get(buttonCode);
		if(action != null) {
			bindingsDown.add(action);
			bindingsJustDown.add(action);
		}
		
		return false;
	}
	
	public boolean buttonUp(Controller controller, int buttonCode) {
		System.out.println(buttonCode + " up");
		Action action = bindings.get(buttonCode);
		if(action != null) {
			bindingsDown.remove(action);
			bindingsJustDown.remove(action);
		}
		
		return false;
	}
	
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		if(Math.abs(value) > 0.25) {
			System.out.println(axisCode + " moved by " + value);
		}
		switch(axisCode) {
		case 0:
			leftStick.x = value;
			break;
		case 1:
			leftStick.y = value;
			break;
		case 3:
			rightStick.x = value;
			break;
		case 4:
			rightStick.y = value;
			break;
		default:
			break;
		}
		
		return false;
	}
	
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		return false;
	}
	
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}
	
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}
	
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		return false;
	}
}
