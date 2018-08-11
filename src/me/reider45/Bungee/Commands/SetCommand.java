package me.reider45.Bungee.Commands;

import me.reider45.Bungee.BungeeManager;
import me.reider45.Bungee.Commands.Util.Utilities;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class SetCommand extends Command {

	public SetCommand() {
		super("setmotd");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission("custom.setmotd"))
			return;

		if (args.length >= 2) {
			String name = args[0];
			String motd = args[1];

			// Add all other arguments to MOTD excluding server name and base of
			// MOTD
			for (String s : args)
				if (!s.equals(name) && !s.equals(args[1]))
					motd += " " + s;

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

			// Create new server w/ updated information
			ServerInfo newInfo = ps.constructServerInfo(prevInfo.getName(), prevInfo.getAddress(), motd, false);

			// Save to map
			ps.getServers().put(newInfo.getName(), newInfo);

			// Send Message
			sender.sendMessage(Utilities.getMessage(
					ChatColor.GREEN + "Server " + newInfo.getName() + " now has the MOTD: " + newInfo.getMotd()));

		} else {
			// Invalid args message
			sender.sendMessage(Utilities.getMessage(ChatColor.RED + "Invalid arguments!"));
		}
	}

}
