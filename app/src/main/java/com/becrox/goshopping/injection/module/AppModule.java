package com.becrox.goshopping.injection.module;

import android.content.Context;

import com.becrox.goshopping.GoShoppingApp;
import com.becrox.goshopping.injection.scope.AppContext;
import com.becrox.goshopping.injection.scope.PerActivity;
import com.becrox.goshopping.presentation.addeditlist.AddListActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Cesar on 18/8/17.
 */

@Module
public abstract class AppModule {

    @Binds
    @AppContext
    abstract Context bindContext(GoShoppingApp app);

    @Provides
    @Singleton
    static DatabaseReference provideDatabaseReference() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        return db.getReference();
    }

    @PerActivity
    @ContributesAndroidInjector
    abstract AddListActivity contributeYourActivityInjector();
}
