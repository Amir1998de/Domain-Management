package test;

import utils.ShopConverter;

public class test {

	public static void main(final String[] args) {

		// final ShopService shopService = new ShopService();
		// System.out.println(shopService.findShopById(1));

		final ShopConverter converter = new ShopConverter();
		System.out.println();

		// IoC with Factory
		final Database database = new Database();
		database.cred.login();

		// IoC with DI
		final Injector injector = new Injector();
		final Client client = injector.getClient();
		client.process();
	}

}
/*
 * IoC : Inversion of Control
 */

// IoC with Factory
class Factory {
	public static Cred getCred() {
		return new Cred();
	}
}

class Database {
	Cred cred = Factory.getCred();

}

class Cred {
	void login() {
		System.out.println("login.");
	}
}

/*
 * DI : Dependency Injection
 */

// IoC with DI
class Service {
	void print() {
		System.out.println("do something.");
	}
}

class Client {
	Service service;

	// Constructor Injection
	public Client(final Service service) {
		this.service = service;
	}

	// Method Injection
	public void setService(final Service service) {
		this.service = service;
	}

	void process() {
		this.service.print();
	}
}

class Injector {
	Client getClient() {
		return new Client(new Service());
	}

}
