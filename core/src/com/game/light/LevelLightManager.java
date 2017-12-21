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
	private HashMap<LightSource, PointLight> fixedLights, dynamicLights;
	
	public LevelLightManager(World box2dWorld, Color ambientLight, boolean shadows) {
		world = box2dWorld;
		RayHandler.useDiffuseLight(true);
//		RayHandler.setGammaCorrection(true);
		rayHandler = new RayHandler(world);
		rayHandler.setShadows(shadows);
		rayHandler.setBlur(true);
		rayHandler.setAmbientLight(ambientLight);
		fixedLights = new HashMap<LightSource, PointLight>();
		dynamicLights = new HashMap<LightSource, PointLight>();
	}
	
	public void applyLight(LayerRenderer renderer) {
		moveDynamicLights();
		rayHandler.setCombinedMatrix(renderer.getCamera());
		rayHandler.updateAndRender();
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
	
	public void addDynamicLight(LightSource source) {
		Vector pos = source.lightPosition();
		PointLight light = new PointLight(rayHandler, source.numLightRays(), source.lightColor(), source.lightStrength(), (float)pos.x, (float)pos.y);
		dynamicLights.put(source, light);
	}
	
	public void removeDynamicLight(LightSource source) {
		if(dynamicLights.containsKey(source)) {
			dynamicLights.get(source).remove();
			dynamicLights.remove(source);
		}
	}
	
	public void removeLight(LightSource source) {
		removeFixedLight(source);
		removeDynamicLight(source);
	}
	
	private void moveDynamicLights() {
		for(LightSource source : dynamicLights.keySet()) {
			PointLight light = dynamicLights.get(source);
			Vector pos = source.lightPosition();
			light.setPosition((float)pos.x, (float)pos.y);
		}
	}
	
	public void dispose() {
		rayHandler.dispose();
	}
}
