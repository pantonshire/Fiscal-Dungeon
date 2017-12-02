package com.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.vector.Vector;

public class LayerRenderer implements Disposable {

	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Viewport viewport;
	private float zoom;
	private boolean cameraMoved;

	public LayerRenderer(int width, int height, float zoom) {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		this.zoom = zoom;
		camera.zoom = zoom;
		viewport = new ScreenViewport(camera);
		viewport.update(width, height);
		cameraMoved = true;
	}

	/** Must be called before any textures or text can be rendered. Updates the camera if it has been moved, binds the camera's position to the SpriteBatch, and tells the SpriteBatch to begin drawing. Note that {@link #endBatch()} must be called once you are finished rendering. */
	public void beginBatch() {
		if(cameraMoved) {
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			if(shapeRenderer != null) { shapeRenderer.setProjectionMatrix(camera.combined); }
			cameraMoved = false;
		}

		batch.begin();
	}

	/** Stops the SpriteBatch from drawing. Must be called before {@link #beginBatch()} can be called again. */
	public void endBatch() {
		batch.end();
	}
	
	/** If the layer has a shape renderer, calling this method will tell it to begin drawing. The shape renderer will always draw lines rather than solid shapes, because I say so. {@link #endShapeRendering()} must be called once you have finished rendering shapes. */
	public void beginShapeRendering() {
		if(shapeRenderer != null) {
			shapeRenderer.begin(ShapeType.Line);
		}
	}
	
	/** Tells the shape renderer to stop drawing, if one exists. This must be called before {@link #beginShapeRendering()} can be called again */
	public void endShapeRendering() {
		if(shapeRenderer != null) {
			shapeRenderer.end();
		}
	}

	/** Moves this layer's camera to the specified position. */
	public void setCameraPosition(Vector position) {
		setCameraPosition((float)position.x, (float)position.y);
	}

	/** Moves this layer's camera to the specified position. If you would rather pass in a single Vector2 object, use {@link #setCameraPosition(Vector2)} instead. */
	public void setCameraPosition(float x, float y) {
		if(camera.position.x != x || camera.position.y != y) {
			camera.position.set(x, y, 0);
			cameraMoved = true;
		}
	}
	
	public void translateCamera(Vector amount) {
		translateCamera((float)amount.x, (float)amount.y);
	}
	
	public void translateCamera(float x, float y) {
		setCameraPosition(camera.position.x + x, camera.position.y + y);
	}

	/** Sets the zoom of the camera. This will enlarge the image shown on the screen, where the scale factor is the zoom value and the centre is the centre of the screen. */
	public void setCameraZoom(float zoom) {
		if(camera.zoom != zoom) {
			camera.zoom = zoom;
			cameraMoved = true;
		}
	}
	
	/** Increases the value of zoom by the specified amount. min and max specify the upper and lower bounds. */
	public void zoomCamera(float amount, float min, float max) {
		setCameraZoom(MathUtils.clamp(camera.zoom + amount, min, max));
	}
	
	public float getDefaultZoom() {
		return zoom;
	}

	/** Call whenever the dimensions of the screen change to update the viewport and the camera. */
	public void updateDimensions(int width, int height) {
		viewport.update(width, height);
		setCameraPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public void setTextColour(Color colour) {
		if(font != null) {
			font.setColor(colour);
		}
	}
	
	public void drawText(String text, int x, int y) {
		if(font != null && batch.isDrawing()) {
			font.draw(batch, text, x, y);
		}
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}
}
