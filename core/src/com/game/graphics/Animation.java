package com.game.graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

	private TextureRegion textureRegion;
	private Sequence[] sequences;
	private HashMap<String, Integer> sequenceNames;
	private int sequence, frame;
	private int timer;
	private int loops;
	private boolean paused;
	private boolean reversed;
	private boolean regionChanged;

	public Animation(Texture texture, Sequence... sequences) {
		textureRegion = new TextureRegion(texture);
		sequenceNames = new HashMap<String, Integer>();
		this.sequences = sequences;
		updateRegion();

		for(int i = 0; i < sequences.length; i++) {
			if(sequences[i].hasName()) {
				sequenceNames.put(sequences[i].getName(), i);
			}
		}
	}

	/** Gets the texture region cropped for the current frame. Calling this method will cause the texture region to be updated if any change has been made to the frame or sequence of the animation. */
	public TextureRegion getFrame() {
		if(regionChanged) {
			updateRegion();
		}

		return textureRegion;
	}

	/** Call after the current frame has been drawn to update the animation. */
	public void updateTimer() {
		//Sequence will not update if animation delay is less than or equal to 0
		if(shouldUpdate() && ++timer >= sequences[sequence].getAnimationDelay()) {
			timer = 0;
			nextFrame();
		}
	}

	/** Advances the current sequence to the next frame. Frame will be reset to 0 if it exceeds the total number of frames in the current sequence. */
	private void nextFrame() {
		regionChanged = true;
		if(!reversed) {
			if(++frame >= sequences[sequence].getNumFrames()) {
				frame = 0;
				++loops;
			}
		}

		else {
			if(--frame < 0) {
				frame = sequences[sequence].getNumFrames() - 1;
				++loops;
			}
		}
	}

	/** Updates the texture region based on the current frame and sequence. Calling this will crop the texture region for the current frame. */
	private void updateRegion() {
		Sequence currentSequence = sequences[sequence];
		textureRegion.setRegion(frame * currentSequence.getWidth(), currentSequence.getRegionY(), currentSequence.getWidth(), currentSequence.getHeight());
		regionChanged = false;
	}

	private boolean shouldUpdate() {
		return !paused && sequences[sequence].getAnimationDelay() > 0 && !(sequences[sequence].noLoop() && frame >= sequences[sequence].getNumFrames() - 1);
	}
	
	public void jumpToFrame(int frame) {
		this.frame = frame;
		timer = 0;
		regionChanged = true;
	}
	
	public void resetFrame() {
		frame = 0;
		timer = 0;
		loops = 0;
		regionChanged = true;
	}

	/** Sets the animation sequence to a specified value. Calling this method will force the texture region to be updated when getFrame is next called. Boolean determines whether the frame should be reset to 0 or not. */
	public void setSequence(int sequence, boolean resetFrame) {
		if(sequence != this.sequence) {
			if(resetFrame) {
				resetFrame();
				reversed = false;
			}

			this.sequence = sequence;
			regionChanged = true;
		}
	}

	public void setSequence(String sequenceName, boolean resetFrame) {
		if(sequenceNames.containsKey(sequenceName)) {
			setSequence(sequenceNames.get(sequenceName), resetFrame);
		}

		else {
			System.err.println("No such sequence: " + sequenceName);
		}
	}

	public int getSequence() {
		return sequence;
	}

	public String getSequenceName() {
		return sequences[sequence].getName();
	}

	public int getFrameNumber() {
		return frame;
	}

	public int getFrameWidth() {
		return textureRegion.getRegionWidth();
	}

	public int getFrameHeight() {
		return textureRegion.getRegionHeight();
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

	public boolean reverse() {
		reversed = !reversed;
		return reversed;
	}

	public int getNumLoops() {
		return loops;
	}
	
	public void togglePaused() {
		paused = !paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public boolean isPaused() {
		return paused;
	}

	public boolean isDone() {
		return !shouldUpdate();
	}
}
