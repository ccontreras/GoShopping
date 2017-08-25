package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class ShoppingListViewModel {

  public final ObservableField<Boolean> isEmpty = new ObservableField<>(false);

  private DatabaseReference mRef;

  @Inject ShoppingListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  public boolean isEmpty() {
    return isEmpty.get();
  }

  public void setEmpty(boolean empty) {
    isEmpty.set(empty);
  }

  public Observable<Boolean> removeAll() {
    return Observable.create(e -> mRef.removeValue().addOnCompleteListener(task -> {
      e.onNext(task.isSuccessful());
      e.onComplete();
    }));
  }
}
