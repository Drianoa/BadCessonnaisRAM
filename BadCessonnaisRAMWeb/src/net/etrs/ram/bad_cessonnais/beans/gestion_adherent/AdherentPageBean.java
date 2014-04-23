package net.etrs.ram.bad_cessonnais.beans.gestion_adherent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import net.etrs.ram.bad_cessonnais.utils.JsfUtils;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Adherent;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Justificatif;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Origine;
import net.etrs.ram.bad_cessonais.entities.gestion_adherents.Sexe;
import net.etrs.ram.bad_cessonais.interop.Licencie;
import net.etrs.ram.bad_cessonais.interop.ServiceClassement;
import net.etrs.ram.bad_cessonais.services.gestion_adherents.dao.FacadeAdherent;


@Log4j
@ManagedBean
@ViewScoped
@FieldDefaults(level=AccessLevel.PRIVATE)
public class AdherentPageBean {

	@EJB
	FacadeAdherent facadeAdherent;
	
	@Getter	@Setter
	Licencie licencie;
	@Getter	@Setter
	Adherent adherent;
	
	@Getter	@Setter
	private List<Adherent> adherentFiltres;  
	
	/**
	 * Initialisation d'un nouvel adhérent.
	 */
	@PostConstruct		
	public void init(){
		adherent = facadeAdherent.newInstance();
	}
	

	
	/**
	 * Récupération de tous les adhérents
	 * @return
	 */
	public List<Adherent> getAdherents(){
	
		return facadeAdherent.readAll();
	
	}
	
	/**
	 * Utilisaé pour le passage à la vue suivante.
	 * @param a
	 */
	public void putInFlash(Adherent a){	
		JsfUtils.putInFlashScope("ADHERENT", a);

	}
	
	
	/**
	 * Permet de désactiver l'adhérebnt. Dans un prmier temps il sera supprimé.
	 * @param adh
	 */
	public void desactiverAdherent(Adherent adh){
		facadeAdherent.read(adh);
		facadeAdherent.delete(adh);
		JsfUtils.sendMessage(null, FacesMessage.SEVERITY_INFO, "Information","Suppresion effectuée");
		//log.info("Desactivation de l'adherent : "+ adh.toString());
	}	


	
	/**
	 * On recupere les informations de classement du licencié via le Webservice. 
	 */
	public void getClassementLicencieFFBa(){
		String licenceFFBa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("formAdherent:inputFFBaAdherent");

		try {
			URL url = new URL("http://201214-22:9090/ServeurFFbadWeb/ServiceClassement?wsdl");
			QName qname = new QName("http://interop.mockup/","ServiceClassementService");
			
			Service service = Service.create(url, qname);
			ServiceClassement sc = service.getPort(ServiceClassement.class);
			licencie = sc.classementLicencie(licenceFFBa);

		} catch (MalformedURLException e) {
			
			log.error("Imossible d'acceder au webservice", e);

		}
	}
	
	
	

	

	private void ecritureFluxPdf(ByteArrayOutputStream baos,HttpServletResponse response) throws IOException {
		ServletOutputStream os = response.getOutputStream();
		// Ecriture du flux
		for (Byte byte1 : baos.toByteArray()) {
			os.write(byte1);
		}
		os.flush();
		os.close();
	}
	private void mimePdf(String nomPdf, HttpServletResponse response) {
		// Construction de la réponse
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","inline; filename=\"" + nomPdf + "\"");
		response.setHeader("Cache-Control", "public");
	}
	public void renvoyerPDF(ByteArrayOutputStream out){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extCtx = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) extCtx.getResponse();
		try {
			
			mimePdf("FeuilleDePresence", response);
			response.setContentLength(out.size());
			
			ecritureFluxPdf(out, response);
			out.close();
		} catch (Exception e) {
			JsfUtils.sendMessage(e);
		}
		facesContext.responseComplete();
	}
	public void genererPDFPresenceAdherent(){
		// etape 1
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
		try {
	            // etape 2:
	            // creation du writer -> PDF ou HTML 
	            //PdfWriter.getInstance(document, new FileOutputStream(out));
			PdfWriter.getInstance(document, out);
	                      	
	            // etape 3: Ouverture du document
	            document.open();
	           
	            // etape 4: Ajout du contenu au document
	            document.add(new Phrase("Fiche de présence à l'entrainement du ..../..../...."));
	            
	            PdfPTable aTable = new PdfPTable(2);
	            int[] columnWidths = {10, 50};
	            	
	            aTable.setWidths(columnWidths);
	            PdfPCell c = new PdfPCell(new Phrase("Présent"));
	            aTable.addCell(c);
	            c = new PdfPCell(new Phrase("Nom - Prénom"));
	            aTable.addCell(c);

	    		for (Adherent a : getAdherents()) {
		            aTable.addCell(new PdfPCell());
	    			c = new PdfPCell(new Phrase(a.getNom()+" "+a.getPrenom()));
		            aTable.addCell(c);
	    		}
	            document.add(aTable);

	        }
	        catch(DocumentException de) {
	            System.err.println(de.getMessage());
	        }

	        // etape 5: Fermeture du document
	        document.close();

	        renvoyerPDF(out);

}
	
	
	
	
	/**
	 * On récupère la listes des justificatifs
	 * @return
	 */
	public Justificatif[] getListeJustificatif(){
		return facadeAdherent.getListeJustificatif();
	}
	public Origine[] getListeOrigine(){
		return facadeAdherent.getListeOrigine();
	}
	public Sexe[] getListeSexe(){
		return facadeAdherent.getListeSexe();
	}

}
