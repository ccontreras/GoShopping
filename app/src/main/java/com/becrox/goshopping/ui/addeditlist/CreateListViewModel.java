package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import com.becrox.goshopping.dto.ShoppingList;
import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class CreateListViewModel {

  public final ObservableField<String> title = new ObservableField<>("");

  private DatabaseReference mRef;
  private PublishSubject<Boolean> mSubject = PublishSubject.create();

  @Inject public CreateListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  public Disposable onShoppingListCreate(Consumer<Boolean> consumer) {
    return mSubject.subscribe(consumer);
  }

  public void createShoppingList() {
    String title = this.title.get();
    if (title != null) {
      ShoppingList shoppingList = new ShoppingList.Builder().withTitle(title).build();
      mRef.push()
          .setValue(shoppingList)
          .addOnCompleteListener(task -> Observable.just(task.isSuccessful()).subscribe(mSubject));
    }
  }
}
