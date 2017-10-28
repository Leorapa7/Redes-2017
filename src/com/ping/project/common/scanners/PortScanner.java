package com.ping.project.common.scanners;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ping.project.common.entities.Configuration;
import com.ping.project.common.entities.Host;
import com.ping.project.common.entities.IPInfo;

public class PortScanner {
    private Configuration _configuration;
	public PortScanner(Configuration configuration) {
		_configuration = configuration;
	}

	public void scan(Host host, boolean _console) {
		IPInfo scan = new IPInfo();
		scan.ip = host.hostName;
		if (_console) {
			System.out.println("Scanning...");
		}
		for( int current = _configuration.lowPort; current <= _configuration.highPort; current++ ) {
			try {
				Socket s = new Socket();
				s.connect( new InetSocketAddress( host.hostName, current ), _configuration.timeout );
				s.close();
				host.ports.add(String.valueOf(current));
			}
			catch( IOException ioe ) {
				//El puerto esta cerrado.
			}
		}
	}
}
