package org.whitehack97.rWorldGUI.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.whitehack97.rWorldGUI.main.rWorldGUI;

public class PlayerListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void PlayerCommand(PlayerCommandPreprocessEvent event)
	{
		if(rWorldGUI.InventoryOpenCommands.containsKey(event.getMessage()))
		{
			event.setCancelled(true);
			event.getPlayer().openInventory(rWorldGUI.InventoryOpenCommands.get(event.getMessage()));
		}
	}
}
