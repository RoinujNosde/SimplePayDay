package me.roinujnosde.simplepayday.tasks;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.bukkit.scheduler.BukkitRunnable;

import me.roinujnosde.simplepayday.Group;
import me.roinujnosde.simplepayday.SimplePayDay;
import me.roinujnosde.simplepayday.TimeClock;

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
				double amount = group.getAmount();
				long onlineTime = TimeClock.getLoginTime(p.getUniqueId()).until(LocalDateTime.now(), ChronoUnit.MINUTES);
				if (onlineTime < group.getInterval()) {
					amount = (onlineTime / (double) group.getInterval()) * amount;
				} else {
					onlineTime = group.getInterval();
				}
				
				plugin.getEconomy().depositPlayer(p, amount);
				p.sendMessage(MessageFormat.format(plugin.getLang("money-received"), group.getAmount(), onlineTime));
			}
		});;
	}

}
