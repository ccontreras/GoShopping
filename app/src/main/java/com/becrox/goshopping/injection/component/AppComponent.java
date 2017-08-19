package com.becrox.goshopping.injection.component;

import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Cesar on 18/8/17.
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(GoShoppingApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder app(GoShoppingApp app);

        AppComponent build();
    }
}
