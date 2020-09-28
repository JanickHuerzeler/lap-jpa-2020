package ch.fhnw.eaf.jpa.test7;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
public class Test7 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Test7.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Customer c;
		c = new Customer("Meier", 24);
		em.persist(c);
		System.out.println(c.getId());

		c = new Customer("Mueller", 24);
		em.persist(c);
		System.out.println(c.getId());

		System.out.println("done");

		// a) PKs: 1, 2, 33, 34, 35, 36, 37, 38
		// b) PKs: 1, 2, 3, 4, 5, 6 (Neue Tablle "HYBERNATE_SEQUENCES" mit column NEXT_VAL)
	}

}
