package fr.cgi.AnalyserFluxREST;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Tutorial : http://www.vogella.com/articles/REST/article.html

@Path("/collect")
public class UrlCollector {
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Urls getXMLCollect(@QueryParam("url") String newUrl) {
		Urls urls = new Urls();
		String currentPage = Utils.wget(newUrl);
		urls.addAll(extractUrlSimple(currentPage));
		urls.addAll(extractUrlAHref(currentPage,newUrl));
		return urls;
	}
	
	
	// Donne les URL du texte 
	  private Set<String> extractUrlSimple(String text) {
		  TreeSet<String> result = new TreeSet<String>();
		  Pattern p = Pattern.compile("https?://[^\\s\"';<]+",Pattern.CASE_INSENSITIVE);
		  Matcher m = p.matcher(text);
		  while(m.find()) {
			  result.add(m.group());
		  }
		  return result;
	  }
	  
	  // Donne les URL des liens a href du texte
	  private Set<String> extractUrlAHref(String text, String currentUrl) {
		  TreeSet<String> result = new TreeSet<String>();  
		  Pattern p = Pattern.compile(Utils.regexpAttributTag("a","href"),Pattern.CASE_INSENSITIVE);
		  Matcher m = p.matcher(text);
		  while(m.find()) {
			  String href = m.group(1);
			  try {
				  if(!href.contains("http:") && !href.contains("https:")){
					  	URL newUrl = new URL(currentUrl);
					  	href =  newUrl.getHost() + "/" + newUrl.getPath() + "/"  + href;
					  	href = href.replaceAll("//+", "/");
					  	href = newUrl.getProtocol() + "://" + href;
				  }
				  result.add(href);
			  } catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }
		  }
		  return result;
	  }
	  
	  
}
