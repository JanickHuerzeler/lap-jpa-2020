package ch.fhnw.eaf.jpa.Uebung03;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.jpa.model.Address;
import ch.fhnw.eaf.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
public class Aufgabe1 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Aufgabe1.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Customer c1 = new Customer("Lee", 44);
		Customer c2 = new Customer("Gosling", 55);
		Address a = new Address("Dornacherstrasse", "Basel");
		c1.setAddress(a);
		c2.setAddress(a);
		em.persist(a);
		em.persist(c1);
		em.persist(c2);
	}

}
