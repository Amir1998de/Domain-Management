package entitis;

import java.io.Serializable;

public class Shop implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5412302503045831598L;
	private int id;
	private String name;
	private String details;
	private String domain;

	/*
	 * Constructor
	 */
	public Shop() {
	}

	public Shop(final int id, final String name, final String details) {
		this.name = name;
		this.details = details;
		this.id = id;
	}

	/*
	 * Setter and Getter
	 */
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "Shop [id=" + this.id + ", name=" + this.name + ", details=" + this.details + "]";
	}

}