package com.ping.project.common.helpers;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServerHelper {

	
	public static void connect(int port, Handler handler)
	{
		Server server = new Server(port);
        server.setHandler(handler);

        try {
            server.start();
            server.join();
        } catch(Exception e) {
            server.destroy();
            }
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void connect(int port, Class className, String path)
	{
		Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(className, path);
        
        try {
            server.start();
            server.join();
        } catch(Exception e) {
            server.destroy();
            }
	}
}
