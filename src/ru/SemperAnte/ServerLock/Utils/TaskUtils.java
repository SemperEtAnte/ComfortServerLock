package ru.SemperAnte.ServerLock.Utils;

import org.bukkit.Bukkit;
import ru.SemperAnte.ServerLock.Main.LockDownTask;
import ru.SemperAnte.ServerLock.Main.ServerLock;

public class TaskUtils
{
	 private static ServerLock plugin;

	 public TaskUtils(ServerLock plug)
	 {
		  plugin = plug;
	 }

	 public static void startTimer()
	 {
		  Bukkit.getScheduler().runTaskAsynchronously(plugin, new LockDownTask());
	 }
}
