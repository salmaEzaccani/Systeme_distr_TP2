package ma.enset.jpaap.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

//ca c spring data "ca contient tous les methodes classique de DAO add,delete.."
public interface PatientRepository extends JpaRepository<Patient,Long> {

//on a des methodes de bases mais jai besoin dajouter dautres methodes
  //par exemple afficher juste les patients qui sont malades alors que spring

}
