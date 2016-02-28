package org.whitehack97.rWorldGUI.api;

import org.bukkit.inventory.Inventory;

public class InventoryExpander
{
	private Inventory Inventory = null;
	private String Command = null;
	private boolean SoundEnabled = false;

	public InventoryExpander(Inventory Inventory)
	{
		this.Inventory = Inventory;
	}

	public void setCommand(String Command)
	{
		this.Command = Command;
	}

	public void EnabledSound(boolean Enabled)
	{
		this.SoundEnabled  = Enabled;
	}
	
	public boolean isSoundEnabled()
	{
		return this.SoundEnabled;
	}
	
	public boolean hasCommand()
	{
		return this.Command.equalsIgnoreCase(null) ? false : true;
	}
	
	public String getCommand()
	{
		return this.Command;
	}
	
	public Inventory getInventory()
	{
		return this.Inventory;
	}
	
}
