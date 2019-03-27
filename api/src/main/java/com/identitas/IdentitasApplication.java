package com.identitas;

import com.google.inject.Injector;
import com.identitas.guice.IdentitasGuiceBridge;
import com.identitas.guice.ServiceLocatorContainer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

/**
 * Created by toshikijahja on 3/23/19.
 */
public class IdentitasApplication extends ResourceConfig {

    @Inject
    public IdentitasApplication(final ServiceLocator serviceLocator) {
        System.out.println("Configuring Application");

        // Guice
        System.out.println("Initializing the guice bridge");
        IdentitasGuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        final GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        final Injector injector = IdentitasServletConfig.INSTANCE;
        injector.getInstance(ServiceLocatorContainer.class).setServiceLocator(serviceLocator);
        guiceBridge.bridgeGuiceInjector(injector);

        packages("com.identitas.source");

//        // security filters
//        register(SecurityFilterBindingFeature.class);
//        register(IdentitasExceptionMapper.class);
//
//        final SharedConfig sharedConfig = injector.getInstance(SharedConfig.class);
//        if (!sharedConfig.getEnv().equals("production")) {
//            // Swagger
//            register(ApiListingResource.class);
//            register(SwaggerSerializers.class);
//            swaggerSetup();
//        }

//        register(CORSFilter.class);
//        register(IdentitaspplicationEventListener.class);
    }

//    private BeanConfig swaggerSetup() {
//        final BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.2");
//        beanConfig.setResourcePackage("com.identitas.resource"); // replace with your packages
//        beanConfig.setBasePath("/");
//        beanConfig.setDescription("Identitas API Documentation");
//        beanConfig.setTitle("Identitas API");
//        beanConfig.setScan(true);
//        return beanConfig;
//    }
}
