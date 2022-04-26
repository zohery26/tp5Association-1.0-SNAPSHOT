package mg.zo.tpbanque.jsf;

import mg.zo.tpbanque.ejb.GestionnaireCompte;
import mg.zo.tpbanque.entities.CompteBancaire;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 *
 * @author acer
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert {

  @EJB
  private GestionnaireCompte gestionnaireCompte;

  private long idSource;
  private long idDestination;
  private int montant;

  public long getIdSource() {
    return idSource;
  }

  public void setIdSource(long id) {
    this.idSource = id;
  }

  public long getIdDestination() {
    return idDestination;
  }

  public void setIdDestination(long id) {
    this.idDestination = id;
  }

  public int getMontant() {
    return montant;
  }

  public void setMontant(int montant) {
    this.montant = montant;
  }

  public String enregistrer() {
    boolean erreur = false;
    
    CompteBancaire source = gestionnaireCompte.findById(idSource);
    if (source == null) {
      Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
      erreur = true;
    } else {
      if (source.getSolde() < montant) {
        Util.messageErreur("Solde de " + source.getNom() + " insuffisant", "Solde insuffisant", "form:montant");
        erreur = true;
      }
    }

    CompteBancaire destination = gestionnaireCompte.findById(idDestination);
    if (destination == null) {
      Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
      erreur = true;
    }
    
    if (erreur) {
      return null;
    }
    
    gestionnaireCompte.transferer(source, destination, montant);
    Util.addFlashInfoMessage("Transfert de " + source.getNom() + " vers " 
            + destination.getNom() 
            + " pour un montant de " + montant + " correctement effectué");
    return "listeComptes?faces-redirect=true";
  }
}
