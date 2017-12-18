package com.game.input;

import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

public abstract class Input {

	private static final KeyboardInput keyboard = new KeyboardInput();
	private static final MasterControllerListener controller = new MasterControllerListener();
	public static Input instance = keyboard;

	public static void init() {
		controller.initListener();
	}

	public static void setControllerInput(boolean controllerInput) {
		instance = controllerInput ? controller : keyboard;
	}
	
	public static int getNumPlayers() {
		return controller.numConnectedControllers() > 0 ? controller.numConnectedControllers() : 1;
	}

	public abstract void update();
	public abstract boolean up(byte id);
	public abstract boolean down(byte id);
	public abstract boolean left(byte id);
	public abstract boolean right(byte id);
	public abstract Vector getTargetPos(Player player, LayerRenderer renderer, byte id);
	public abstract boolean isPerformingAction(Action action, byte id);
	public abstract boolean isJustPerformingAction(Action action, byte id);
}
