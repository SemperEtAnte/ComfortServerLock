package ru.SemperAnte.ServerLock.Commands;

import org.bukkit.Bukkit;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.APICommandSender;

import java.util.List;

public class BypassCommand extends CommandListener
{
	 public BypassCommand(APICommandSender cs, String[] args) throws SemperAPIException
	 {
		  super(cs, args);
	 }

	 @Override
	 public boolean execute() throws SemperAPIException
	 {
		  try
		  {
				String prefix = ServerLock.getPrefix();
				List<String> bypassed = ServerLock.getConfigUtils().getStringList("bypassedPlayers");
				switch (args[1].toLowerCase())
				{
					 case "add":
						  if (bypassed.contains(args[2].toLowerCase()))
								sender.sendPrefixedMessage(ServerLock.getLangUtils().castString("alreadyExists", args[2]));
						  else
						  {
								bypassed.add(args[2].toLowerCase());
								ServerLock.getConfigUtils().set("bypassedPlayers", bypassed);
								Bukkit.broadcastMessage(prefix + ServerLock.getLangUtils().castString("add", sender.getName(), args[2]));

						  }
						  return true;
					 case "remove":
						  if(!bypassed.contains(args[2].toLowerCase()))
						  	 sender.sendPrefixedMessage(ServerLock.getLangUtils().castString("notExists", args[2]));
						  else
						  {
								bypassed.remove(args[2].toLowerCase());
								ServerLock.getConfigUtils().set("bypassedPlayers", bypassed);
								Bukkit.broadcastMessage(prefix + ServerLock.getLangUtils().castString("remove", sender.getName(), args[2]));

						  }
					 default:
						  sender.sendPrefixedMessage("/sl bypass <add/remove> <username>");
						  return true;
				}
		  }
		  catch (ArrayIndexOutOfBoundsException e)
		  {
				sender.sendPrefixedMessage("/sl bypass <add/remove> <username>");
		  }
		  return false;
	 }
}
