package org.whitehack97.rWorldGUI.Listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import org.whitehack97.rWorldGUI.api.InventoryExpander;
import org.whitehack97.rWorldGUI.api.ItemExpander;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class InventoryListener implements Listener
{
	@EventHandler
	public void InventoryOpen(InventoryOpenEvent event)
	{
		Player player = (Player) event.getPlayer();
		Inventory Inventory = event.getInventory();
		if(rWorldGUI.InventoryExpandedItems.containsKey(Inventory))
		{
			InventoryExpander ExpandedInventory = rWorldGUI.ExpandedInventories.get(Inventory);
			if(ExpandedInventory.isSoundEnabled()) player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.2F, 1.2F);
			return;
		}
		else
		{
			return;
		}
	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent event)
	{
		Inventory Inventory = event.getInventory();
		if(rWorldGUI.InventoryExpandedItems.containsKey(Inventory))
		{
			ItemExpander ExtendedItem = null;
			if(rWorldGUI.InventoryExpandedItems.containsKey(event.getClickedInventory()) && event.getCurrentItem().hasItemMeta() && !(event.getCurrentItem().getType() == Material.AIR))
			{
				if(rWorldGUI.InventoryExpandedItems.get(Inventory).get(event.getSlot()).hasType())
				{
					if(rWorldGUI.InventoryExpandedItems.get(Inventory).get(event.getSlot()).getType().equalsIgnoreCase("CLOSE"))
					{
						event.getWhoClicked().closeInventory();
						return;
					}
				}
				else
				{
					ExtendedItem = rWorldGUI.InventoryExpandedItems.get(Inventory).get(event.getSlot());
				}
			}
			else
			{
				event.setCancelled(true);
				return;
			}
			event.getWhoClicked().teleport(ExtendedItem.getLocation());
			event.setCancelled(true);
		}
		else
		{
			return;
		}
	}
}
