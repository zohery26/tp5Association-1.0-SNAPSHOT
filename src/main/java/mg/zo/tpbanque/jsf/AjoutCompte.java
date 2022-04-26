package mg.zo.tpbanque.jsf;

import mg.zo.tpbanque.ejb.GestionnaireCompte;
import mg.zo.tpbanque.entities.CompteBancaire;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * 
 * @author acer
 */
@Named(value = "ajout")
@RequestScoped
public class AjoutCompte {
    
  @EJB
  private GestionnaireCompte gestionnaireCompte;
  
  private String nom;
  private int solde;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getSolde() {
    return solde;
  }

  public void setSolde(int solde) {
    this.solde = solde;
  }

  public String creer() {
    gestionnaireCompte.creerCompte(new CompteBancaire(nom, solde));
    return "listeComptes?faces-redirect=true";
  }
  
  
}
