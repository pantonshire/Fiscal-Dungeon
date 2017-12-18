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

	private int leftStickX, leftStickY, rightStickX, rightStickY;
	private HashMap<Integer, Action> bindings;
	private HashSet<Action> bindingsDown, bindingsJustDown;
	private Vector leftStick, rightStick;
	private Vector target;
	
	private static final double MIN_AXIS_MOVEMENT = 0.4;

	public ControllerInput(Controller controller) {
		bindings = new HashMap<Integer, Action>();
		bindingsDown = new HashSet<Action>();
		bindingsJustDown = new HashSet<Action>();
		leftStick = new Vector();
		rightStick = new Vector();
		target = new Vector();
		
		int[] bindingIDs = ControllerBindings.getBindings(controller);
		bindings.put(bindingIDs[0], Action.PAUSE);
		bindings.put(bindingIDs[1], Action.ATTACK);
		
		int[] analogueSticks = ControllerBindings.getAnalogueSticks(controller);
		leftStickX = analogueSticks[0];
		leftStickY = analogueSticks[1];
		rightStickX = analogueSticks[2];
		rightStickY = analogueSticks[3];
	}
	
	public void update() {
		if(!bindingsJustDown.isEmpty()) {
			bindingsJustDown.clear();
		}
	}

	public boolean up(byte id) {
		return leftStick.y < -MIN_AXIS_MOVEMENT;
	}
	
	public boolean down(byte id) {
		return leftStick.y > MIN_AXIS_MOVEMENT;
	}
	
	public boolean left(byte id) {
		return leftStick.x < -MIN_AXIS_MOVEMENT;
	}
	
	public boolean right(byte id) {
		return leftStick.x > MIN_AXIS_MOVEMENT;
	}

	public Vector getTargetPos(Player player, LayerRenderer renderer, byte id) {
		if(Math.abs(rightStick.x) > MIN_AXIS_MOVEMENT || Math.abs(rightStick.y) > MIN_AXIS_MOVEMENT) {
			target.set(rightStick.x, -rightStick.y);
		}
		return player.getPosition().copy().add(-3, 11).add(target);
	}

	public boolean isPerformingAction(Action action, byte id) {
		return bindingsDown.contains(action);
	}

	public boolean isJustPerformingAction(Action action, byte id) {
		return bindingsJustDown.contains(action);
	}
	
	public void connected(Controller controller) {
//		Input.setControllerInput(true);
	}
	
	public void disconnected(Controller controller) {
//		Input.setControllerInput(false);
	}
	
	public boolean buttonDown(Controller controller, int buttonCode) {
//		System.out.println(buttonCode + " down");
		Action action = bindings.get(buttonCode);
		if(action != null) {
			bindingsDown.add(action);
			bindingsJustDown.add(action);
		}
		
		return false;
	}
	
	public boolean buttonUp(Controller controller, int buttonCode) {
//		System.out.println(buttonCode + " up");
		Action action = bindings.get(buttonCode);
		if(action != null) {
			bindingsDown.remove(action);
			bindingsJustDown.remove(action);
		}
		
		return false;
	}
	
	public boolean axisMoved(Controller controller, int axisCode, float value) {
//		if(Math.abs(value) > 0.25) {
//			System.out.println(axisCode + " moved by " + value);
//		}
		
		if(axisCode == leftStickX) { leftStick.x = value; }
		else if(axisCode == leftStickY) { leftStick.y = value; }
		else if(axisCode == rightStickX) { rightStick.x = value; }
		else if(axisCode == rightStickY) { rightStick.y = value; }
		
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
