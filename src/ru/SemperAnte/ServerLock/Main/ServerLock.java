package ru.SemperAnte.ServerLock.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.SemperAnte.ServerLock.Commands.CommandListener;
import ru.SemperAnte.ServerLock.Commands.ServerLockSender;
import ru.SemperAnte.ServerLock.Listener.PlayerListener;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.Classes.Sender;
import ru.SemperAnte.plugins.API.MainAPI;
import ru.SemperAnte.plugins.API.Utils.ConfigUtils;
import ru.SemperAnte.plugins.API.Utils.LangUtils;

public class ServerLock extends JavaPlugin
{

	 private static ConfigUtils CU;
	 private static LangUtils LU;

	 @Override
	 public void onEnable()
	 {
		  if (!MainAPI.isMustEnable(this))
				return;
		  Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		  CU = new ConfigUtils(this);
		  LU = new LangUtils(this);
	 }

	 @Override
	 public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	 {
		  try
		  {
				return new CommandListener(new ServerLockSender(sender), args).isDone();
		  }
		  catch (SemperAPIException e)
		  {
				sender.sendMessage("Command not executed. Ask server administrator to fix that.");
				e.printStackTrace();
		  }
		  return false;
	 }

	 public static ConfigUtils getConfigUtils()
	 {
		  return CU;
	 }

	 public static LangUtils getLangUtils()
	 {
		  return LU;
	 }

	 public static String getPrefix()
	 {
		  return CU.getString("prefix");
	 }
}
