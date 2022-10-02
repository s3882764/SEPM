package com.spem.application.pojo;

public class PreferredTime {
	
	private String time;
	
	private String slot;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "PreferredTime [time=" + time + ", slot=" + slot + "]";
	}
	
	

}
