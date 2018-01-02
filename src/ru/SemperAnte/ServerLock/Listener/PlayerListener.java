package ru.SemperAnte.ServerLock.Listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import ru.SemperAnte.ServerLock.Main.ServerLock;

public class PlayerListener implements Listener
{

	 @EventHandler
	 public void onJoin(PlayerLoginEvent event)
	 {
		  if (ServerLock.getConfigUtils().getBoolean("locked"))
		  {
				if (!ServerLock.getConfigUtils().getStringList("bypassedPlayers").contains(event.getPlayer().getName().toLowerCase()))
				{
					 String msg = ServerLock.getLangUtils().castString("KickMessage");
					 event.setKickMessage(msg);
					 event.disallow(PlayerLoginEvent.Result.KICK_OTHER, msg);
					 event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
					 Bukkit.broadcastMessage(ServerLock.getPrefix() + ServerLock.getLangUtils().castString("denyEntry", event.getPlayer().getName()));
				}
				else
					 Bukkit.broadcastMessage(ServerLock.getPrefix() + ServerLock.getLangUtils().castString("allowEntry", event.getPlayer().getName()));
		  }
	 }

}
