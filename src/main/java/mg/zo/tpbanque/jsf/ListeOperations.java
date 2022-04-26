package mg.zo.tpbanque.jsf;

import mg.zo.tpbanque.ejb.GestionnaireCompte;
import mg.zo.tpbanque.entities.CompteBancaire;
import mg.zo.tpbanque.entities.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 *
 * @author acer
 */
@Named(value = "listeOperations")
@RequestScoped
public class ListeOperations implements Serializable {

  @EJB
  private GestionnaireCompte gestionnaireCompte;

  private Long idCompteBancaire;
  private CompteBancaire compte;

  public ListeOperations() {
  }

  public Long getIdCompteBancaire() {
    return idCompteBancaire;
  }

  public void setIdCompteBancaire(Long idCompteBancaire) {
    this.idCompteBancaire = idCompteBancaire;
    System.out.println("**** idCompteBancaire = " + this.idCompteBancaire);
  }

  public List<OperationBancaire> getOperations() {
    return compte.getOperations();
  }

  public void setCompte() {
    compte = gestionnaireCompte.findById(idCompteBancaire);
  }

  public CompteBancaire getCompte() {
    return compte;
  }

}
