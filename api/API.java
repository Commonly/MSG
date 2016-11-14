package me.jdog.msg.other.githubapi;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

/**
 * Basically the same methods I used in this plugin.
 * 
 * @author Muricans
 *
 */

public class API {

	/**
	 * 
	 * Usage: {@link API#message(Player, String)}
	 * 
	 * @param p
	 *            The player
	 * @param msg
	 *            The message sent to the player
	 */

	public void message(Player p, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		p.sendMessage(msg);
	}

	/**
	 * 
	 * Usage: {@link API#message(CommandSender, String)}
	 * 
	 * @param sender
	 *            The sender
	 * @param msg
	 *            The message sent to the sender
	 */

	public void message(CommandSender sender, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		sender.sendMessage(msg);
	}

	/**
	 * 
	 * Usage: {@link API#broadcast(String)}
	 * 
	 * @param msg
	 *            The message broadcasted to the Spigot server.
	 */

	public void broadcast(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		Bukkit.getServer().broadcastMessage(msg);
	}

}
