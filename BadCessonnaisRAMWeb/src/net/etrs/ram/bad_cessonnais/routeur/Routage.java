package net.etrs.ram.bad_cessonnais.routeur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lombok.Getter;



@ManagedBean
@ApplicationScoped
@Getter
public class Routage {
	public final String accueil = "/test-layout.xhtml";
	
}
