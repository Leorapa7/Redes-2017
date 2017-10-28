package com.ping.project.console;
import java.util.Scanner;
import com.ping.project.common.scanners.IPScanner;
import com.ping.project.common.entities.Configuration;
import com.ping.project.common.entities.IPInfo;
import com.ping.project.common.factories.ReportsFactory;

public class Console {
	private static Scanner s;

	public static void main(String[] args) throws Exception
    {
		IPInfo ipInfo = new IPInfo();
	  	s = new Scanner (System.in);
	  	System.out.println("Ingresar ip/mascara:");
	  	String input = s.next ();
	  	System.out.println("Puerto menor:");
	  	String lowPort = s.next ();
	  	System.out.println("Puerto mayor:");
	  	String highPort = s.next ();
	  	Configuration configuration = new Configuration(Integer.parseInt(lowPort) ,
				Integer.parseInt(highPort));
	  	
        IPScanner ip = new IPScanner(ipInfo, true, configuration);
	  	ip.fAnalizarEntrada(input);
	  	System.out.println(new ReportsFactory(ipInfo, false).GenerateReport());
    }
}
