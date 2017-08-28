package com.becrox.goshopping.injection.module;

import com.becrox.goshopping.injection.scope.PerActivity;
import com.becrox.goshopping.injection.scope.PerFragment;
import com.becrox.goshopping.ui.addeditlist.AddListDialog;
import com.becrox.goshopping.util.Global;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * @author cconTreras
 */

@Module public abstract class ShoppingListActivityModule {

  @Provides @PerActivity static DatabaseReference provideDatabaseReference() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    return database.getReference().child(Global.SHOPPING_LIST_COLLECTION);
  }

  @PerFragment @ContributesAndroidInjector
  abstract AddListDialog contributeCreateListDialogInjector();
}
