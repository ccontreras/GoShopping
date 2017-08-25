package com.becrox.goshopping.injection.module;

import android.content.Context;
import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.qualifier.AppContext;
import com.becrox.goshopping.injection.scope.PerActivity;
import com.becrox.goshopping.ui.addeditlist.ShoppingListActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import javax.inject.Singleton;

/**
 * @author Cesar on 18/8/17.
 */

@Module public abstract class AppModule {

  @Binds @Singleton @AppContext abstract Context bindContext(GoShoppingApp app);

  @PerActivity @ContributesAndroidInjector(modules = ShoppingListActivityModule.class)
  abstract ShoppingListActivity contributeAddListActivityInjector();
}
