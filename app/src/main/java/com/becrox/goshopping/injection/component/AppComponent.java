package com.becrox.goshopping.injection.component;

import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.module.AppModule;
import com.becrox.goshopping.presentation.addeditlist.ViewModel;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author Cesar on 18/8/17.
 */

@Component(modules = AppModule.class) @Singleton public interface AppComponent {

  void inject(GoShoppingApp app);

  void inject(ViewModel vm);

  @Component.Builder interface Builder {

    @BindsInstance Builder app(GoShoppingApp app);

    AppComponent build();
  }
}
