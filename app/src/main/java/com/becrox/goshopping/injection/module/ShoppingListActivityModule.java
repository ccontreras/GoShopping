package com.becrox.goshopping.injection.module;

import com.becrox.goshopping.injection.scope.PerActivity;
import com.becrox.goshopping.util.Global;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;

/**
 * @author cconTreras
 */

@Module public class ShoppingListActivityModule {

  @Provides @PerActivity static DatabaseReference provideDatabaseReference() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    database.setPersistenceEnabled(true);
    return database.getReference(Global.SHOPPING_LIST_COLLECTION);
  }
}
