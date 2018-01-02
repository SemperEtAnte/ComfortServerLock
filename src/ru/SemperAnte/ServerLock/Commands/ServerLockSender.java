package ru.SemperAnte.ServerLock.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.SemperAnte.ServerLock.Main.ServerLock;
import ru.SemperAnte.plugins.API.Interface.Classes.Sender;

public class ServerLockSender extends Sender
{
	 public ServerLockSender(CommandSender sender)
	 {
		  super(sender);
	 }

	 @Override
	 public void sendPrefixedMessage(String message)
	 {
		  base.sendMessage(ChatColor.translateAlternateColorCodes('&', ServerLock.getPrefix() + message));
	 }
}
