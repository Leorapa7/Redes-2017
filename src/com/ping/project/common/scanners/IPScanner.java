package com.ping.project.common.scanners;
import java.io.IOException;
import java.net.InetAddress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ping.project.common.entities.Configuration;
import com.ping.project.common.entities.Host;
import com.ping.project.common.entities.IPInfo;

public class IPScanner {
	private PortScanner _portScanner;
    private IPInfo _ipInfo;
    private boolean _console;
	public IPScanner(IPInfo ipInfo,boolean console, Configuration configuration)
	{
		_ipInfo = ipInfo;
		_console = console;
		_portScanner = new PortScanner(configuration);
	}
	
    public void fAnalizarEntrada (String input) throws IOException {
    	String [] splitInput = input.split("/");
    	String temp= splitInput[0];
    	String [] ipSplit = temp.split("\\.");
    	String subnet = "";
    	_ipInfo.ip = input;
    	switch (Integer.parseInt(splitInput[1])){
    	case 8: 
    		_ipInfo.clase = "Clase A";
    		subnet = ipSplit[0];
    		procesarMascaraA (subnet); 
    		break;
    	case 16:
    		_ipInfo.clase = "Clase B";
    		subnet = ipSplit[0]+"."+ipSplit[1];
    		procesarMascaraB (subnet); 
    		break;
    	case 24:
    		_ipInfo.clase = "Clase C";
    		subnet = ipSplit[0]+"."+ipSplit[1]+"."+ipSplit[2];
    		procesarMascaraC (subnet); 
    		break;
    	case 32: 
    		_ipInfo.clase = "Custom 32";
    		subnet = ipSplit[0]+"."+ipSplit[1]+"."+ipSplit[2]+"."+ipSplit[3];
    		revisarHosts(subnet);
    		break;
    	default: 
    		_ipInfo.clase = "Default";
    		break;
    	}
    }
    
    public void procesarMascaraA(String subnet) throws IOException
    {
    	scanningAdvice('A');
        for (int i = 1; i <= 255; i++)
        {
	        for (int j = 0; j <= 255; j++)
	        {
		        for (int k = 0; k <= 255; k++)
		        {
		        	if ((i==0 && j==0 && k==0) || (i==255 && j==255 && k==255)){
			            String host = subnet + "." + i + "." + j  + "." + k;
			            revisarHosts (host);
		        	}
		        }
	        }
        }
    }
	
	public void procesarMascaraB(String subnet) throws IOException
    {
    	scanningAdvice('B');   
        for (int i = 1; i <= 255; i++)
        {
	        for (int j = 0; j <= 255; j++)
	        {
	        	if ((i==0 && j==0) || (i==255 && j==255)){
		            String host = subnet + "." + i + "." + j;
		            revisarHosts (host);
	        	}
	        }
        }
    }
	
	public void procesarMascaraC(String subnet) throws IOException
    {
    	scanningAdvice('C');
        for (int i = 1; i < 255; i++)
        {
            String host = subnet + "." + i;
            revisarHosts(host);
        }
    }
	
	private void scanningAdvice(char letter)
	{
		if (_console) {
            System.out.println("Scanning..." + letter + "...");
		}
	}
	
	private  void revisarHosts (String hostName) throws IOException {
		int timeout = 50;
		if (InetAddress.getByName(hostName).isReachable(timeout))
        {
            Host host = new Host();
            host.hostName = hostName;
            _portScanner.scan(host, _console);
            if (host.ports.size() > 0) {
                _ipInfo.hosts.add(host);
			}
        }
	}

	public String GetJson (){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(_ipInfo);
	}
}
