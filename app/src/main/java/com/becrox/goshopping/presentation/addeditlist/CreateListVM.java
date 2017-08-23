package com.becrox.goshopping.presentation.addeditlist;

import android.databinding.ObservableField;
import com.becrox.goshopping.dto.ShoppingList;
import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class CreateListVM {

  private final ObservableField<String> title = new ObservableField<>();

  private DatabaseReference mRef;
  private PublishSubject<Boolean> mCreateShoppingListSubject = PublishSubject.create();

  @Inject public CreateListVM(DatabaseReference ref) {
    mRef = ref;
  }

  public PublishSubject<Boolean> getCreateShoppingListSubject() {
    return mCreateShoppingListSubject;
  }

  public void setTitle(String title) {
    this.title.set(title);
  }

  public String getTitle() {
    return title.get();
  }

  public void createShoppingList() {
    String title = this.title.get();
    if (title != null) {
      ShoppingList shoppingList = new ShoppingList.Builder().withTitle(title).build();
      mRef.push()
          .setValue(shoppingList)
          .addOnCompleteListener(
              task -> Observable.just(task.isSuccessful()).subscribe(mCreateShoppingListSubject));
    }
  }
}
