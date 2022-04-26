package mg.zo.tpbanque.ejb;

import mg.zo.tpbanque.entities.CompteBancaire;;
import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 *
 * @author acer
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "zoHery5122",
        databaseName = "banque",
        properties = {
          "useSSL=false",
          "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

  @PersistenceContext
  private EntityManager em;

  public void creerCompte(CompteBancaire compte) {
    em.persist(compte);
  }

  public List<CompteBancaire> getAllComptes() {
    TypedQuery query = 
            em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
    return query.getResultList();
  }

  public long nbComptes() {
    TypedQuery<Long> query = 
            em.createQuery("select count(c) from CompteBancaire c", Long.class);
    return query.getSingleResult();
  }

  public void transferer(CompteBancaire source, CompteBancaire destination,
          int montant) {
    source.retirer(montant);
    destination.deposer(montant);
    update(source);
    update(destination);
  }

  public CompteBancaire update(CompteBancaire compteBancaire) {
    return em.merge(compteBancaire);
  }
  
  public void supprimer(CompteBancaire compte) {
    em.remove(em.merge(compte));
  }
  
  public CompteBancaire findById(long id) {
    return em.find(CompteBancaire.class, id);
  }
  
    public void deposer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.deposer(montant);
      update(compteBancaire);
    }
    
    public void retirer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.retirer(montant);
      update(compteBancaire);
    }
  
}
