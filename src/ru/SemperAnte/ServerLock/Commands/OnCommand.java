package ru.SemperAnte.ServerLock.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.ServerLock.Utils.TaskUtils;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.APICommandSender;

public class OnCommand extends CommandListener
{
	 public OnCommand(APICommandSender cs, String[] args) throws SemperAPIException
	 {
		  super(cs, args);
	 }

	 @Override
	 public boolean execute() throws SemperAPIException
	 {
		  if (ServerLock.getConfigUtils().getBoolean("locked"))
		  {
				sender.sendPrefixedMessage(" &cAlready Locked");
		  }
		  else
		  {
				TaskUtils.startTimer();
				sender.sendPrefixedMessage(" &aDone.");
				Bukkit.broadcastMessage(ServerLock.getLangUtils().castString("enableLock", sender.getName()));
		  }
		  return true;
	 }
}
