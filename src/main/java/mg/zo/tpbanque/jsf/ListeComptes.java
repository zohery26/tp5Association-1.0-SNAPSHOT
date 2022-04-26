package mg.zo.tpbanque.jsf;

import mg.zo.tpbanque.ejb.GestionnaireCompte;
import mg.zo.tpbanque.entities.CompteBancaire;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;

/**
 * 
 * @author acer
 */
@Named
@ViewScoped
public class ListeComptes implements Serializable {

  @EJB
  private GestionnaireCompte gestionnaireCompte;
  
  private List<CompteBancaire> listeComptes;

  public ListeComptes() {
  }
  
  public List<CompteBancaire> getAllComptes() {
    if (listeComptes == null) {
      listeComptes = gestionnaireCompte.getAllComptes();
    }
    return listeComptes;
  }
  
  public boolean filterBySolde(Object valeurColonne, Object valeurFiltre, Locale locale) {
    String valeurFiltreString = (String) valeurFiltre;
    if (valeurFiltreString.equals("")) {
      return true;
    }
    try {
      return (Integer) valeurColonne >= Integer.valueOf(valeurFiltreString);
    } catch (NumberFormatException e) {
      return true;
    }
  }
  
  public String supprimerCompte(CompteBancaire compte) {
    gestionnaireCompte.supprimer(compte);
    Util.addFlashInfoMessage("Compte de " + compte.getNom() + " supprimé");
    // Redirection pour faire afficher la nouvelle liste, sans le compte
    // supprimé
    return "listeComptes?faces-redirect=true";
  }
  
}
