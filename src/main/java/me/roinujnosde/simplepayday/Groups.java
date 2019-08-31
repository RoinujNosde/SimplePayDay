package me.roinujnosde.simplepayday;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

public class Groups {
	private final SimplePayDay plugin;
	private static Set<Group> groups;
	
	public Groups(SimplePayDay plugin) {
		this.plugin = Objects.requireNonNull(plugin);
		if (groups == null) {
			groups = new HashSet<>();
		}
	}
	
	public void loadGroups() {
		groups.clear();

		ConfigurationSection csGroups = plugin.getConfig().getConfigurationSection("groups");
		csGroups.getValues(false).forEach((s, o) -> {
			ConfigurationSection csGroup = csGroups.getConfigurationSection(s);
			double amount = csGroup.getDouble("amount");
			int interval = csGroup.getInt("interval");
			
			groups.add(new Group(s, amount, interval));
		});
	}
	
	public static Set<Group> getGroups() {
		return groups;
	}
}
