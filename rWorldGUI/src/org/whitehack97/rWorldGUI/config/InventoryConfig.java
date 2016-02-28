package org.whitehack97.rWorldGUI.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import org.whitehack97.rWorldGUI.Util.FileManager;
import org.whitehack97.rWorldGUI.Util.MessageManager;
import org.whitehack97.rWorldGUI.Util.SectionManager;
import org.whitehack97.rWorldGUI.api.InventoryExpander;
import org.whitehack97.rWorldGUI.api.ItemExpander;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class InventoryConfig
{
	@SuppressWarnings("deprecation")
	public static void InventoryLoad()
	{
		YamlConfiguration File = FileManager.LoadSourceFile("Inventory");
		Set<String> Inventories = File.getKeys(false);
		for(String InventoryName : Inventories)
		{
			// ConfigurationSection InventorySection = SectionManager.addSection(File, InventoryName);
			
			Map<Integer, ItemExpander> ExpandedItems = new HashMap<Integer, ItemExpander>();
			Map<Integer, String> ExpandedItemIndexNames = new HashMap<Integer, String>();
			List<Integer> IncludedIndex = new ArrayList<Integer>();
	
			String Title = "rWorldGUI"; // Default Title
			int Rows = 1; // Default Rows
			
			if(File.contains(InventoryName + ".Name")) Title = File.getString(InventoryName + ".Name");
			if(File.contains(InventoryName + ".Rows")) Rows = File.getInt(InventoryName + ".Rows");
			Inventory Inventory = Bukkit.createInventory(null, Rows*9, ChatColor.translateAlternateColorCodes('&', Title));
	
			InventoryExpander ExpandedInv = new InventoryExpander(Inventory);
			if(File.contains(InventoryName + ".Command"))
			{
				ExpandedInv.setCommand(File.getString(InventoryName + ".Command"));
				rWorldGUI.InventoryOpenCommands.put("/" + File.getString(InventoryName + ".Command"), Inventory);
			}
			if(File.contains(InventoryName + ".Sound")) ExpandedInv.EnabledSound(File.getBoolean(InventoryName + ".Sound"));
			rWorldGUI.ExpandedInventories.put(Inventory, ExpandedInv);
			if(File.contains(InventoryName + ".Items"))
			{
				Set<String> IndexItemNames = File.getConfigurationSection(InventoryName + ".Items").getKeys(false);
				for(String ItemName : IndexItemNames)
				{
					ConfigurationSection itemSection = File.getConfigurationSection(InventoryName + ".Items." + ItemName);
					Location Location = new Location(null, 0, 0, 0); // Default location
					ItemStack ItemStack = new ItemStack(Material.BEDROCK); // Default location
					int ItemX = 0, ItemY = 0, ItemID = 1;
					byte Data = 0;
					ItemMeta ItemMeta = ItemStack.getItemMeta();

					if(itemSection.contains("NAME")) ItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemSection.getString("NAME")));
					if(itemSection.contains("ID")) ItemID = itemSection.getInt("ID");
					if(itemSection.contains("DATA-VALUE")) Data = Byte.parseByte(itemSection.getString("DATA-VALUE"));
					/*
					if(itemSection.contains("ENCHANTMENT"))
					{
						No exist code
					}
					*/
					if(itemSection.contains("WORLD")) Location.setWorld(Bukkit.getWorld(itemSection.getString("WORLD")));
					if(itemSection.contains("ITEM-X")) ItemX = itemSection.getInt("ITEM-X");
					if(itemSection.contains("ITEM-Y")) ItemY = itemSection.getInt("ITEM-Y");

					ItemStack = new MaterialData(ItemID, Data).toItemStack(1);
					ExpandedItemIndexNames.put((ItemX - 1)+((ItemY - 1)* 9), InventoryName + "." + ItemName);
					IncludedIndex.add((ItemX - 1)+((ItemY - 1)* 9));

					ItemExpander ExpandedItem = new ItemExpander(ItemStack);
					if(itemSection.contains("LORE"))
					{
						ExpandedItem.setLore(MessageManager.RepColorList(itemSection.getStringList("LORE")));
					}
					ItemStack.setItemMeta(ItemMeta);
					Inventory.setItem((ItemX - 1)+((ItemY - 1)* 9), new ItemStack(ItemStack));
					ExpandedItem.setIndex((ItemX - 1)+((ItemY - 1)* 9));
					ExpandedItem.setInventory(Inventory);
					if(itemSection.contains("TYPE"))
					{
						ExpandedItem.setType(itemSection.getString("TYPE"));
					}
					else
					{
						if(itemSection.contains("LOCATION.X")) Location.setX(Double.parseDouble(itemSection.getString("LOCATION.X")));
						if(itemSection.contains("LOCATION.Y")) Location.setY(Double.parseDouble(itemSection.getString("LOCATION.Y")));
						if(itemSection.contains("LOCATION.Z")) Location.setZ(Double.parseDouble(itemSection.getString("LOCATION.Z")));
						if(itemSection.contains("LOCATION.Yaw")) Location.setPitch(Float.parseFloat(itemSection.getString("LOCATION.Yaw")));
						if(itemSection.contains("LOCATION.Pitch")) Location.setPitch(Float.parseFloat(itemSection.getString("LOCATION.Pitch")));
						ExpandedItem.setLocation(Location);
					}
					ExpandedItems.put((ItemX - 1)+((ItemY - 1)* 9), ExpandedItem);
				}
			}
			rWorldGUI.InventoryExpandedItems.put(Inventory, ExpandedItems);
			rWorldGUI.InventoryIndexNames.put(InventoryName, Inventory);
			rWorldGUI.InventoryIncludedIndex.put(Inventory, IncludedIndex);
			rWorldGUI.InventoryExpandedItemIndexNames.put(Inventory, ExpandedItemIndexNames);
		}
	}
}
