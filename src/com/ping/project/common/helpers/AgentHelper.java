package com.ping.project.common.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AgentHelper {

	public static void runProgram() {

	}

	public static String getRuntimeValue(String jarName) {

		StringBuffer output = new StringBuffer();
		Process p = null;
		
		String cmd = "";
		try {
			switch (OSHelper.getOs()) {
			case "windows":
				cmd = "cmd /c java -jar " + jarName + ".jar";
				break;
			case "solaris":
			case "unix":
				cmd = "java -jar " + jarName + ".jar";
				break;
			}
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

}
