package ru.SemperAnte.ServerLock.Utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.SemperAnte.ServerLock.Main.ServerLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigUtils
{

	 public static List<String> bypassedPlayers;
	 public static boolean Locked;
	 public short LockCooldown;
	 private ServerLock plugin;
	 public String prefix;
	 private FileConfiguration config;
	 private FileConfiguration lang;
	 private Map<String, Object> langs;

	 public ConfigUtils(ServerLock plugin)
	 {
		  this.plugin = plugin;
		  config = plugin.getConfig();
		  lang = plugin.getLang();
		  configure();
	 }

	 public String getFromLang(String what)
	 {
		  return ChatColor.translateAlternateColorCodes('&', (String) langs.get(what));
	 }

	 private void configure()
	 {
		  bypassedPlayers = config.getStringList("bypassedPlayers");
		  Locked = config.getBoolean("locked");
		  LockCooldown = (short) config.getInt("cooldown");
		  prefix = ChatColor.translateAlternateColorCodes('&', config.getString("prefix"));
		  langs = lang.getConfigurationSection("messages").getValues(true);
	 }

	 public void set(String path, Object value)
	 {
		  config.set(path, value);
	 }

	 public void save()
	 {
		  plugin.saveConfig();
	 }

	 public void reloadConfig(FileConfiguration config)
	 {
		  this.config = config;
		  configure();
	 }

	 public void reloadLang(FileConfiguration lang)
	 {
		  this.lang = lang;
		  configure();
	 }

	 public boolean canBypass(Player player)
	 {
		  return bypassedPlayers.contains(player.getName()) || player.hasPermission("serverlock.bypass");
	 }

}
