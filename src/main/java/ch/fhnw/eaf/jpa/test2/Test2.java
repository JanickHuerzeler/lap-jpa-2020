package ch.fhnw.eaf.jpa.test2;

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
public class Test2 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Test2.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Customer c = em.find(Customer.class, 1);

		System.out.println(c.getAddress().getClass());
		// LAZY: class ch.fhnw.eaf.jpa.model.Address$HibernateProxy$ZP2R9Why
		// EAGER: class ch.fhnw.eaf.jpa.model.Address

		c.setAge(55);
		c.getAddress().setStreet("Bahnhofstrasse 5");

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			c.getAddress().getStreet();
		}
		System.out.println(System.currentTimeMillis() - start);
		// Lazy: 124ms
		// Eager: 6ms

		System.out.println("done");
		System.exit(0);
	}
}
