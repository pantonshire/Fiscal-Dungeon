package com.game.input;

import com.badlogic.gdx.controllers.Controllers;
import com.game.entities.Player;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

public abstract class Input {

	private static final KeyboardInput keyboard = new KeyboardInput();
	private static final ControllerInput controller = new ControllerInput();
	public static Input instance = keyboard;

	public static void init() {
		Controllers.addListener(controller);
		if(Controllers.getControllers().size > 0) {
			setControllerInput(true);
		}
	}

	public static void setControllerInput(boolean controllerInput) {
		instance = controllerInput ? controller : keyboard;
	}

	public abstract void update();
	public abstract boolean up();
	public abstract boolean down();
	public abstract boolean left();
	public abstract boolean right();
	public abstract Vector getTargetPos(Player player, LayerRenderer renderer);
	public abstract boolean isPerformingAction(Action action);
	public abstract boolean isJustPerformingAction(Action action);
}
