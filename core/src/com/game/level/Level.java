package com.game.level;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.game.Main;
import com.game.entities.Coin;
import com.game.entities.Enemy;
import com.game.entities.Entity;
import com.game.entities.Player;
import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.Textures;
import com.game.input.Action;
import com.game.input.Input;
import com.game.light.LevelLightManager;
import com.game.light.LightSource;
import com.game.rooms.BossRoom;
import com.game.run.Run;
import com.game.utils.RandomUtils;
import com.game.vector.Vector;

public class Level {

	public LayerRenderer gameRenderer;
	public LayerRenderer overlayRenderer;

	private boolean paused;
	private int gameOverTimer;
	private int fadeOut, fadeIn, roomTitle;

	private TileMap tiles;
	private Player player1;
	private ArrayList<Player> players;
	private ArrayList<Entity> entities;
	private HashSet<Entity> spawnQueue;
	private ArrayList<Coin> coins;
	private ArrayList<Enemy> enemies;

	private World world;
	private LevelLightManager light;
	private float ambientLightLevel;
	
	private Animation coin;
	private TextureRegion manaBar;

	public Level(LayerRenderer gameRenderer, LayerRenderer overlayRenderer, int width, int height, boolean boss) {
		this.gameRenderer = gameRenderer;
		this.overlayRenderer = overlayRenderer;
		
		fadeOut = -1;
		fadeIn = 60;
		roomTitle = 240;
		
		coin = new Animation(Textures.instance.getTexture("coin"), Sequence.formatSequences(new Sequence(14, 14, 6, 8)));
		manaBar = new TextureRegion(Textures.instance.getTexture("magic_meter"));
		
		entities = new ArrayList<Entity>();
		spawnQueue = new HashSet<Entity>();
		coins = new ArrayList<Coin>();
		enemies = new ArrayList<Enemy>();
		players = new ArrayList<Player>();

		world = new World(new Vector2(0, 0), true);
		ambientLightLevel = 0.25F;
		light = new LevelLightManager(world, new Color(ambientLightLevel, ambientLightLevel, ambientLightLevel, ambientLightLevel), true);
		
		if(boss) { createPlayers(1280, 560); }
		else { createPlayers(732, 256); }

		tiles = TileMapFactory.newBlankMap(LevelFactory.getTileset(), (byte)1, 32, width, height);

		if(boss) { TileMapFactory.insertRoom(tiles, new BossRoom(this), this, 21, 5, LevelFactory.floor); }
		else { tiles = TileMapFactory.generateRandomMap(this, tiles, 4, LevelFactory.floor); }
	}

	public TileMap getTileMap() {
		return tiles;
	}
	
	public LevelLightManager getLightManager() {
		return light;
	}
	
	public float getAmbientLightLevel() {
		return ambientLightLevel;
	}
	
	public float getPlayerLightLevel() {
		return 1 - ambientLightLevel;
	}

	public ArrayList<Player> getPlayers() {
		return players;
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
			if(coins.size() >= 5000) {
				Coin toRemove = coins.get(0);
				entities.remove(toRemove);
				coins.remove(0);
			}
		}

