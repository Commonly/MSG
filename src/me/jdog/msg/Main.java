package me.jdog.msg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import me.jdog.msg.other.events.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.jdog.msg.other.commands.GuiCommand;
import me.jdog.msg.other.commands.Options;
import me.jdog.msg.other.commands.StaffChat;
import me.jdog.msg.other.commands.ToggleChat;
import me.jdog.msg.other.commands.reload;
import me.jdog.msg.other.config.DataManager;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public DataManager dataManager = DataManager.getInstance();
	public Map<Player, Player> reply = new HashMap<Player, Player>();
	public volatile boolean allowChat = true;

	public static void MessageAPI(Player target, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		target.sendMessage(msg);
	}

	public static void MessageAPI(CommandSender sender, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		sender.sendMessage(msg);
	}

	public void broadcast(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		Bukkit.getServer().broadcastMessage(msg);
	}

	@Override
	public void onEnable() {
		Logger logger = this.getLogger();

		this.eventList();
		this.commandList();
		this.saveDefaultConfig();
		this.dataManager.setup(this);
		Options.autoStaff = this.dataManager.getData().getStringList("auto");
		if (!Options.autoStaff.contains("dank_memed_error_fixed")) {
			Options.autoStaff.add("dank_memed_error_fixed");
		}
		this.dataManager.getData().set("auto", Options.autoStaff);
		this.dataManager.saveData();

		logger.info("Message has been enabled!");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command can only be used by a player.");
				return true;
			}
			Player p = (Player) sender;
			String noargsMsg = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("noargsmsg"));
			if (args.length == 0) {
				Main.MessageAPI(p, noargsMsg);
				return true;
			}

			if (args.length >= 2) {
				String notonlineMsg = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("notonlinemsg"));
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					Main.MessageAPI(p, notonlineMsg);
					return true;
				}

				String sendingSelf = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("sendingtoself"));
				if (target == sender) {
					Main.MessageAPI(sender, sendingSelf);
					return true;
				}

				StringBuilder msg2 = new StringBuilder();
				@SuppressWarnings("unused")
				String[] newArray = Arrays.copyOfRange(args, 1, args.length);
				String[] arrstring = Arrays.copyOfRange(args, 1, args.length);
				int m = arrstring.length;
				int m2 = 0;
				while (m2 < m) {
					String arg = arrstring[m2];
					msg2.append(arg);
					msg2.append(" ");
					++m2;
				}

				String senderMsg = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("sendermsg").replace("%target%", target.getName())
								.replace("%sender%", sender.getName()).replace("%msg%", msg2));
				String targetMsg = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("targetmsg").replace("%sender%", sender.getName())
								.replace("%target%", target.getName()).replace("%msg%", msg2));
				callEvent(new EventMessageHandler(target));
				Main.MessageAPI(target, targetMsg);
				this.reply.put(p, target);
				this.reply.put(target, p);
				callEvent(new EventMessageHandler(sender));
				Main.MessageAPI(sender, senderMsg);
				return true;
			}
		}

		if (cmd.getName().equalsIgnoreCase("reply")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command can only be used by a player.");
				return true;
			}

			String noargsRep = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("noargsreply"));
			if (args.length == 0) {
				Main.MessageAPI(sender, noargsRep);
				return true;
			}

			if (args.length >= 1) {
				StringBuilder msg = new StringBuilder();
				String[] msg2 = args;
				int sendingSelf = msg2.length;
				int target = 0;
				while (target < sendingSelf) {
					String arg = msg2[target];
					msg.append(arg);
					msg.append(" ");
					++target;
				}

				Player r = this.reply.get(sender);
				String notonlineRep = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("notonlinereply"));
				if (r == null || !r.isOnline()) {
					Main.MessageAPI(sender, notonlineRep);
					return true;
				}

				String replyMsg = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("replymsg")
						.replace("%sender%", sender.getName()).replace("%target%", r.getName()).replace("%msg%", msg));
				String replysenderMsg = ChatColor.translateAlternateColorCodes('&',
						this.getConfig().getString("replysendermsg").replace("%target%", r.getName())
								.replace("%sender%", sender.getName()).replace("%msg%", msg));
				callEvent(new EventMessageHandler(r));
				Main.MessageAPI(r, replyMsg);
				callEvent(new EventMessageHandler(sender));
				Main.MessageAPI(sender, replysenderMsg);
				return true;

			}
		}

		if (cmd.getName().equalsIgnoreCase("mhelp")) { // Default user help.
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command can only be used by a player.");
				return true;
			}
			Player p = (Player) sender;
			if (p.hasPermission("msg.help")) {
				Main.MessageAPI(p, "&bMessage help >>");
				Main.MessageAPI(p, "&b/msg (/m | /t | /tell) - Send a message to the specified person.");
				Main.MessageAPI(p, "&b/reply (/r) - Reply to the person you last messaged.");
				Main.MessageAPI(p, "&b/mhelp - Display message help.");
			}
			return true;
		}

		return true;
	}

	public void allowChat(boolean allow) {
		this.allowChat = allow;
	}

	public boolean isChatEnabled() {
		return this.allowChat;
	}

	public void commandList() {
		this.getCommand("message").setExecutor(new reload(this));
		this.getCommand("staffchat").setExecutor(new StaffChat(this));
		this.getCommand("moptions").setExecutor(new Options(this));
		this.getCommand("mpanel").setExecutor(new GuiCommand(this));
		this.getCommand("togglechat").setExecutor(new ToggleChat(this));
	}

	public void eventList() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new Leave(this), this);
		pm.registerEvents(new EventChat(this), this);
		pm.registerEvents(new Join(this), this);
		pm.registerEvents(new EventClick(this), this);
		pm.registerEvents(new EventMessage(this), this);
		pm.registerEvents(new JoinJSON(this), this);
        pm.registerEvents(new EventDeath(this), this);
	}

	public void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
	}

}
