package com.identitas.guice;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.jvnet.hk2.guice.bridge.internal.GuiceIntoHK2BridgeImpl;

public class IdentitasGuiceBridgeImpl {

    /* (non-Javadoc)
     * @see org.jvnet.hk2.guice.bridge.api.GuiceBridge#initializeGuiceBridge(org.glassfish.hk2.api.ServiceLocator)
     */
    public void initializeGuiceBridge(final ServiceLocator locator) {
        boolean addService = true;
        if (locator.getBestDescriptor(BuilderHelper.createContractFilter(GuiceIntoHK2Bridge.class.getName())) != null) {
            addService = false;
        }

        boolean addContext = true;
        if (locator.getBestDescriptor(BuilderHelper.createContractFilter(IdentitasGuiceScopeContext.class.getName())) != null) {
            addContext = false;
        }

        if (!addService && !addContext) {
            return;
        }


        DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);
        if (dcs == null) {
            throw new IllegalStateException("This service locator has no DynamicConfiugurationService: " + locator);
        }

        DynamicConfiguration config = dcs.createDynamicConfiguration();

        if (addContext) {
            config.addActiveDescriptor(IdentitasGuiceScopeContext.class);
        }

        if (addService) {
            config.addActiveDescriptor(GuiceIntoHK2BridgeImpl.class);
        }

        config.commit();

    }

}
