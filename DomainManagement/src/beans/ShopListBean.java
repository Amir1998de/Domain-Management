package beans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import configuration.FreeMarkerService;
import entitis.Shop;
import service.AppBean;
 
import org.omnifaces.util.Faces;
import javax.faces.context.ExternalContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Named
@ViewScoped
public class ShopListBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7943202027989415807L;

	/**
	 * constants
	 */

	/**
	 * properties
	 */
	

	private List<Shop> shops = null;

	private Shop selectedShop;

	private int currentLevel = 1;
	@Inject
	private AppBean appBean;

	@PostConstruct
	public void init() {
		this.shops = this.appBean.getAllShops();
		setDomain();
		this.print("ShopListBean.init");
	}

	/**
	 * constructor
	 */
	public ShopListBean() {
		this.print("ShopListBean.constructor");
	}

	/**
	 * method
	 */

	public List<Shop> getShops() {
		return this.shops;
	}

	public void test() {

		System.out.println("test");

	}

	// renderTemplate
	private static String generatTemplate(final Shop shop) {
		System.out.println("renderTemplate :" + "shop.name");
		
		 String generatedText;

		final FreeMarkerService freeMarkerService = new FreeMarkerService();

		final Map<String, Object> dataModel = new HashMap<>();

		dataModel.put("shopName", shop.getName());
		dataModel.put("shopId", shop.getId());
		dataModel.put("shopDomain", shop.getDomain());

		try {
			generatedText = freeMarkerService.renderTemplate("information.ftl", dataModel);
			
		} catch (final Exception e) {
			e.printStackTrace();
			generatedText = "Fehler beim Verarbeiten der Vorlage.";
		}
		return generatedText;
	}
	
	public void downloadAllConfigs() {
        StringBuilder str = new StringBuilder();
        for (Shop s : shops){
        	str.append(generatTemplate(s));
        }
		
		download("configs" , str.toString());
		
	}
	
	public void downloadTemplate(final Shop shop) {
		
		String template = generatTemplate(shop);
		download(shop.getName() , template);
		
	}
	

	public static void download(String fName ,String temp) {
	     Faces.sendFile(fName + ".txt", true, output -> {
	         try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8))) {
	             writer.println(temp);
	          }
	     });
	}

	public void setDomain(){
		for(Shop s : shops){
			if(s.getId() == 1){
				s.setDomain("http://localhost:8082/");
			}else if(s.getId() == 2){
				s.setDomain("http://localhost2:8082/");
			}else{
				s.setDomain("");
			}

		}
	}
	
	
	

	public void print(final String str) {
		System.out.println(str);

	}

	public Shop getSelectedShop() {
		return this.selectedShop;
	}

	public void setSelectedShop(final Shop selectedShop) {
		this.selectedShop = selectedShop;
	}


	public void setCurrentLevel(final int currentLevel) {
		System.out.println("Current level selected: " + currentLevel);
		this.currentLevel = currentLevel;
	}

	public int getCurrentLevel() {
		System.out.println("currentLevel: " + this.currentLevel);
		return this.currentLevel;
	}

	public void redirectToShopList() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("ShopList.xhtml");
	}

	public void deleteShop(final Shop shop) {
		this.shops.remove(shop);
	}

}