		if(entity instanceof Enemy) {
			enemies.add((Enemy)entity);
		}
	}

	private void createPlayers(float x, float y) {
		int numPlayers = Input.getNumPlayers();
		for(byte id = 0; id < numPlayers; id++) {
			Player player = new Player(this, x, y, id);

			if(id == 0) {
				player1 = player;
			}

			else {
				player.getPosition().add(RandomUtils.randVector(16, 32));
			}

			players.add(player);
			addEntity(player);
		}
	}

	public void startGameOverTimer() {
		gameOverTimer = 120;
	}

	public void nextRoom() {
		fadeOut = 60;
	}

	private void updateCamera() {
		OrthographicCamera camera = gameRenderer.getCamera();
		Vector cameraPos = player1.getPosition().copy().add(0, 40);
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
		if(fadeOut == -1 && fadeIn == 0 && !Run.currentRun.isDead() && Input.instance.isJustPerformingAction(Action.PAUSE, Player.PLAYER_1)) {
			paused = !paused;
			for(Player player : players) {
				player.setStopped();
			}
		}

		if(!paused && fadeOut == -1) {
			for(Entity entity : entities) {
				entity.update();
			}

			for(int i = 0; i < entities.size(); i++) {
				if(entities.get(i).shouldRemove()) {
					if(entities.get(i) instanceof Coin) { coins.remove(entities.get(i)); }
					else if(entities.get(i) instanceof Enemy) { enemies.remove(entities.get(i)); }
					if(entities.get(i) instanceof LightSource) { light.removeLight((LightSource)entities.get(i)); }
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

		if(Run.currentRun.isDead() && --gameOverTimer <= 0) {
			paused = true;
		}

		if(gameOverTimer <= -240) {
			Main.toMainMenu();
		}

		if(fadeOut > 0 && --fadeOut == 0) {
			LevelFactory.nextFloor(this);
		}

		if(fadeIn > 0) {
			--fadeIn;
		}
	}
	
	public void stepWorld() {
		world.step(1/60F, 6, 2);
	}
	
	public void applyLight() {
		light.applyLight(gameRenderer);
	}
	
	public void disposeLight() {
		light.dispose();
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
		Color color = gameRenderer.getSpriteBatch().getColor();
		float fade = fadeOut >= 0 ? (float)fadeOut / 59.0F : (fadeIn > 0 ? (float)(59 - fadeIn) / 59.0F : -1);

		if(fade != -1) {
			gameRenderer.getSpriteBatch().setColor(new Color(color.r * fade, color.g * fade, color.b * fade, color.a));
		}

		tiles.render(gameRenderer);
		for(Entity entity : entities) {
			if(entity.isOnScreen(gameRenderer)) {
				entity.render(gameRenderer);
			}
		}

		if(fade != -1) {
			gameRenderer.getSpriteBatch().setColor(color);
		}
	}

	private void renderOverlayLayer() {
		renderManaBar();
		overlayRenderer.getSpriteBatch().draw(coin.getFrame(), 330, Gdx.graphics.getHeight() / 2 + 180);
		coin.updateTimer();
		overlayRenderer.setTextColour(Color.WHITE);
		overlayRenderer.drawText("x " + Run.currentRun.getCoins(), 350, Gdx.graphics.getHeight() / 2 + 192);
		overlayRenderer.drawText("/ " + Run.currentRun.getMaxCoins(), 380, Gdx.graphics.getHeight() / 2 + 192);

		if(roomTitle > 0) {
			--roomTitle;
			String text = "Floor " + (LevelFactory.floor + 1) + ": " + LevelFactory.getFloorName();
			GlyphLayout layout = new GlyphLayout(overlayRenderer.getFont(), text);
			int x = (int)(Gdx.graphics.getWidth() / 2 - layout.width / 2), y = (int)(Gdx.graphics.getHeight() / 2 - layout.height / 2) + 40;
			overlayRenderer.drawText(text, x, y);
		}

		if(Run.currentRun.isDead() && gameOverTimer <= 0) {
			String text = "GAME OVER";
			GlyphLayout layout = new GlyphLayout(overlayRenderer.getFont(), text);
			int x = (int)(Gdx.graphics.getWidth() / 2 - layout.width / 2), y = (int)(Gdx.graphics.getHeight() / 2 - layout.height / 2) + 40;
			overlayRenderer.drawText(text, x, y);
		}

		else if(paused) {
			String text = "PAUSED";
			GlyphLayout layout = new GlyphLayout(overlayRenderer.getFont(), text);
			int x = (int)(Gdx.graphics.getWidth() / 2 - layout.width / 2), y = (int)(Gdx.graphics.getHeight() / 2 - layout.height / 2) + 40;
			overlayRenderer.drawText(text, x, y);
		}
	}

	private void renderManaBar() {
		manaBar.setRegion(0, 0, 150, 11);
		int x = Gdx.graphics.getWidth() / 2 + 160;
		int y = Gdx.graphics.getHeight() / 2 + 180;
		overlayRenderer.getSpriteBatch().draw(manaBar, x, y);
		double percentageMana = player1.getManaPercentage();
		int width = (int)(150 * percentageMana);
		manaBar.setRegion(0, Run.currentRun.spell.hasSufficientMana(player1) ? 11 : 18, width, 7);
		overlayRenderer.getSpriteBatch().draw(manaBar, x, y + 2);
	}
}
