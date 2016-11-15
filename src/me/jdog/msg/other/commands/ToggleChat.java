package me.jdog.msg.other.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jdog.msg.Main;
import net.md_5.bungee.api.ChatColor;

public class ToggleChat implements CommandExecutor {

	private Main plugin;

	public ToggleChat(Main pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String chatMuted = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("chat.disabled").replace("%sender%", sender.getName()));
		String chatUnmuted = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("chat.enabled").replace("%sender%", sender.getName()));
		if (cmd.getName().equalsIgnoreCase("togglechat")) {
			if (plugin.isChatEnabled()) {
				plugin.allowChat(false);
				plugin.broadcast(chatMuted);
				return true;
			} else {
				plugin.allowChat(true);
				plugin.broadcast(chatUnmuted);
				return true;
			}
		}
		return true;
	}

}
