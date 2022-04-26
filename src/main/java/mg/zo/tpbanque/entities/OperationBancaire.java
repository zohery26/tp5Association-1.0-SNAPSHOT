/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.zo.tpbanque.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author acer
 */
@Entity
public class OperationBancaire implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String description;
  private LocalDateTime dateOperation;
  private int montant;

  public OperationBancaire() {
  }

  public OperationBancaire(String description, int montant) {
    this.description = description;
    this.montant = montant;
    this.dateOperation = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDateOperation() {
    return dateOperation;
  }

  public int getMontant() {
    return montant;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof OperationBancaire)) {
      return false;
    }
    OperationBancaire other = (OperationBancaire) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

    @Override
    public String toString() {
        return "mg.zo.tpbanque.entities.OperationBancaire[ id=" + id + " ]";
    }

}
