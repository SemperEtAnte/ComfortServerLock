package ru.SemperAnte.ServerLock.Commands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.ServerLock.Utils.TaskUtils;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.APICommandSender;

public class OffCommand extends CommandListener
{
	 public OffCommand(APICommandSender cs, String[] args) throws SemperAPIException
	 {
		  super(cs, args);
	 }

	 @Override
	 public boolean execute() throws SemperAPIException
	 {
		  if (!ServerLock.getConfigUtils().getBoolean("locked"))
		  {
				sender.sendPrefixedMessage(" &cNot locked yet");
		  }
		  else
		  {
				ServerLock.getConfigUtils().set("locked", false);
				sender.sendPrefixedMessage(" &aDone.");
				Bukkit.broadcastMessage(ServerLock.getLangUtils().castString("disableLock", sender.getName()));
		  }
		  return true;
	 }
}
