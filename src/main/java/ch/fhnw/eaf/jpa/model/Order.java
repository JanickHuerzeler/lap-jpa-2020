package ch.fhnw.eaf.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

	public Order() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	@ManyToOne
	private Customer customer;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Set<OrderLine> orderLines;

	public void addOrderLine(OrderLine ol) {
		if (this.orderLines == null)
			this.orderLines = new HashSet<OrderLine>();

		this.orderLines.add(ol);
	}

	public OrderLine deleteFirstOrderLine() {
		if (this.orderLines != null && this.orderLines.size() >= 1) {
			// this.orderLines.remove(0);
			OrderLine ol = this.orderLines.iterator().next();
			if (this.orderLines.remove(ol))
				return ol;
			else
				return null;

		}
		return null;
	}

}
