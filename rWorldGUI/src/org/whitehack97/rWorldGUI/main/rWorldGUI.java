package org.whitehack97.rWorldGUI.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import org.whitehack97.rWorldGUI.Listener.InventoryListener;
import org.whitehack97.rWorldGUI.Listener.PlayerListener;
import org.whitehack97.rWorldGUI.Listener.RefreshInventory;
import org.whitehack97.rWorldGUI.api.InventoryExpander;
import org.whitehack97.rWorldGUI.api.ItemExpander;
import org.whitehack97.rWorldGUI.config.InventoryConfig;

public class rWorldGUI extends JavaPlugin implements Listener
{
	public static Map<Inventory, Map<Integer, String>> InventoryExpandedItemIndexNames = new HashMap<Inventory, Map<Integer, String>>();
	public static Map<Inventory, List<Integer>> InventoryIncludedIndex = new HashMap<Inventory, List<Integer>>();
	public static Map<Inventory, Map<Integer, ItemExpander>> InventoryExpandedItems = new HashMap<Inventory, Map<Integer, ItemExpander>>();
	public static Map<String, Inventory> InventoryIndexNames = new HashMap<String, Inventory>();
	public static Map<String, Inventory> InventoryOpenCommands = new HashMap<String, Inventory>();
	public static Map<Inventory, InventoryExpander> ExpandedInventories = new HashMap<Inventory, InventoryExpander>();
	public static int TID = 0;

	public static rWorldGUI plugin;
	public static String Prefix = "」f[」1r」eW」forld」aGUI」f]」e ";
	
	@Override
	public void onEnable()
	{
		plugin = this;
		InventoryConfig.InventoryLoad();
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(rWorldGUI.plugin, new RefreshInventory(), 0L, 60L);
	}
	
	@Override
	public void onDisable()
	{
		/* Nothing */
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String cmd = command.getName();
		if(cmd.equalsIgnoreCase("rWorldGUI.main"))
		{
			Player player = (Player)sender;
			player.sendMessage(Prefix + InventoryOpenCommands.get(InventoryIndexNames.get(args[0])));
			player.openInventory(InventoryIndexNames.get(args[0]));
			return false;
		}
		return false;
	}
}
