package ru.SemperAnte.ServerLock.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class LockDownTask implements Runnable
{

	 @Override public void run()
	 {
		  synchronized (this)
		  {
				int lastSec = ServerLock.getConfigUtils().getInt("LockCooldown");
				String prefix = ServerLock.getConfigUtils().getString("prefix");
				while (lastSec > 0)
				{
					 Bukkit.broadcastMessage(prefix + ServerLock.getLangUtils().castString("cooldown", lastSec));
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
				ServerLock.getConfigUtils().set("locked", true);
				List<String> bypassed = ServerLock.getConfigUtils().getStringList("bypassedPlayers");
				for (Player p : Bukkit.getServer().getOnlinePlayers())
					 if (!bypassed.contains(p.getName().toLowerCase()))
						  p.kickPlayer(prefix + ServerLock.getLangUtils().castString("KickMessage"));

		  }
	 }
}
