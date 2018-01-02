package ru.SemperAnte.ServerLock.Commands;

import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.plugins.API.Commands.ConsoleCommand;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.APICommandSender;

public class CommandListener extends ConsoleCommand
{

	 public CommandListener(APICommandSender cs, String[] args) throws SemperAPIException
	 {
		  super(cs, args);
	 }

	 @Override public String getPermission()
	 {
		  return "ServerLock.manage";
	 }

	 @Override
	 public boolean execute() throws SemperAPIException
	 {
		  String prefix = ServerLock.getConfigUtils().getString("prefix");
		  try
		  {
				switch (args[0].toLowerCase())
				{
					 case "on":
						  return new OnCommand(sender, args).isDone();
					 case "off":
						  return new OffCommand(sender, args).isDone();
					 case "bypass":
						  return new BypassCommand(sender, args).isDone();
					 case "reload":
						  return new ReloadCommand(sender, args).isDone();
					 default:
						  sender.sendPrefixedMessage("&a/severlock on - enable lockdown");
						  sender.sendPrefixedMessage("&a/serverlock off - disable lockdown");
						  sender.sendPrefixedMessage("&a/serverlock bypass <add/remove> <name> - add/remove player to bypass list");
						  sender.sendPrefixedMessage("&a/serverlock reload - reload config from config.yml");
						  return true;
				}
		  }
		  catch (ArrayIndexOutOfBoundsException e)
		  {
				sender.sendPrefixedMessage("&a/severlock on - enable lockdown");
				sender.sendPrefixedMessage("&a/serverlock off - disable lockdown");
				sender.sendPrefixedMessage("&a/serverlock bypass <add/remove> <name> - add/remove player to bypass list");
				sender.sendPrefixedMessage("&a/serverlock reload - reload config from config.yml");
		  }
		  return false;
	 }

}
