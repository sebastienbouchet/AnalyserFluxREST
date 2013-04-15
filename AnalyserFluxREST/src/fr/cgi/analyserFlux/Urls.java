package fr.cgi.analyserFlux;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Urls {
	
	public Urls() {
		url = new TreeSet<String>();
	}
	
	@XmlElement
	public Set<String> url;
	
	public void addAll(Collection<String> c) {
		url.addAll(c);
	}

}
