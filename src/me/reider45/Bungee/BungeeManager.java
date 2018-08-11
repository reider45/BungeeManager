package me.reider45.Bungee;

import me.reider45.Bungee.Commands.AddCommand;
import me.reider45.Bungee.Commands.DelCommand;
import me.reider45.Bungee.Commands.HostCommand;
import me.reider45.Bungee.Commands.SetCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeManager extends Plugin {
	
	private static BungeeManager i;
	
	public void onEnable(){
		i = this;
		
		// Register Commands
		getProxy().getPluginManager().registerCommand(this, new AddCommand());
		getProxy().getPluginManager().registerCommand(this, new SetCommand());
		getProxy().getPluginManager().registerCommand(this, new DelCommand());
		getProxy().getPluginManager().registerCommand(this, new HostCommand());
	}
	
	public static BungeeManager getInstance(){
		return i;
	}

}