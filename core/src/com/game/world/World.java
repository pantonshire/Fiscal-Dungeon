package com.game.world;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game.entities.Coin;
import com.game.entities.Entity;
import com.game.entities.GoldCoin;
import com.game.entities.Player;
import com.game.entities.RedGem;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.vector.Vector;

public class World {

	private LayerRenderer gameRenderer;
	private LayerRenderer overlayRenderer;
	private boolean paused;
	private int gameOverTimer;
	private TileMap tiles;
	private Player player;
	private ArrayList<Entity> entities;
	private HashSet<Entity> spawnQueue;
	private ArrayList<Coin> coins;
	
	private Animation coin;

	public World(LayerRenderer gameRenderer, LayerRenderer overlayRenderer) {
		this.gameRenderer = gameRenderer;
		this.overlayRenderer = overlayRenderer;
		tiles = new TileMap(Textures.instance.getTexture("tilemap"), new byte[100][100], 20);
		entities = new ArrayList<Entity>();
		spawnQueue = new HashSet<Entity>();
		coins = new ArrayList<Coin>();

		createPlayer(0, 0);
		for(int i = 0; i < 100; i++) {
			spawn(new GoldCoin(this, 350, 300 + i * 10));
			spawn(new RedGem(this, 380, 300 + i * 10));
		}
		
		coin = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
	}

	public TileMap getTileMap() {
		return tiles;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Coin> getCoins() {
		return coins;
	}

	public void spawn(Entity entity) {
		spawnQueue.add(entity);
	}

	private void addEntity(Entity entity) {
		entities.add(entity);
		if(entity instanceof Coin) {
			coins.add((Coin)entity);
		}
	}

	private void createPlayer(float x, float y) {
		player = new Player(this, x, y);
		addEntity(player);
	}

	public void startGameOverTimer() {
		gameOverTimer = 120;
	}
	
	private void updateCamera() {
		OrthographicCamera camera = gameRenderer.getCamera();
		Vector cameraPos = player.getPosition().copy().add(0, 40);
		int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
		int minX = (int)(width / (2 / camera.zoom)), minY = (int)(height / (2 / camera.zoom));
		int maxX = tiles.getWidth() * tiles.getTileSize() - (int)(width / (2 / camera.zoom)), maxY = (tiles.getHeight() + 10) * tiles.getTileSize() - (int)(height / (2 / camera.zoom));
		if(cameraPos.x < minX) { cameraPos.x = minX; }
		else if(cameraPos.x > maxX) { cameraPos.x = maxX; }
		if(cameraPos.y < minY) { cameraPos.y = minY; }
		else if(cameraPos.y > maxY) { cameraPos.y = maxY; }
		gameRenderer.setCameraPosition(cameraPos);
	}

	public void update() {
		if(!paused) {
			for(Entity entity : entities) {
				entity.update();
			}

			for(int i = 0; i < entities.size(); i++) {
				if(entities.get(i).shouldRemove()) {
					if(entities.get(i) instanceof Coin) { coins.remove(entities.get(i)); }
					entities.remove(i);
					--i;
				}
			}

			if(!spawnQueue.isEmpty()) {
				for(Entity newEntity : spawnQueue) {
					addEntity(newEntity);
				}

				spawnQueue.clear();
			}
			
			updateCamera();
		}

		if(player.shouldRemove() && --gameOverTimer <= 0) {
			paused = true;
		}
	}

	public void render(int pass) {
		switch(pass) {
		case 0:
			renderGameLayer();
			break;
		case 1:
			renderOverlayLayer();
			break;
		default:
			break;
		}
	}

	private void renderGameLayer() {
		for(Entity entity : entities) {
			entity.render(gameRenderer);
		}
	}

	private void renderOverlayLayer() {
		overlayRenderer.getSpriteBatch().draw(coin.getFrame(), 20, Gdx.graphics.getHeight() - 40);
		coin.updateTimer();
		overlayRenderer.setTextColour(Color.BLACK);
		overlayRenderer.drawText("x " + player.getCoins(), 40, Gdx.graphics.getHeight() - 27);
	}
}