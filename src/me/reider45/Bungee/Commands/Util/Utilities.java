package me.reider45.Bungee.Commands.Util;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class Utilities {
	
	public static BaseComponent[] getMessage(String msg) {
		return new ComponentBuilder(msg).create();
	}

}
