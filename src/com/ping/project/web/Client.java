package com.ping.project.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.ping.project.common.helpers.ServerHelper;

public class Client extends AbstractHandler
{
	public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
        throws IOException, ServletException
    {
		
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        response.getWriter().println("<script>");
        response.getWriter().println("function myFunction() {");
        response.getWriter().println("    document.getElementById(\"cargando\").style.display = \"block\";");
        response.getWriter().println("    document.getElementById(\"jpForm\").style.display = \"none\";");
        response.getWriter().println("}");
        response.getWriter().println("</script>");
        
        response.getWriter().println("<form id=\"jpForm\" name=\"jpForm\" method=\"post\" action=\"http://localhost:8473/Scanner\">");
        response.getWriter().println("<label>Clase A: IP/8</label> <br/>");
        response.getWriter().println("<label>Clase B: IP/16</label> <br/>");
        response.getWriter().println("<label>Clase C: IP/24</label> <br/>");
        response.getWriter().println("<label>Custom 32: IP/32</label> <br/>");
        
        response.getWriter().println("<label>Ingresar ip/mascara:</label>");
        response.getWriter().println("<input type=\"text\" name=\"ip\"/> <br/>");
        
        response.getWriter().println("<label>Puerto menor:</label>");
        response.getWriter().println("<input type=\"text\" name=\"lowPort\"/> <br/>");

        response.getWriter().println("<label>Puerto mayor:</label>");
        response.getWriter().println("<input type=\"text\" name=\"highPort\"/> <br/>");
        
        response.getWriter().println("<input type=\"submit\" value=\"Enviar\" onClick=\"myFunction()\" />");
        response.getWriter().println("</form>");
        
        
        response.getWriter().println("ip " + request.getRemoteAddr());
        response.getWriter().println("puerto " + request.getRemotePort());
        

        
        
        response.getWriter().println("<label id=\"cargando\" style=\"display:none\">Cargando...</label>");
            
    }

    public static void main(String[] args) throws Exception
    {
		ServerHelper.connect(8081,
				
				new Client()
				//agrego el valor a la DB
				
				//muestro cliente con datos nuevos
				
				//cliente en segundo plano que haga la coneccion al puerto
				
				);
    }
}
