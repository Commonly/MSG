package me.jdog.msg.other;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
* Removed in v1.8
*/

public class GuiAPI {
	
	private static List<String> elore = new ArrayList<String>();
	private static List<String> rlore = new ArrayList<String>();
	private static List<String> slore = new ArrayList<String>();

	public static void gui(Player p, String iname, String ename, String rname) {
		String sname = "&eStaff Chat";
		sname = ChatColor.translateAlternateColorCodes('&', sname);
		iname = ChatColor.translateAlternateColorCodes('&', iname);
		ename = ChatColor.translateAlternateColorCodes('&', ename);
		rname = ChatColor.translateAlternateColorCodes('&', rname);
		// Make the inventory
		Inventory i = Bukkit.createInventory(null, 27, iname);

		// Set items
		ItemStack j = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta e = j.getItemMeta();
		ItemStack l = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta r = l.getItemMeta();
		ItemStack sc = new ItemStack(Material.BLAZE_ROD);
		ItemMeta b = sc.getItemMeta();

		// Set item data
		
		// emerald
		String elores = "Join the auto staffchat";
		e.setDisplayName(ename);
		elore.add(elores);
		e.setLore(elore);
		j.setItemMeta(e);
		elore.remove(elores);
		
		// redstone
		String rlores = "Leave the auto staffchat";
		r.setDisplayName(rname);
		rlore.add(rlores);
		r.setLore(rlore);
		l.setItemMeta(r);
		rlore.remove(rlores);
		
		// blaze rod
		String slores = "Toggle staffchat";
		b.setDisplayName(sname);
		slore.add(slores);
		b.setLore(slore);
		sc.setItemMeta(b);
		slore.remove(slores);
		
		// set item pos
		i.setItem(10, j);
		i.setItem(13, sc);
		i.setItem(16, l);

		// open
		p.openInventory(i);
	}

}
