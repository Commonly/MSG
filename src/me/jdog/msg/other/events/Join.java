package me.jdog.msg.other.events;

import me.jdog.msg.gui.GuiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jdog.msg.Main;
import me.jdog.msg.other.commands.Options;

public class Join implements Listener {

	Main plugin;

	public Join(Main pl) {
		plugin = pl;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!plugin.dataManager.getData().isSet("auto"))
			return;
		if (Options.autoStaff.contains(e.getPlayer().getUniqueId().toString())) {
			e.getPlayer().performCommand("sc");
			plugin.MessageAPI(e.getPlayer(), "&cYou were automaticly put in staffchat!");
		}

	}

}
