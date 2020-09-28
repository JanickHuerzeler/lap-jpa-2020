package ch.fhnw.eaf.jpa.test1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
public class Test1 implements CommandLineRunner {

	@PersistenceUnit
	EntityManagerFactory emf;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Test1.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		EntityManager em = emf.createEntityManager();

		Customer c1 = em.find(Customer.class, 1);
		Customer c2 = em.find(Customer.class, 1);

		System.out.println(c1);
		System.out.println(c2);

		System.out.println(c1 == c2);

		em.close();
		System.out.println("done");

		part1();

		part2();

		System.exit(0);
	}

	/**
	 * Persistenzkontext leeren
	 */
	private void part1() {
		EntityManager em = emf.createEntityManager();

		Customer c1 = em.find(Customer.class, 1);
		em.clear();
		Customer c2 = em.find(Customer.class, 1);

		System.out.println(c1);
		System.out.println(c2);

		System.out.println(c1 == c2);

		em.close();
		System.out.println("done part1");
	}

	/**
	 * Unterschiedliche Entity-Manager
	 */
	private void part2() {
		Customer c1 = emf.createEntityManager().find(Customer.class, 1);
		Customer c2 = emf.createEntityManager().find(Customer.class, 1);

		System.out.println(c1);
		System.out.println(c2);

		System.out.println(c1 == c2);

		System.out.println("done part2");
	}
}
