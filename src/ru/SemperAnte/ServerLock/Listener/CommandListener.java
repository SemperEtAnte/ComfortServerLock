package ru.SemperAnte.ServerLock.Listener;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ru.SemperAnte.ServerLock.Main.LockDownTask;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.ServerLock.Utils.ConfigUtils;

public class CommandListener
{
	 private ConfigUtils config;
	 private ServerLock plugin;

	 public CommandListener(ServerLock plugin)
	 {
		  config = plugin.getConfigUtils();
		  this.plugin = plugin;
	 }

	 public void reloadConfig(ConfigUtils config)
	 {
		  this.config = config;
	 }

	 public boolean onCommand(CommandSender sender, String[] args)
	 {
		  String prefix = config.prefix;
		  if (!sender.hasPermission("ServerLock.manage"))
		  {
				sender.sendMessage(prefix + config.getFromLang("noPermissions"));
				return true;
		  }
		  try
		  {
				args[0] = args[0].toLowerCase();
				switch (args[0])
				{
					 case "on":
						  if (ConfigUtils.Locked)
						  {
								sender.sendMessage(prefix + "Already Locked");
								return true;
						  }
						  Bukkit.broadcastMessage(prefix + String.format(config.getFromLang("enableLock"), sender.getName()));
						  Bukkit.getScheduler().runTaskAsynchronously(plugin, new LockDownTask(config));
						  break;
					 case "off":
						  if (!ConfigUtils.Locked)
						  {
								sender.sendMessage(prefix + "Not locked yet");
								return true;
						  }
						  Bukkit.broadcastMessage(prefix + String.format(config.getFromLang("disableLock"), sender.getName()));
						  config.set("locked", false);
						  ConfigUtils.Locked = false;
						  config.save();
						  break;
					 case "bypass":
						  String what = args[1].toLowerCase();
						  switch (what)
						  {
								case "add":
									 ConfigUtils.bypassedPlayers.add(args[2]);
									 config.set("bypassedPlayers", ConfigUtils.bypassedPlayers);
									 config.save();
									 Bukkit.broadcastMessage(prefix + String.format(config.getFromLang("add"), sender.getName(), args[2]));
									 break;
								case "remove":
									 ConfigUtils.bypassedPlayers.remove(args[2]);
									 config.set("bypassedPlayers", ConfigUtils.bypassedPlayers);
									 config.save();
									 Bukkit.broadcastMessage(prefix + String.format(config.getFromLang("remove"), sender.getName(), args[2]));
									 break;
						  }
					 case "reload":
						  plugin.reloadLang();
						  plugin.reloadConfig();
						  sender.sendMessage(prefix + "Done");

				}
		  }
		  catch (
				  ArrayIndexOutOfBoundsException e)

		  {
				sender.sendMessage("Few arguments");
				sender.sendMessage("/severlock on - enable lockdown");
				sender.sendMessage("/serverlock off - disable lockdown");
				sender.sendMessage("/serverlock bypass <add/remove> <name> - add/remove player to bypass list");
				sender.sendMessage("/serverlock reload - reload config from config.yml");
		  }
		  return false;
	 }
}
