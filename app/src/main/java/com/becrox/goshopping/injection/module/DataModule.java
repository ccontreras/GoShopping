package com.becrox.goshopping.injection.module;

import com.becrox.goshopping.data.IShoppingListManager;
import com.becrox.goshopping.data.impl.ShoppingListManagerImpl;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author cconTreras
 */

@Module public class DataModule {

  @Provides @Singleton public FirebaseDatabase provideFirebaseDatabase() {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    db.setPersistenceEnabled(true);
    return db;
  }

  @Provides @Singleton public IShoppingListManager provideShoppingList(FirebaseDatabase db) {
    return new ShoppingListManagerImpl(db);
  }
}
