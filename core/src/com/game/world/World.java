package com.game.world;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game.entities.Coin;
import com.game.entities.Enemy;
import com.game.entities.Entity;
import com.game.entities.Player;
import com.game.entities.TreasureChest;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.vector.Vector;

public class World {

	public LayerRenderer gameRenderer;
	public LayerRenderer overlayRenderer;
	public int difficulty;
	private boolean paused;
	private int gameOverTimer;
	private TileMap tiles;
	private Player player;
	private ArrayList<Entity> entities;
	private HashSet<Entity> spawnQueue;
	private ArrayList<Coin> coins;
	private ArrayList<Enemy> enemies;
	
	private Animation coin;

	public World(LayerRenderer gameRenderer, LayerRenderer overlayRenderer, int width, int height) {
		this.gameRenderer = gameRenderer;
		this.overlayRenderer = overlayRenderer;
		coin = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		entities = new ArrayList<Entity>();
		spawnQueue = new HashSet<Entity>();
		coins = new ArrayList<Coin>();
		enemies = new ArrayList<Enemy>();

		createPlayer(640, 160);
		
		tiles = TileMapFactory.newBlankMap("tilemap", (byte)1, 32, width, height);
		tiles = TileMapFactory.generateRandomMap(this, tiles, 4);
		
		spawn(new TreasureChest(this, 680, 160));
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
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void spawn(Entity entity) {
		spawnQueue.add(entity);
	}

	private void addEntity(Entity entity) {
		entities.add(entity);
		if(entity instanceof Coin) {
			coins.add((Coin)entity);
			if(coins.size() >= 500) {
				Coin toRemove = coins.get(0);
				entities.remove(toRemove);
				coins.remove(0);
			}
		}
		
		if(entity instanceof Enemy) {
			enemies.add((Enemy)entity);
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
					if(entities.get(i) instanceof Enemy) { enemies.remove(entities.get(i)); }
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
		
		if(gameOverTimer <= -120) {
			//Go to main menu
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
		tiles.render(gameRenderer);
		for(Entity entity : entities) {
			if(entity.isOnScreen(gameRenderer)) {
				entity.render(gameRenderer);
			}
		}
	}

	private void renderOverlayLayer() {
		overlayRenderer.getSpriteBatch().draw(coin.getFrame(), 330, Gdx.graphics.getHeight() / 2 + 180);
		coin.updateTimer();
		overlayRenderer.setTextColour(Color.WHITE);
		overlayRenderer.drawText("x " + player.getCoins(), 350, Gdx.graphics.getHeight() / 2 + 192);
		double weight = player.getCoins() * 12.0D / 100.0D;
		overlayRenderer.drawText("Weight: " + weight + " kg", 334, Gdx.graphics.getHeight() / 2 + 172);
		
		if(player.shouldRemove() && gameOverTimer < 0) {
			overlayRenderer.drawText("GAME OVER", Gdx.graphics.getWidth() / 2 - 60, Gdx.graphics.getHeight() / 2);
		}
		
		else if(paused) {
			overlayRenderer.drawText("PAUSED", Gdx.graphics.getWidth() / 2 - 60, Gdx.graphics.getHeight() / 2);
		}
	}
}
