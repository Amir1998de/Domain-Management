package beans;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entitis.Shop;

/**
 * x
 * @author Amirhossein Vatani
 *
 */
@Named
@ViewScoped
public class ShopViewBean implements Serializable {

	private static final long serialVersionUID = -6043746077735043017L;

	private Shop selectedShop;

	@PostConstruct
	public void init() {
		System.out.println("ShopViewBean");
	}

	public Shop getSelectedShop() {
		return this.selectedShop;
	}

	public void setSelectedShop(final Shop selectedShop) {
		this.selectedShop = selectedShop;
	}

}
