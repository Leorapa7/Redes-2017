package com.ping.project.common.entities;

public class Configuration {
	public final int lowPort;
	public final int highPort;
	public final int timeout = 300;
	public Configuration(int lowPort, int highPort) {
		this.lowPort = lowPort;
		this.highPort = highPort;
	}
}
