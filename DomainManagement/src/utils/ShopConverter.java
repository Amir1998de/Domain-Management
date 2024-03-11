package utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entitis.Shop;
import service.AppBean;

@FacesConverter(value = "shopConverter", managed = true)
public class ShopConverter implements Converter<Shop> {

	@Inject
	private AppBean appBean;

	/*
	 * http://localhost:8080/Test/shop/1 -> value = 1
	 *
	 */
	@Override
	public Shop getAsObject(final FacesContext context, final UIComponent component, final String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		try {

			final int id = Integer.parseInt(value);
			return this.appBean.findShopById(id);
		} catch (final NumberFormatException e) {
			// TODO
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Shop value) {
		if (value instanceof Shop) {
			return String.valueOf(value.getId());
		} else {
			return "";
		}
	}

	public void setAppBean(final AppBean appBean) {
		this.appBean = appBean;
	}
}
