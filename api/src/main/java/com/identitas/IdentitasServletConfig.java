package com.identitas;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.annotation.WebListener;

/**
 * Created by toshikijahja on 3/23/19.
 */
@WebListener
public class IdentitasServletConfig extends GuiceServletContextListener {
    public static volatile Injector INSTANCE = null;

    @Override
    protected Injector getInjector() {
        if (INSTANCE == null) {
            synchronized (this) {
                if (INSTANCE == null) {
                    INSTANCE = Guice.createInjector(new IdentitasJerseyModule());
                }
            }
        }
        return INSTANCE;
    }
}
