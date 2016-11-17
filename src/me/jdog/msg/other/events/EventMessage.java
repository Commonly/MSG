package me.jdog.msg.other.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.jdog.msg.Main;

public class EventMessage implements Listener {

	Main plugin;

	public EventMessage(Main pl) {
		plugin = pl;
	}

	@EventHandler
	public void onMessage(EventMessageHandler e) {
		// later updates...
		if (e.getSender() instanceof Player) {
			// do something
		}
	}

}
