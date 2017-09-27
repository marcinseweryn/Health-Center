package com.github.marcinseweryn.pojo;

public enum Presence {
	present(0), absent(1), current(2), inQueue(3), firstAbsent(4);
	
	private final Integer value;
	
	private Presence(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}

}
