package me.roinujnosde.simplepayday;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.roinujnosde.simplepayday.tasks.GiveMoneyTask;
import net.milkbowl.vault.economy.Economy;

public class SimplePayDay extends JavaPlugin {
	private Economy econ;
	
	@Override
	public void onEnable() {
		if (!setupEconomy()) {
			getLogger().severe("Vault or Economy plugin not found! Disabling...");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		saveDefaultConfig();
		
		Groups groups = new Groups(this);
		groups.loadGroups();
		
		startTasks();
	}
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
	}
	
	private void startTasks() {
		Groups.getGroups().forEach(g -> {
			new GiveMoneyTask(g).start(this);
		});
	}
	
    public Economy getEconomy() {
		return econ;
	}

	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public String getLang(String message) {
		if (message == null) {
			return "";
		}
		
		String string = getConfig().getString("messages." + message);
		if (string == null) {
			string = "&4Missing language for " + message;
		}
		
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}
