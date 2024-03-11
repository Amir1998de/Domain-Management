package configuration;

import javax.faces.application.ResourceHandler;
import javax.servlet.ServletContext;

import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class ShopRewriteConfiguration extends HttpConfigurationProvider {

	private static final boolean ENABLED = false;

	@Override
	public Configuration getConfiguration(final ServletContext arg0) {

		final ConfigurationBuilder cb = ConfigurationBuilder.begin();

		cb.addRule().perform(Log.message(Level.INFO, "Rewrite is active."));

		if (!ShopRewriteConfiguration.ENABLED)
			return cb;

		// /javax.faces.resource/main.css.xhtml?ln=css
		// ResourceHandler.RESOURCE_IDENTIFIER => /javax.faces.resource
		cb.addRule(Join.path("/resources/{ln}/{file}").to(ResourceHandler.RESOURCE_IDENTIFIER + "/{file}.xhtml"));

		// /javax.faces.resource/watermark/watermark.js.xhtml?ln=primefaces
		cb.addRule(Join.path("/resources/{ln}/{subfolder}/{file}")
				.to(ResourceHandler.RESOURCE_IDENTIFIER + "/{subfolder}/{file}.xhtml"));

		// http://localhost:8080/Test/shop //
		// to("/shop/shopView.xhtml?shopId=1")

		// cb.addRule(Join.path("/s/{shopId}").to("/shop/shopView.xhtml"));

		cb.addRule(Join.path("/shopId/{shopId}").to("/shop/shopView.xhtml"));

		return cb;
	}

	@Override
	public int priority() {
		return 0;
	}

}
