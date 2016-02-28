package org.whitehack97.rWorldGUI.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemExpander 
{
	private ItemStack ItemStack = null;
	private int Index = 0;
	private Inventory Inventory = null;
	private Location Location = null;
	private String Method = null;
	private List<String> Lore = new ArrayList<String>();

	public ItemExpander(ItemStack ItemStack)
	{
		this.ItemStack = ItemStack;
	}

	public void setIndex(int Index)
	{
		this.Index = Index;
	}

	public void setInventory(Inventory Inventory)
	{
		this.Inventory = Inventory;
	}

	public void setLocation(Location Location)
	{
		this.Location = Location;
	}

	public void setType(String Method)
	{
		this.Method = Method;
	}
	
	public ItemStack getItemStack()
	{
		return this.ItemStack;
	}
	
	public int getIndex()
	{
		return this.Index;
	}
	
	public Inventory getInventory()
	{
		return this.Inventory;
	}
	
	public Location getLocation()
	{
		return this.Location;
	}
	
	public String getType()
	{
		return this.Method;
	}
	
	public boolean hasInventory()
	{
		return this.Inventory == null ? false : true;
	}
	
	public boolean hasLocation()
	{
		return this.Location == null ? false : true;
	}
	
	public boolean hasIndex()
	{
		return this.Index == 0 ? false : true;
	}
	
	public boolean hasType()
	{
		try
		{
			return this.Method.equalsIgnoreCase(null) ? false : true;
		}
		catch(NullPointerException e)
		{
			return false;
		}
	}

	public void setLore(List<String> Lore)
	{
		this.Lore = Lore;
	}
	
	public List<String> getLore()
	{
		return this.Lore;
	}
	
	public boolean hasLore()
	{
		return this.Lore.size() == 0 ? false : true;
	}

}
