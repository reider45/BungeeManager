package me.reider45.Bungee.Commands;

import java.net.InetSocketAddress;

import me.reider45.Bungee.BungeeManager;
import me.reider45.Bungee.Commands.Util.Utilities;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class AddCommand extends Command {

	public AddCommand() {
		super("addserver");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("custom.addserver"))
			return;

		if (args.length == 3) {
			String name = args[0];
			String ip = args[1];
			Integer port;

			try {
				// Verify they entered an int for the port
				port = Integer.parseInt(args[2]);
			} catch (Exception e) {
				// if not, tell them and return
				sender.sendMessage(new ComponentBuilder(ChatColor.RED + "Invalid port!").create());
				return;
			}

			ProxyServer ps = BungeeManager.getInstance().getProxy();

			// Verify the server name is unique
			if (ps.getServers().containsKey(name)) {
				sender.sendMessage(Utilities.getMessage(ChatColor.RED + "That server already exists!"));
				return;
			}

			// Construct the server
			ServerInfo newServer = ps.constructServerInfo(name, new InetSocketAddress(ip, port), "Default MOTD", false);

			// Add to server map
			ps.getServers().put(newServer.getName(), newServer);

			// Send Message
			sender.sendMessage(Utilities.getMessage(
					ChatColor.GREEN + "New server named " + name + " was added, connect with /server " + name + "!"));

		} else {
			// Invalid args message
			sender.sendMessage(Utilities.getMessage(ChatColor.RED + "Invalid arguments!"));
		}

	}
}
