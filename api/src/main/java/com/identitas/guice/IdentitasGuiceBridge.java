package com.identitas.guice;

/**
 * It is necessary to overide the guice bridge due to the guice scope context having an incorrect visibility
 * (IS LOCAL, needs to be NORMAL to allow injections in filters)
 */
public abstract class IdentitasGuiceBridge {

    private final static IdentitasGuiceBridgeImpl INSTANCE = new IdentitasGuiceBridgeImpl();

    public static IdentitasGuiceBridgeImpl getGuiceBridge() {
        return INSTANCE;
    }
}
