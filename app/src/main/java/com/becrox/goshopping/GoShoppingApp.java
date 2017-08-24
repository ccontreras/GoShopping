package com.becrox.goshopping;

import android.app.Activity;
import android.app.Application;
import com.becrox.goshopping.injection.component.DaggerAppComponent;
import com.google.firebase.database.FirebaseDatabase;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

/**
 * @author Cesar on 18/8/17.
 */

public class GoShoppingApp extends Application implements HasActivityInjector {
  @Inject DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

  @Override public void onCreate() {
    super.onCreate();

    FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    DaggerAppComponent.builder().app(this).build().inject(this);
  }

  @Override public AndroidInjector<Activity> activityInjector() {
    return mActivityDispatchingAndroidInjector;
  }
}
