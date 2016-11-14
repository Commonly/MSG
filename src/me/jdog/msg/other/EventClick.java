package me.jdog.msg.other;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.jdog.msg.Main;

public class EventClick implements Listener {
	
	Main plugin;
	
	public EventClick(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getName().equalsIgnoreCase("Message Panel")) {
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(!e.getCurrentItem().hasItemMeta())
				return;
			switch (e.getCurrentItem().getType()) {
			case EMERALD_BLOCK:
				p.performCommand("mo auto join");
				break;
			case REDSTONE_BLOCK:
				p.performCommand("mo auto leave");
				break;
			case BLAZE_ROD:
				p.performCommand("sc");
				break;
			default:
				break;
			}
		}
	}

}
