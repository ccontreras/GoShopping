package com.becrox.goshopping;

import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;

/**
 * @author cconTreras
 */

public class ShoppingListImpl implements ShoppingList {
  public static final String REF = "shopping_list";

  private DatabaseReference mReference;

  public ShoppingListImpl(DatabaseReference reference) {
    mReference = reference;
  }

  @Override public Observable<Boolean> createList(List newList) {
    return Observable.create(e -> mReference.setValue(newList).addOnCompleteListener(task -> {
      e.onNext(task.isSuccessful());
      e.onComplete();
    }));
  }

  @Override public Observable<Boolean> removeList(List list) {
    return null;
  }
}
