package fr.cgi.AnalyserFluxREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
	static public String regexpContentTag(String tag) {
		return "<\\s*"+tag+"\\s*>\\s*([^<]*)<\\s*/\\s*"+tag+"\\s*>";
	}

	static public String regexpAttributTag(String tag, String attribut) {
		return "<\\s*"+tag+"\\s+"+attribut+"\\s*=\\s*[\"|']([^\"']+)[\"|']";
	}

	// Télécharge la page et renvoie son contenu
	static public String wget(String site) {
		StringBuffer content = new StringBuffer();
		String line;
		BufferedReader r;
		try {
			r = new BufferedReader(new InputStreamReader(new URL(site).openStream()));
			while ((line = r.readLine()) != null) {
				content.append(line);
			}
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch( IllegalArgumentException  e) {
			System.err.println(e.getMessage());
		}
		return content.toString();
	}
}
