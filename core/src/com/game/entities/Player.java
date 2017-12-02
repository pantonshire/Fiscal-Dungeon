package com.game.entities;

import com.game.graphics.Animation;
import com.game.graphics.LayerRenderer;
import com.game.graphics.Sequence;
import com.game.graphics.TextureManager;
import com.game.world.World;

public class Player extends EntityLiving {

	private Animation animation;
	private int coins; //Basically health in this game lol
	
	public Player(World world, double x, double y, int width, int height, double walkSpeed) {
		super(world, x, y, width, height, walkSpeed);
		animation = new Animation(TextureManager.instance.getTexture("player"),
				Sequence.formatSequences(
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4),
						new Sequence(8, 20, 4, 4)));
	}
	
	protected void updateEntity() {
		
	}
	
	public void render(LayerRenderer renderer) {
		renderer.getSpriteBatch().draw(animation.getFrame(), (float)(position.x - (animation.getFrameWidth() / 2)), (float)(position.y - (animation.getFrameHeight() / 2)));
		animation.updateTimer();
	}
}
