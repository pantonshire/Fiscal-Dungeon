package com.game.input;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

public class MasterControllerListener extends Input implements ControllerListener {
	
	private ArrayList<Controller> connectedControllers;
	private HashMap<Controller, ControllerInput> controllerListeners;
	
	public MasterControllerListener() {
		connectedControllers = new ArrayList<Controller>();
		controllerListeners = new HashMap<Controller, ControllerInput>();
	}
	
	public void initListener() {
		for(Controller controller : Controllers.getControllers()) {
			connected(controller);
		}
		
		Controllers.addListener(this);
	}
	
	public int numConnectedControllers() {
		return Math.min(connectedControllers.size(), 4);
	}
	
	public void connected(Controller controller) {
		System.out.println("Connected " + controller.getName());
		
		if(connectedControllers.size() == 0) {
			Input.setControllerInput(true);
		}
		
		connectedControllers.add(controller);
		ControllerInput listener = new ControllerInput(controller);
		controllerListeners.put(controller, listener);
		controller.addListener(listener);
	}
	
	public void disconnected(Controller controller) {
		System.out.println("Disconnected " + controller.getName());
		connectedControllers.remove(controller);
		controllerListeners.remove(controller);
		
		if(connectedControllers.size() == 0) {
			Input.setControllerInput(false);
		}
	}
	
	public void update() {
		for(ControllerInput listener : controllerListeners.values()) {
			listener.update();
		}
	}

	public boolean up(byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).up(id);
	}
	
	public boolean down(byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).down(id);
	}
	
	public boolean left(byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).left(id);
	}
	
	public boolean right(byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).right(id);
	}

	public Vector getTargetPos(Player player, LayerRenderer renderer, byte id) {
		if(connectedControllers.size() <= id) {
			return player.getPosition().copy();
		}
		
		return controllerListeners.get(connectedControllers.get(id)).getTargetPos(player, renderer, id);
	}

	public boolean isPerformingAction(Action action, byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).isPerformingAction(action, id);
	}

	public boolean isJustPerformingAction(Action action, byte id) {
		return connectedControllers.size() > id && controllerListeners.get(connectedControllers.get(id)).isJustPerformingAction(action, id);
	}
	
	public boolean buttonDown(Controller controller, int buttonCode) {
		return false;
	}
	
	public boolean buttonUp(Controller controller, int buttonCode) {
		return false;
	}
	
	public boolean axisMoved(Controller controller, int axisCode, float value) {
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
