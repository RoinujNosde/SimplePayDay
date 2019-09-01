package me.roinujnosde.simplepayday.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.roinujnosde.simplepayday.TimeClock;

public class JoinQuitListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		TimeClock.add(event.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		TimeClock.remove(event.getPlayer().getUniqueId());
	}
}
