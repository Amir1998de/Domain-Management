package service;

import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;

@Named
@RequestScoped
public class SelectLevelListener {

	private boolean errorOccured = false;

	public int handleNavigation(final SelectLevelEvent selectLevelEvent) {
		if (this.errorOccured) {
			return 2;
		} else {
			return selectLevelEvent.getNewLevel();
		}
	}

	public void setErrorOccured(final boolean errorOccured) {
		this.errorOccured = errorOccured;
	}
}
