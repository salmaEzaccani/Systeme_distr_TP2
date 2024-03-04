package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {

	@Autowired  //pour faire l'injection des depandances  "je demande a spring une implementation de cette interface que tu va linjecter  ""spring dispose deja dune impl generique
	private PatientRepository patientRepository;
	public static void main(String[] args)  {
		SpringApplication.run(JpaApApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		for(int i=0;i<100;i++) {
			patientRepository.save(new Patient(null, "hassan", new Date(), false, (int)(Math.random()*100)));
		}



		// je demande de afficher les 5 premier patient
		Page<Patient> patients=patientRepository.findAll(PageRequest.of(0,5));  // findAll ca aucun sens puique si jai millions de data pourquo je fait find all c bcp  c pour ca on utilise "la pagination avec spring data"  demander a afiicher jutse la prem page par ex
		System.out.println(" total pages :"+patients.getTotalPages());
		System.out.println("total elmts :"+patients.getTotalElements());
		System.out.println("Num page :"+patients.getNumber());
		List<Patient> content=patients.getContent();

		content.forEach(p->{
			 System.out.println("*************");
			 System.out.println(p.getId());
			 System.out.println(p.getNom());
			 System.out.println(p.getScore());
			 System.out.println(p.getDateNaissance());
			 System.out.println(p.isMalade());
		 });

		System.out.println("===========");
		Patient patient=patientRepository.findById(1L).orElse(null);


	if(patient!=null){
			System.out.println(patient.getNom());
			System.out.println(patient.isMalade() );
		}
		patient.setScore(870);
		patientRepository.save(patient);
		patientRepository.deleteById(1L);
	}
}
