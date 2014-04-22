package net.etrs.ram.bad_cessonnais.beans.administration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import viecili.jrss.generator.RSSFeedGenerator;
import viecili.jrss.generator.RSSFeedGeneratorFactory;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.Item;
import viecili.jrss.generator.elem.RSS;

/**
 * Servlet implementation class fluxRss
 */
@WebServlet({ "/fluxRss", "/RSS" })
public class fluxRss extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/xml;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
		String rss= testRss();
		
		out.print(rss);

	}
	
	
	 public String testRss(){
		 final RSSFeedGenerator rssFeed = RSSFeedGeneratorFactory.getDefault();
		 final RSS rss = new RSS(RSS.VERSION_2_0);
		  
		 final Channel channel = new Channel("List", "http://magicsupremacy.fr/index.jsp#!menu=deck&amp;submenu=search_by_events", "Une liste des derniers Tournois Magic l'Assemblée ajoutés, tout format confondu");
		 channel.setWebMaster("admin@magicsupremacy.fr");
		  
		 final Item item1 = new Item("mon Tournoi", "http://magicsupremacy.fr/index.jsp#!menu=deck&amp;event=115", "description simplifiée du tournoi #2853353");
		 item1.setAuthor("Admin");
		 item1.setPubDate(new Date());
		  
		 final Item item2 = new Item("Tournoi 2 - Standard 2010-2011 [ZEN M11 SOM]", "http://magicsupremacy.fr/index.jsp#!menu=deck&amp;event=114", "description simplifiée du tournoi #2853358");
		 item2.setAuthor("Admin");
		 item2.setPubDate(new Date());
		  
		 channel.addItem(item1);
		 channel.addItem(item2);
		  
		 rss.addChannel(channel);
		  
		 try {
			return rssFeed.generateAsString(rss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 
		 
		
		 
		 
		 	
	 }
	
	
	
	
	
	

}
