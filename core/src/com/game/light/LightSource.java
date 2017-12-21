package com.game.light;

import com.badlogic.gdx.graphics.Color;
import com.game.vector.Vector;

public interface LightSource {

	public Vector lightPosition();
	public int numLightRays();
	public float lightStrength();
	public Color lightColor();
}
