package com.ping.project.common.factories;
import com.ping.project.common.entities.Host;
import com.ping.project.common.entities.IPInfo;

public class ReportsFactory {
    private IPInfo _ipInfo;
    private String _line;
    private boolean _html;
	public ReportsFactory(IPInfo ipInfo, boolean html)
	{
		_html = html;
		_ipInfo = ipInfo;
		if (_html) {
			_line = "<br />";
		}
		else {
			_line = System.lineSeparator();
		}
	}
	
	public String GenerateReport (){
		StringBuilder str = new StringBuilder();
		addLine(str, verifiTag("<h1>"), "IP escaneada: ", _ipInfo.ip, verifiTag("</h1>"));
		addLine(str, verifiTag("<h2>"), "", _ipInfo.clase, verifiTag("</h2>"));
		for (Host host : _ipInfo.hosts) {
			addLine(str, verifiTag("<h3>"), "Host: ", host.hostName, verifiTag("</h3>"));
			addLine(str, verifiTag("<label>"), "Puertos abiertos: ", "", verifiTag("</label>"));
			if (_html) {
				addLine(str, "<ul>", "", "", "");
			}
			for (String port : host.ports) {
				addLine(str, verifiTag("<li>"), "", port, verifiTag("</li>"));
			}
			if (_html) {
				addLine(str, "", "", "", "</ul>");
			}
		}
		
		if (_ipInfo.hosts.size() == 0) {
			addLine(str, verifiTag("<label>"), "No hay puertos abiertos en la ip seleccionada.", "", verifiTag("</label>"));
		}
		

		addLine(str, verifiTag("</br><label><strong>"), "Profesor.", "", verifiTag("</strong></label>"));
		addLine(str, verifiTag("<label>"), "German Basisty.", "", verifiTag("</label></br>"));
		addLine(str, verifiTag("<label><strong>"), "Alumnos.", "", verifiTag("</strong></label>"));
		addLine(str, verifiTag("<label>"), "German Dario.", "", verifiTag("</label>"));
		addLine(str, verifiTag("<label>"), "Rodrigo Vallaro.", "", verifiTag("</label>"));
		addLine(str, verifiTag("<label>"), "Leonel Rapagnani.", "", verifiTag("</label>"));
		
		return str.toString();
	}
	
	private String verifiTag(String tag)
	{
		return _html ? tag : "";
	}
	
	private void addLine(StringBuilder str, String tag, String title, String content, String closeTag)
	{
		str.append(tag);
		str.append(title);
		str.append(content);
		str.append(closeTag);
		str.append(_line);
	}
}
