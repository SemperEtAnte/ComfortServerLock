package ru.SemperAnte.ServerLock.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.SemperAnte.ServerLock.Utils.ConfigUtils;

public class LockDownTask implements Runnable
{
	 private ConfigUtils config;

	 public LockDownTask(ConfigUtils config)
	 {
		  this.config = config;
	 }

	 @Override public void run()
	 {
		  synchronized (this)
		  {
				short lastSec = config.LockCooldown;
				String prefix = config.prefix;
				while (lastSec > 0)
				{
					 Bukkit.broadcastMessage(prefix + String.format(config.getFromLang("cooldown"), lastSec));
					 lastSec--;
					 try
					 {
						  Thread.sleep(1000);
					 }
					 catch (InterruptedException e)
					 {
						  e.printStackTrace();
					 }
				}
				config.Locked = true;
				config.set("locked", true);
				config.save();
				for (Player p : Bukkit.getServer().getOnlinePlayers())
					 if (!config.canBypass(p))
						  p.kickPlayer(prefix + config.getFromLang("KickMessage"));

		  }
	 }
}
