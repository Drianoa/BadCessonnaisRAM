package net.etrs.ram.bad_cessonnais.beans.administration;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.etrs.ram.bad_cessonais.entities.gestion_tournoi.Tournoi;
import net.etrs.ram.bad_cessonais.services.gestion_tournoi.dao.FacadeTournoi;
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
	private static RSSFeedGenerator rssFeed = RSSFeedGeneratorFactory.getDefault();
	private static RSS rss = new RSS(RSS.VERSION_2_0);
	
	
	@EJB
	FacadeTournoi facadeTournoi;
	
	

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/xml;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
		String rss= genererRss();
		
		out.print(rss);

	}
	
	//Génération du fichier RSS
	private String genererRss(){
		Channel channel =  headerRss();
		return contenuRss(channel);
	}

	//header du RSS
	private Channel headerRss(){
		 Channel channel = new Channel("Liste des tournois", "http://localhost:9090/BadCessonnaisRAMWeb/pages/gestion-tournoi/lister-tournoi.xhtml", "Voici la listes des tournois du club Badminotn Cessonnais");
		 channel.setWebMaster("admin@badCessonnais.fr");
		 return channel;
	}
	
	//On peuple le RSS 
	private String contenuRss(Channel channel){
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		 
		 List<Tournoi> listeTournoi =facadeTournoi.readAll();
		 
		 for (Tournoi t : listeTournoi) {
			 Item item1 = new Item(t.getNom(), "http://", "Tournoi du "+sdf.format(t.getDateTournoi()));
			 item1.setAuthor("Admin");
			 item1.setPubDate(new Date());
			 channel.addItem(item1);
		}
		 rss.addChannel(channel);

		 try {
			return rssFeed.generateAsString(rss);
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}

	 }
	
	

	
	

}
