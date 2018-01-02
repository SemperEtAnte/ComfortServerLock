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
				String playername = args[2];
				switch (args[1].toLowerCase())
				{
					 case "add":
						  if (bypassed.contains(playername.toLowerCase()))
								sender.sendPrefixedMessage(ServerLock.getLangUtils().castString("alreadyExists", playername));
						  else
						  {
								bypassed.add(playername.toLowerCase());
								ServerLock.getConfigUtils().set("bypassedPlayers", bypassed);
								Bukkit.broadcastMessage(prefix + ServerLock.getLangUtils().castString("add", sender.getName(), playername));

						  }
						  return true;
					 case "remove":
						  if(!bypassed.contains(playername.toLowerCase()))
						  	 sender.sendPrefixedMessage(ServerLock.getLangUtils().castString("notExists", playername));
						  else
						  {
								bypassed.remove(playername.toLowerCase());
								ServerLock.getConfigUtils().set("bypassedPlayers", bypassed);
								Bukkit.broadcastMessage(prefix + ServerLock.getLangUtils().castString("remove", sender.getName(), playername));

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
