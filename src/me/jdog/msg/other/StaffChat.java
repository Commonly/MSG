package me.jdog.msg.other;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdog.msg.Main;
import net.md_5.bungee.api.ChatColor;

public class StaffChat implements CommandExecutor {

	Main plugin;

	public StaffChat(Main pl) {
		plugin = pl;
	}

	public static ArrayList<Player> chat = new ArrayList<Player>();
	public static ArrayList<Player> sc = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String scEnabled = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("staffchat.enabled"));
		String scDisabled = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("staffchat.disabled"));
		String notEnabled = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("staff-chat-not-enabled").replace("%player%", sender.getName()));
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("staffchat")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command can only be used by a player.");
				return true;
			}
			if (!sc.contains(p) && plugin.getConfig().getBoolean("use-staff-chat") == true) {
				sc.add(p);
				chat.add(p);
				Main.MessageAPI(p, scEnabled);
				return true;
			}
			if (plugin.getConfig().getBoolean("use-staff-chat") == true) {
				sc.remove(p);
				chat.remove(p);
				Main.MessageAPI(p, scDisabled);
				return true;
			} else {
				sender.sendMessage(notEnabled);
			}
			return true;
		}
		return true;
	}

}
