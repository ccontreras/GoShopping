package com.becrox.goshopping.injection.module;

import android.content.Context;
import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.scope.AppContext;
import com.becrox.goshopping.injection.scope.PerActivity;
import com.becrox.goshopping.presentation.addeditlist.AddListActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Cesar on 18/8/17.
 */

@Module public abstract class AppModule {

  @Binds @AppContext abstract Context bindContext(GoShoppingApp app);

  @PerActivity @ContributesAndroidInjector
  abstract AddListActivity contributeYourActivityInjector();
}
