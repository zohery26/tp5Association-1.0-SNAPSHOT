package mg.zo.tpbanque.jsf;

import mg.zo.tpbanque.ejb.GestionnaireCompte;
import mg.zo.tpbanque.entities.CompteBancaire;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author acer
 */
@Named(value = "modifierNom")
@ViewScoped
public class ModifierNom implements Serializable {

  @EJB
  private GestionnaireCompte gestionnaireCompte;

  private Long id;
  private CompteBancaire compte;
  private String nom;

  public String getNom() {
    return nom;
  }

  public void setMontant(String nom) {
    this.nom = nom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CompteBancaire getCompte() {
    return compte;
  }

  public void loadCompte() {
    compte = gestionnaireCompte.findById(id);
  }

  public String enregistrer() {
    gestionnaireCompte.update(compte);
    Util.addFlashInfoMessage("Nouveau nom enregistré : " + compte.getNom());
    return "listeComptes?faces-redirect=true";
  }

}
