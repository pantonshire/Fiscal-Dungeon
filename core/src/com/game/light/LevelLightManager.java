package com.game.light;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.game.graphics.LayerRenderer;
import com.game.vector.Vector;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LevelLightManager implements Disposable {

	public static int DEFAULT_RADIAL_SOURCE = 128;
	
	private World world;
	private RayHandler rayHandler;
	private HashMap<LightSource, PointLight> fixedLights, temporaryLights;
	
	public LevelLightManager(World box2dWorld, Color ambientLight, boolean shadows) {
		world = box2dWorld;
		rayHandler = new RayHandler(world);
		rayHandler.setShadows(shadows);
		rayHandler.setAmbientLight(ambientLight);
		fixedLights = new HashMap<LightSource, PointLight>();
		temporaryLights = new HashMap<LightSource, PointLight>();
	}
	
	public void applyLight(LayerRenderer renderer) {
		rayHandler.setCombinedMatrix(renderer.getCamera());
		rayHandler.updateAndRender();
		removeTemporaryLights();
	}
	
	public void addFixedLight(LightSource source) {
		Vector pos = source.lightPosition();
		PointLight light = new PointLight(rayHandler, source.numLightRays(), source.lightColor(), source.lightStrength(), (float)pos.x, (float)pos.y);
		fixedLights.put(source, light);
	}
	
	public void removeFixedLight(LightSource source) {
		if(fixedLights.containsKey(source)) {
			fixedLights.get(source).remove();
			fixedLights.remove(source);
		}
	}
	
	public void addTemporaryLight(LightSource source) {
		Vector pos = source.lightPosition();
		PointLight light = new PointLight(rayHandler, source.numLightRays(), source.lightColor(), source.lightStrength(), (float)pos.x, (float)pos.y);
		temporaryLights.put(source, light);
	}
	
	private void removeTemporaryLights() {
		for(PointLight light : temporaryLights.values()) {
			light.remove();
		}
		
		temporaryLights.clear();
	}
	
	public void dispose() {
		rayHandler.dispose();
	}
}
