package me.reider45.Bungee.Commands;

import me.reider45.Bungee.BungeeManager;
import me.reider45.Bungee.Commands.Util.Utilities;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class DelCommand extends Command {

	public DelCommand() {
		super("delserver");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("custom.delserver"))
			return;

		if (args.length == 1) {
			String name = args[0];

			// Get Proxy Server
			ProxyServer ps = BungeeManager.getInstance().getProxy();

			// Verify the server name
			if (!ps.getServers().containsKey(name)) {
				sender.sendMessage(Utilities.getMessage(ChatColor.RED + "No such server!"));
				return;
			}

			// Remove the server
			ps.getServers().remove(name);

			// Send Message
			sender.sendMessage(Utilities.getMessage(ChatColor.GREEN + "Server " + name + " deleted!"));

		} else {
			// Invalid args message
			sender.sendMessage(Utilities.getMessage(ChatColor.RED + "Invalid arguments!"));
		}
	}
}
