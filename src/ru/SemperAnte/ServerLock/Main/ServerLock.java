package ru.SemperAnte.ServerLock.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.SemperAnte.ServerLock.Listener.CommandListener;
import ru.SemperAnte.ServerLock.Listener.PlayerListener;
import ru.SemperAnte.ServerLock.Utils.Config;
import ru.SemperAnte.ServerLock.Utils.ConfigUtils;

public class ServerLock extends JavaPlugin
{
	 private Config ConfigClass = new Config(this, "config.yml");
	 private Config LangClass = new Config(this, "lang.yml");
	 private FileConfiguration lang = LangClass.getConfig();
	 private FileConfiguration config = ConfigClass.getConfig();
	 private ConfigUtils configUtils = new ConfigUtils(this);
	 private PlayerListener PL = new PlayerListener(this);

	 @Override
	 public void onEnable()
	 {
		  Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
	 }

	 @Override
	 public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	 {
		  if (command.getName().equalsIgnoreCase("serverlock"))
				return new CommandListener(this).onCommand(sender, args);
		  return false;
	 }

	 @Override
	 public FileConfiguration getConfig()
	 {
		  return config;
	 }

	 @Override
	 public void reloadConfig()
	 {
		  ConfigClass.reloadCFG();
		  config = ConfigClass.getConfig();
		  configUtils.reloadConfig(config);
		  PL.reloadConfig(configUtils);
	 }

	 public ConfigUtils getConfigUtils()
	 {
		  return configUtils;
	 }

	 public FileConfiguration getLang()
	 {
		  return lang;
	 }

	 public void reloadLang()
	 {
		  LangClass.reloadCFG();
		  lang = LangClass.getConfig();
		  configUtils.reloadLang(lang);
	 }

	 public void saveConfig()
	 {
		  ConfigClass.saveConfig();
		  reloadConfig();
	 }
}
