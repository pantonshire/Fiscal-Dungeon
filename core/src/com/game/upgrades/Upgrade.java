package com.game.upgrades;

public class Upgrade {

	private String icon;
	private String name;
	private String description;
	private Upgrade[] prerequisites;
	
	public Upgrade(String icon, String name) {
		this.icon = icon;
		this.name = name;
		this.description = "";
	}
	
	public Upgrade setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public Upgrade setPrerequisites(Upgrade... upgrades) {
		prerequisites = upgrades;
		return this;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Upgrade[] getPrerequisites() {
		return prerequisites;
	}
}
