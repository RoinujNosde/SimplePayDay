package me.roinujnosde.simplepayday;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeClock {
	private final static Map<UUID, LocalDateTime> DATA = new HashMap<>();
	
	public static LocalDateTime getLoginTime(UUID uuid) {
		return DATA.get(uuid);
	}
	
	public static void add(UUID uuid) {
		DATA.put(uuid, LocalDateTime.now());
	}
	
	public static void remove(UUID uuid) {
		DATA.remove(uuid);
	}
}
