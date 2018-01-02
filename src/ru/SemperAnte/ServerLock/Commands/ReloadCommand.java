package ru.SemperAnte.ServerLock.Commands;

import org.bukkit.plugin.java.JavaPlugin;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.plugins.API.Exceptions.SemperAPIException;
import ru.SemperAnte.plugins.API.Interface.APICommandSender;

public class ReloadCommand extends CommandListener
{
	 public ReloadCommand(APICommandSender cs, String[] args) throws SemperAPIException
	 {
		  super(cs, args);
	 }

	 @Override
	 public boolean execute() throws SemperAPIException
	 {
		  ServerLock.getConfigUtils().reloadConfig();
		  ServerLock.getLangUtils().reloadLang();
		  return true;
	 }
}
