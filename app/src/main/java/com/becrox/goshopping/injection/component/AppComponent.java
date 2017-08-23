package com.becrox.goshopping.injection.component;

import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.module.AppModule;
import com.becrox.goshopping.injection.module.DataModule;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author Cesar on 18/8/17.
 */

@Component(modules = { AppModule.class, DataModule.class }) @Singleton
public interface AppComponent {

  void inject(GoShoppingApp app);

  @Component.Builder interface Builder {

    @BindsInstance Builder app(GoShoppingApp app);

    AppComponent build();
  }
}
