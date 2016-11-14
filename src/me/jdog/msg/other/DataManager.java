package me.jdog.msg.other;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import me.jdog.msg.Main;

public class DataManager {
	static DataManager instance = new DataManager();
	Main p;
	FileConfiguration config;
	File cfile;
	FileConfiguration data;
	File dfile;

	private DataManager() {
	}

	public static DataManager getInstance() {
		return instance;
	}

	public void setup(Main p) {
		this.cfile = new File(p.getDataFolder(), "config.yml");
		this.config = p.getConfig();
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		this.dfile = new File(p.getDataFolder(), "data.yml");
		if (!this.dfile.exists()) {
			try {
				this.dfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe((Object) ChatColor.RED + "Could not create data.yml!");
			}
		}
		
		this.data = YamlConfiguration.loadConfiguration((File) this.dfile);
	}

	public FileConfiguration getData() {
		return this.data;
	}

	public void saveData() {
		try {
			this.data.save(this.dfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe((Object) ChatColor.RED + "Could not save data.yml!");
		}
	}

	public void reloadData() {
		this.data = YamlConfiguration.loadConfiguration((File) this.dfile);
	}

	public FileConfiguration getConfig() {
		return this.config;
	}

	public void saveConfig() {
		try {
			this.config.save(this.cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe((Object) ChatColor.RED + "Could not save config.yml!");
		}
	}

	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration((File) this.cfile);
	}

	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}
}
