package me.jdog.msg.other;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdog.msg.Main;

public class Options implements CommandExecutor {

	public static List<String> autoStaff;

	Main plugin;

	public Options(Main pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("moptions")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be used by players!");
				return true;
			}

			if (args.length == 0) {
				Main.MessageAPI(sender, "&cUnknown argument! [auto]");
				return true;
			}

			if (args[0].equalsIgnoreCase("auto")) {
				Player p = (Player) sender;

				if (args.length == 1) {
					Main.MessageAPI(sender, "&cUnknown arguments! [join,leave]");
					return true;
				}

				if (args[1].equalsIgnoreCase("join")) {
					autoStaff = plugin.dataManager.getData().getStringList("auto");
					if (!autoStaff.contains(p.getUniqueId().toString())) {
						autoStaff.add(p.getUniqueId().toString());
						plugin.dataManager.getData().set("auto", autoStaff);
						plugin.dataManager.saveData();
						Main.MessageAPI(sender, "&cYou were added to the auto staffchat list!");
						return true;
					} else if (autoStaff.contains(p.getUniqueId().toString())) {
						Main.MessageAPI(sender, "&cYou're already added to the auto staffchat list!");
						return true;
					}
				} else if (args[1].equalsIgnoreCase("leave")) {
					autoStaff = plugin.dataManager.getData().getStringList("auto");
					if (autoStaff.contains(p.getUniqueId().toString())) {
						autoStaff = plugin.getConfig().getStringList("auto");
						autoStaff.remove(p.getUniqueId().toString());
						plugin.dataManager.getData().set("auto", autoStaff);
						plugin.dataManager.saveData();
						Main.MessageAPI(sender, "&cYou were removed from the auto staffchat list!");
						return true;
					} else if (!autoStaff.contains(p.getUniqueId().toString())) {
						Main.MessageAPI(sender, "&cYou're not on the auto staffchat list!");
						return true;
					}
				} else {
					Main.MessageAPI(sender, "&cUnknown arguments! [join,leave]");
					return true;
				}

			} else {
				Main.MessageAPI(sender, "&cUnknown argument! [auto]");
				return true;
			}

		}
		return true;
	}

}
