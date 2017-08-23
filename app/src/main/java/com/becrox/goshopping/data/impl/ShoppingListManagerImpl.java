package com.becrox.goshopping.data.impl;

import com.becrox.goshopping.data.IShoppingListManager;
import com.becrox.goshopping.dto.ShoppingList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The {@link IShoppingListManager} implementation class.
 *
 * @author cconTreras
 */

@Singleton public class ShoppingListManagerImpl implements IShoppingListManager {

  /* The name of the collection list in the database. */
  private static final String COLLECTION = "lists";

  /* A reference to the root database. */
  private DatabaseReference mDatabase;

  @Inject public ShoppingListManagerImpl(FirebaseDatabase db) {
    mDatabase = db.getReference().child(COLLECTION);
  }

  /**
   * {@inheritDoc}
   */
  @Override public Observable<Boolean> createList(ShoppingList newList) {
    return Observable.create(e -> {
      String id = mDatabase.push().getKey();
      newList.setId(id);
      mDatabase.child(COLLECTION).child(id).setValue(newList).addOnCompleteListener(task -> {
        e.onNext(task.isSuccessful());
        e.onComplete();
      });
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override public Observable<Boolean> deleteList(ShoppingList list) {
    return Observable.create(
        e -> mDatabase.child(list.getId()).removeValue().addOnCompleteListener(task -> {
          e.onNext(task.isSuccessful());
          e.onComplete();
        }));
  }

  /**
   * {@inheritDoc}
   */
  @Override public Observable<ListResult> findById(String id) {
    return Observable.create(e -> {
      ValueEventListener listener = new ValueEventListener() {
        @Override public void onDataChange(DataSnapshot dataSnapshot) {
          e.onNext(new ListResult(dataSnapshot.getValue(ShoppingList.class)));
        }

        @Override public void onCancelled(DatabaseError databaseError) {
          e.onError(databaseError.toException());
        }
      };
      mDatabase.child(COLLECTION).child(id).addValueEventListener(listener);
      e.setCancellable(() -> mDatabase.removeEventListener(listener));
    });
  }
}
