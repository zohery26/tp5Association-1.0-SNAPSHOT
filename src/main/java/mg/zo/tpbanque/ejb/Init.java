package mg.zo.tpbanque.ejb;

import mg.zo.tpbanque.entities.CompteBancaire;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * 
 *
 * @author acer
 */
@Singleton
@Startup
public class Init {

  @EJB
  private GestionnaireCompte gestionnaireCompte;

  @PostConstruct
  public void initComptes() {
    if (gestionnaireCompte.nbComptes() != 0) {
      return;
    }
    gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
    gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
    gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
    gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
  }
}
