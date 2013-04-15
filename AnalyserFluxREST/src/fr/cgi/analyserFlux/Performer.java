package fr.cgi.analyserFlux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/perform")
public class Performer {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getXMLCollect(@QueryParam("url") String url) {
		String currentPage = Utils.wget(url);
		Pattern p = Pattern.compile(Utils.regexpContentTag("title"),Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(currentPage);
		if(m.find()) {
			return m.group(1);
		}
		return "";
	}

}
