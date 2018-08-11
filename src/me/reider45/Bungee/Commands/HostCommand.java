package me.reider45.Bungee.Commands;

import java.net.InetSocketAddress;

import me.reider45.Bungee.BungeeManager;
import me.reider45.Bungee.Commands.Util.Utilities;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class HostCommand extends Command {

	public HostCommand() {
		super("sethost");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("custom.sethost"))
			return;

		if (args.length == 2) {
			String ip = args[0];
			String name = args[1];

			ProxyServer ps = BungeeManager.getInstance().getProxy();

			// Verify the server name
			if (!ps.getServers().containsKey(name)) {
				sender.sendMessage(Utilities.getMessage(ChatColor.RED + "No such server!"));
				return;
			}

			// Get server's info
			ServerInfo prevInfo = ps.getServers().get(name);

			// Remove from Map
			ps.getServers().remove(name);

			// Get port
			int port = prevInfo.getAddress().getPort();

			// Create new server w/ updated information
			ServerInfo newInfo = ps.constructServerInfo(prevInfo.getName(), new InetSocketAddress(ip, port),
					prevInfo.getMotd(), false);

			// Save to map
			ps.getServers().put(newInfo.getName(), newInfo);

			// Send Message
			sender.sendMessage(
					Utilities.getMessage(ChatColor.GREEN + "Server " + newInfo.getName() + " now has the IP: " + ip));

		} else {
			// Invalid args message
			sender.sendMessage(Utilities.getMessage(ChatColor.RED + "Invalid arguments!"));
		}
	}

}
