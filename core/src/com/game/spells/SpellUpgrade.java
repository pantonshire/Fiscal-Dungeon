package com.game.spells;

public enum SpellUpgrade {
	
	RICOCHET (3, "ricochet", "Magical projectiles will bounce off enemies and walls, resulting in utter carnage!"),
	SPEED (3, "projectile speed", "Magic projectiles will travel faster, increasing the overall efficiency of enemy annihilation."),
	HOMING (1, "homing", "Grants sentience to your magical projectiles, causing them to chase nearby enemies. Don\'t think too hard about the moral implications of this."),
	HEAVY (2, "heavy projectiles", "Increases the mass of your magical projectiles, causing them to do more damage to enemies they collide with. Unfortunately for you, due to conservation of momentum, they\'re also slower."),
	ACCURACY (3, "projectile accuracy", "Reduces the innacuracy of magical projectiles."),
	
	//Fireball specific
	HOT (1, "heat", "Hotter fireballs = more damage!"),
	
	//Warp specific
	CONTROL (3, "controlling trajectory", "Allows you to curve your trajectory while warping."),
	CONTACT_DAMAGE (3, "contact damage", "Warping through enemies will do some damage to them."),
	
	//Repel specific
	REPEL_RADIUS (3, "repel range", "Allows you to repel coins that are further away from you."),
	REPEL_FORCE (3, "repulsion", "Repel coins with more force to send them even futher away from you."),
	REPEL_DAMAGE(3, "repulsion damage", "Enemies within range of your repel spell will take some damage."),
	
	//Dart trap specific
	NUM_TRAPS (3, "trap potency", "Increases the number of dart traps you create, ensuring that whoever dares to go near will have a very bad day."),
	TRAP_RANGE (3, "trap range", "Dart traps will seek out enemies from longer distances."),
	
	//Portal specific
	RATE_OF_FIRE (3, "", ""),
	NUM_SHOTS (3, "", ""),
	PORTAL_RANGE (3, "", "")
	;
	
	public final int numLevels;
	public final String spellName;
	public final String description;
	
	private SpellUpgrade(int numLevels, String name, String description) {
		this.numLevels = numLevels;
		this.spellName = name;
		this.description = description;
	}
}
