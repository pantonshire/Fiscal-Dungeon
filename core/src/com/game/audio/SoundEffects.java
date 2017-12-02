package com.game.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class SoundEffects implements Disposable {

	public static final SoundEffects instance = new SoundEffects();

	private HashMap<String, Sound> sounds;

	public SoundEffects() {
		sounds = new HashMap<String, Sound>();
	}

	public void loadSounds(String... soundsToLoad) {
		for(String sound : soundsToLoad) {
			sounds.put(sound, Gdx.audio.newSound(Gdx.files.internal("sfx/" + sound + ".wav")));
		}
	}
	
	public void dispose() {
		disposeAll();
	}
	
	public void dispose(String... soundsToDispose) {
		for(String sound : soundsToDispose) {
			if(sounds.containsKey(sound)) {
				sounds.get(sound).dispose();
				sounds.remove(sound);
			}
		}
	}
	
	public void disposeAll() {
		String[] soundNames = new String[sounds.keySet().size()];
		dispose(sounds.keySet().toArray(soundNames));
	}
	
	public void play(String soundName, float volume, float pitch, int pan) {
		Sound sound = sounds.get(soundName);
		if(sound != null) {
			sound.play(volume, pitch, pan);
		}
	}
}
