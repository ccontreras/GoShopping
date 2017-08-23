package com.becrox.goshopping.injection.module;

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
}
