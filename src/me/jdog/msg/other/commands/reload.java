package me.jdog.msg.other.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdog.msg.Main;

public class reload implements CommandExecutor {

	private Main plugin;

	public reload(Main pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("message")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Nope!");
				return true;
			}
			if (args.length == 0) {
				Main.MessageAPI(sender, "&bPlease add args! Accepted args: reload | help");
				return true;
			}

			if (args[0].equalsIgnoreCase("reload")) {
				this.plugin.reloadConfig();
				Main.MessageAPI(sender, "&bConfig has been reloaded!");
				return true;
			}

			if (args[0].equalsIgnoreCase("help")) {
				Main.MessageAPI(sender, "&b&m---------------&r&aMSG&b&m---------------");
				Main.MessageAPI(sender, "&c/msg (aliases: m, t, tell)");
				Main.MessageAPI(sender, "&c/reply (aliases: r)");
				Main.MessageAPI(sender, "&b/mhelp - Display message help.");
				Main.MessageAPI(sender, "&b/staffchat - Enter staffchat room.");
				Main.MessageAPI(sender, "&b&m---------------&r&aMSG&b&m---------------");
				return true;
			}

		}
		return true;
	}

}
