package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.game.audio.SoundEffects;
import com.game.graphics.Fonts;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Textures;
import com.game.input.Input;
import com.game.level.Level;
import com.game.level.LevelFactory;

public class Main extends ApplicationAdapter {
	
	private LayerRenderer gameRenderer;
	private LayerRenderer overlayRenderer;
	private LayerRenderer fontRenderer;
	private Level currentLevel;
	private int option;
	private boolean deleteLevel;
	
	public static Level nextLevel;
	private static int screen;
	private static Main instance;
	
	public void create() {
		instance = this;
		Fonts.instance.loadFont("november", 24);
		Fonts.instance.loadFont("pcsenior", 24);
		SoundEffects.instance.loadSounds("blast", "boom", "coin", "good", "hurt", "select", "coin_snake_die", "bow", "magic", "fire_blast");
		Input.init();
		gameRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		overlayRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0.5F);
		fontRenderer = new LayerRenderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1.0F);
	}
	
	public void render() {
		clearScreen();

		if(deleteLevel) {
			deleteLevel = false;
			deleteCurrentLevel();
		}
		
		if(nextLevel != null) {
			deleteCurrentLevel();
			currentLevel = nextLevel;
			nextLevel = null;
		}
		
		if(currentLevel != null) {
			currentLevel.update();
			gameRenderer.beginBatch();
			currentLevel.render(0);
			gameRenderer.endBatch();
			currentLevel.applyLight();
			overlayRenderer.beginBatch();
			currentLevel.render(1);
			overlayRenderer.endBatch();
			fontRenderer.beginBatch();
			currentLevel.render(2);
			fontRenderer.endBatch();
//			currentLevel.stepWorld();
		}
		
		else if(screen == 0) {
			if(Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
				SoundEffects.instance.play("select", 1, 1, 0);
				--option;
				if(option < 0) { option = 2; }
			}
			
			else if(Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
				SoundEffects.instance.play("select", 1, 1, 0);
				++option;
				if(option > 2) { option = 0; }
			}
			
			else if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				if(option != 2) {
					SoundEffects.instance.play("select", 1, 1, 0);
				}
				
				switch(option) {
				case 0:
					LevelFactory.firstFloor(gameRenderer, overlayRenderer, fontRenderer);
					break;
				case 1:
					screen = 2;
					break;
				case 2:
					Gdx.app.exit();
					break;
				default:
					break;
				}
			}
			
			Texture title = Textures.instance.getTexture("title");
			Texture pointer = Textures.instance.getTexture("pointer");
			Texture newGame = Textures.instance.getTexture("new_game");
			Texture help = Textures.instance.getTexture("help");
			Texture quit = Textures.instance.getTexture("quit");
			
			overlayRenderer.beginBatch();
			renderMenuBg();
			overlayRenderer.getSpriteBatch().draw(title, Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 120);
			overlayRenderer.getSpriteBatch().draw(newGame, Gdx.graphics.getWidth() / 2 - newGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 20);
			overlayRenderer.getSpriteBatch().draw(help, Gdx.graphics.getWidth() / 2 - newGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 30);
			overlayRenderer.getSpriteBatch().draw(quit, Gdx.graphics.getWidth() / 2 - newGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 80);
			overlayRenderer.getSpriteBatch().draw(pointer, Gdx.graphics.getWidth() / 2 - newGame.getWidth() / 2 - 20, Gdx.graphics.getHeight() / 2 + 24 - (50 * option));
			overlayRenderer.endBatch();
		}
		
		else if(screen == 1) {
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				screen = 0;
				SoundEffects.instance.play("select", 1, 1, 0);
			}
			
			overlayRenderer.beginBatch();
			renderMenuBg();
			overlayRenderer.endBatch();
			fontRenderer.beginBatch();
			String font = "november-24";
			fontRenderer.drawText("Congratulations on conquering the Fiscal Dungeon!", font, 340, 580);
			fontRenderer.drawText("Thank you for playing my game.", font, 340, 560);
			fontRenderer.drawText("Press the ESC key to return to the main menu.", font, 340, 540);
			fontRenderer.endBatch();
		}
		
		else if(screen == 2) {
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				screen = 0;
				SoundEffects.instance.play("select", 1, 1, 0);
			}
			
			overlayRenderer.beginBatch();
			renderMenuBg();
			overlayRenderer.endBatch();
			fontRenderer.beginBatch();
			String font = "pcsenior-24";
			fontRenderer.drawText("Welcome, brave adventurer, to the Fiscal Dungeon!", font, 40, 760);
			fontRenderer.drawText("Once you embark on your quest to conquer the dungeon, use the W, A, S and D keys to move,", font, 40, 720);
			fontRenderer.drawText("use your mouse to aim your bow and use left-click to shoot.", font, 40, 690);
			fontRenderer.drawText("Your quest will not be easy. The dungeon is full of gold and treasure, but beware; many", font, 40, 650);
			fontRenderer.drawText("believe it all to be cursed. After all, the theme of this Ludum Dare is \"the more you have, the", font, 40, 620);
			fontRenderer.drawText("worse it is\"!", font, 40, 590);
			fontRenderer.drawText("Obtaining 100 coins in the dungeon will likely result in your death.", font, 40, 550);
			fontRenderer.drawText("A polite reminder that, by dungeon law, adventurers are required to pay a \"murder tax\" of 10", font, 40, 500);
			fontRenderer.drawText("gold coins for each dungeon entity they slaugher on their travels. If you see any tax return", font, 40, 470);
			fontRenderer.drawText("documents, I implore you to do the lawful thing and pick them up in order to pay them.", font, 40, 440);
			fontRenderer.drawText("Press the ESC key to return to the main menu.", font, 40, 400);
			fontRenderer.endBatch();
		}
		
		Input.instance.update();
	}
	
	public void dispose() {
		gameRenderer.dispose();
		overlayRenderer.dispose();
		fontRenderer.dispose();
		Textures.instance.dispose();
		SoundEffects.instance.dispose();
		Fonts.instance.dispose();
	}
	
	private void deleteCurrentLevel() {
		if(currentLevel != null) {
			currentLevel.disposeLight();
			currentLevel = null;
		}
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	private void renderMenuBg() {
		Texture bg = Textures.instance.getTexture("bg");
		for(int x = 0; x < 20; x++) {
			for(int y = 0; y < 13; y++) {
				overlayRenderer.getSpriteBatch().draw(bg, x * bg.getWidth() + 320, y * bg.getWidth() + 200);
			}
		}
	}
	
	public static void toMainMenu() {
		screen = 0;
		instance.deleteLevel = true;
		instance.option = 0;
	}
	
	public static void toCongratulesScreen() {
		screen = 1;
		instance.deleteLevel = true;
	}
}
