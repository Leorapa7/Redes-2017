package com.ping.project.common.helpers;

public class OSHelper {
	private static String OS = System.getProperty("os.name").toLowerCase();

	private static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	private static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	private static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	private static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

	public static String getOs() {
		String os = "";
		if (isWindows()) {
			os = "windows";
		} else if (isMac()) {
			os = "mac";
		} else if (isUnix()) {
			os = "unix";
		} else if (isSolaris()) {
			os = "solaris";
		} else {
			os ="error";
		}
		return os;
	}
}
