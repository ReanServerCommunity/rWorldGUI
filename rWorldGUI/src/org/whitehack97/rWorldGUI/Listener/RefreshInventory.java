package org.whitehack97.rWorldGUI.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.whitehack97.rWorldGUI.api.GlowEffect;
import org.whitehack97.rWorldGUI.api.ItemExpander;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class RefreshInventory implements Runnable
{
	@Override
	public void run()
	{
		Set<Inventory> Inventories = rWorldGUI.InventoryExpandedItems.keySet();
		for(Inventory Inv : Inventories)
		{
			for(Integer Index : rWorldGUI.InventoryIncludedIndex.get(Inv))
			{
				ItemStack ItemStack = Inv.getItem(Index);
				ItemMeta ItemMeta = Inv.getItem(Index).getItemMeta();
				ItemExpander ExpandedItem = rWorldGUI.InventoryExpandedItems.get(Inv).get(Index);
				List<String> Lore = ExpandedItem.getLore();
				if(! ExpandedItem.hasType())
				{
					World World = ExpandedItem.getLocation().getWorld();
					List<String> ReplaceLore = new ArrayList<String>();
					for(String Str : Lore)
					{
						if(Str.contains("%world_player%"))
						{
							ReplaceLore.add(Str.replaceAll("%world_player%", String.valueOf(World.getPlayers().size())));
						}
						else
						{
							ReplaceLore.add(Str);
						}
					}
					// CraftItemStack CraftItem = CraftItemStack.asCraftCopy(new org.bukkit.inventory.ItemStack(ItemStack));
					if(World.getPlayers().size() != 0)
					{
						// GlowEffect.addGlow(CraftItem);
						ItemStack.setAmount(World.getPlayers().size());
					}
					else
					{
						// GlowEffect.removeGlow(CraftItem);
						ItemStack.setAmount(1);
					}
					ItemMeta.setLore(ReplaceLore);
					ItemStack.setItemMeta(ItemMeta);
					Inv.setItem(Index, ItemStack);
				}
			}
		}
	}
}
