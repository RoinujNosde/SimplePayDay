package me.roinujnosde.simplepayday.tasks;

import java.text.MessageFormat;
import java.util.Objects;

import org.bukkit.scheduler.BukkitRunnable;

import me.roinujnosde.simplepayday.Group;
import me.roinujnosde.simplepayday.SimplePayDay;

public class GiveMoneyTask extends BukkitRunnable {
	private SimplePayDay plugin;
	private final Group group;
	
	public GiveMoneyTask(Group group) {
		this.group = Objects.requireNonNull(group);
	}
	
	public void start(SimplePayDay plugin) {
		this.plugin = Objects.requireNonNull(plugin);
		int interval = group.getInterval() * 60 * 20;
		
		runTaskTimer(plugin, interval, interval);
	}
	
	@Override
	public void run() {
		if (plugin == null) {
			throw new IllegalStateException("Use the start method!");
		}
		
		plugin.getServer().getOnlinePlayers().forEach(p -> {
			if (p.hasPermission("simplepayday.group."+ group.getName())) {
				plugin.getEconomy().depositPlayer(p, group.getAmount());
				p.sendMessage(MessageFormat.format(plugin.getLang("money-received"), group.getAmount()));
			}
		});;
	}

}
