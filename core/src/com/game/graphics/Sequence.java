package com.game.graphics;

public class Sequence {
	
	private final int width, height;
	private final int delay;
	private final int numFrames;
	private int regionY;
	private boolean noLoop;
	private String name = "";
	
	public static Sequence[] formatSequences(Sequence... sequences) {
		for(int i = 0; i < sequences.length; i++) {
			if(i > 0) {
				sequences[i].setRegionY(sequences[i - 1].getNextSequenceY());
			}
		}
		
		return sequences;
	}
	
	public Sequence(int width, int height, int delay, int numFrames) {
		this.width = width;
		this.height = height;
		this.delay = delay;
		this.numFrames = numFrames;
	}
	
	public Sequence setNoLoop() {
		noLoop = true;
		return this;
	}
	
	public Sequence setName(String name) {
		this.name = name;
		return this;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getAnimationDelay() {
		return delay;
	}
	
	public int getNumFrames() {
		return numFrames;
	}
	
	public void setRegionY(int value) {
		regionY = value;
	}
	
	public int getRegionY() {
		return regionY;
	}
	
	public int getNextSequenceY() {
		return regionY + height;
	}
	
	public boolean noLoop() {
		return noLoop;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isCalled(String name) {
		return this.name.equals(name);
	}
	
	public boolean hasName() {
		return !name.isEmpty();
	}
}
