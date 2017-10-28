package com.ping.project.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ping.project.common.entities.Configuration;
import com.ping.project.common.entities.IPInfo;
import com.ping.project.common.factories.ReportsFactory;
import com.ping.project.common.helpers.AgentHelper;
import com.ping.project.common.helpers.ServerHelper;
import com.ping.project.common.scanners.IPScanner;

@SuppressWarnings("serial")
@WebServlet("/Scanner")
public class Servlet  extends HttpServlet
{
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		//cambiar aca por lo que venga de parametro del request
		String result = AgentHelper.getRuntimeValue(request.getParameter("jarName"));
		
		Configuration configuration = new Configuration(Integer.parseInt(request.getParameter("lowPort")) ,
				Integer.parseInt(request.getParameter("highPort")));
		IPInfo ipInfo = new IPInfo();
        String ip = request.getParameter("ip");
        
        IPScanner ipScanner = new IPScanner(ipInfo, false, configuration);
        ipScanner.fAnalizarEntrada(ip);

        //esto es a ejemplo la realidad es que no va se debe de hacer la relacion en la base de datos enla linea de abajo
        response.getWriter().println(new ReportsFactory(ipInfo, true).GenerateReport());
        
        //aca se tiene que enviar a la base de datos
        
        
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//result va a devolver todo el jar que se ejecuto
		
		//para compilar el dateUtils es boton derecho en pom en maven. Maven instal para bajar dependencias
		//maven build para generar JAR
		String result = AgentHelper.getRuntimeValue(req.getParameter("jarName"));
		//ir a la base de datos a guardarlo
		
		//si salio todo bien 200
		resp.setHeader(getServletName(), getServletInfo());
		
		super.doGet(req, resp);
	}
	public static void main(String[] args) {
		ServerHelper.connect(8473, Servlet.class, "/Scanner");
	}
}
