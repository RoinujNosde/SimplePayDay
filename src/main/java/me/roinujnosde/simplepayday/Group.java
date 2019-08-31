package me.roinujnosde.simplepayday;

import java.util.Objects;

public class Group {
	private String name;
	private double amount;
	private int interval;
	
	public Group(String name, double amount, int interval) {
		if (amount < 0) {
			amount = 0;
		}
		if (interval < 0) {
			interval = 0;
		}
		this.name = Objects.requireNonNull(name);
		this.amount = amount;
		this.interval = interval;
	}

	public String getName() {
		return name;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public int getInterval() {
		return interval;
	}
}
