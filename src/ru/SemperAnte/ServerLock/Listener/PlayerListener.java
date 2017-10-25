package ru.SemperAnte.ServerLock.Listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.ServerLock.Utils.ConfigUtils;

public class PlayerListener implements Listener
{
	 ConfigUtils config;

	 public PlayerListener(ServerLock plugin)
	 {
		  config = plugin.getConfigUtils();
	 }

	 @EventHandler
	 public void onJoin(PlayerLoginEvent event)
	 {
		  if (ConfigUtils.Locked)
		  {
				if (!config.canBypass(event.getPlayer()))
				{
					 String msg = config.getFromLang("KickMessage");
					 event.setKickMessage(msg);
					 event.disallow(PlayerLoginEvent.Result.KICK_OTHER, msg);
					 event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
					 Bukkit.broadcastMessage(config.prefix + String.format(config.getFromLang("denyEntry"), event.getPlayer().getName()));
				}
				else
				{
					 Bukkit.broadcastMessage(config.prefix + String.format(config.getFromLang("allowEntry"), event.getPlayer().getName()));
				}
		  }
	 }

	 public void reloadConfig(ConfigUtils config)
	 {
		  this.config = config;
	 }
}
