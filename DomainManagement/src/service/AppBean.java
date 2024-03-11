package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import entitis.Shop;

@Named
@ApplicationScoped
public class AppBean implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = -564221162434503067L;
	private final List<Shop> shops = new ArrayList<>();
	private Shop newShop;

	public AppBean() {
		this.shops.add(new Shop(1, "Shop 1", "Beschreibung von Shop 1 a"));

		this.shops.add(new Shop(2, "Shop 2", "Beschreibung von Shop 2 b"));

		this.shops.add(new Shop(3, "Shop 3", "Beschreibung von Shop 3 c"));
	}

	@PostConstruct
	public void init() {
		System.out.println("ShopViewBean");
	}

	public List<Shop> getAllShops() {
		return this.shops;
	}

	public void add(final Shop shop) {
		this.shops.add(shop);
	}
	

	public void deleteAll() {
		this.shops.clear();
	}

	public Shop findShopById(final int id) {
		return this.shops.stream().filter(shop -> shop.getId() == id).findFirst().orElse(null);
	}

	public void save() {
		System.out.println("a new shop added: " + this.newShop.getName() + ", " + this.newShop.getDetails());
		this.add(this.newShop);
	}

	public Shop getNewShop() {
		if (this.newShop == null) {
			this.newShop = new Shop();
		}
		return this.newShop;
	}

	public void setNewShop(final Shop newShop) {
		this.newShop = newShop;
	}
	
    public Shop findById(int id) {
        for (Shop shop : shops) {
            if (shop.getId() == id) {
                return shop;
            }
        }
        return null;
    }
    
    public void update(Shop shop) {
        Shop shopToUpdate = findById(shop.getId());
        if (shopToUpdate != null) {
            shopToUpdate.setName(shop.getName());
            shopToUpdate.setDetails(shop.getDetails());
            
        } else {
            System.out.println("Shop not found.");
        }
    }

}
