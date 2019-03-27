package com.identitas;

/**
 * Created by toshikijahja on 3/23/19.
 */

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.identitas.guice.ServiceLocatorContainer;
import com.identitas.module.IdentitasModule;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;


public class IdentitasJerseyModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        bind(ServiceLocator.class).toProvider(ServiceLocatorContainer.class);

        final Map<String, String> params = new HashMap<>();
        params.put("javax.ws.rs.Application", IdentitasApplication.class.getCanonicalName());
        bind(ServletContainer.class).in(Scopes.SINGLETON);
        serve("/*").with(ServletContainer.class, params);

        install(new IdentitasModule());
    }
}

