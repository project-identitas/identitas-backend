package com.identitas.guice;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.glassfish.hk2.api.ServiceLocator;

@Singleton
public class ServiceLocatorContainer implements Provider<ServiceLocator> {
    private volatile ServiceLocator locator;

    @Override
    public ServiceLocator get() {
        if (locator == null) {
            throw new IllegalStateException("ServiceLocator is not found");
        }
        return locator;
    }

    public void setServiceLocator(final ServiceLocator locator) {
        this.locator = locator;
    }
}
