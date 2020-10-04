package ch.fhnw.eaf.jpa.Uebung03.Aufgabe3;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.jpa.model.Address;
import ch.fhnw.eaf.jpa.model.Customer;
import ch.fhnw.eaf.jpa.model.Order;
import ch.fhnw.eaf.jpa.model.OrderLine;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
public class Aufgabe3 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Aufgabe3.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("-------------------- Aufgabe 3 --------------------");

		Customer c1 = new Customer("Haller", 52);

		Order o1 = new Order();

		for (int i = 0; i < 10; i++) {
			o1.addOrderLine(new OrderLine());
		}

		// em.persist(o1);

		c1.addOrder(o1);
		em.persist(c1);

		System.out.println("Customer ID: " + c1.getId());
		System.out.println("Order ID: " + o1.getId());

		Order loadedOrder = em.getReference(Order.class, o1.getId());
		OrderLine deletedOl = loadedOrder.deleteFirstOrderLine();
		em.remove(deletedOl);
		em.merge(loadedOrder);

		System.out.println("-------------------- END Aufgabe 3 --------------------");
	}

}
