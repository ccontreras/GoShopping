package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import com.becrox.goshopping.dto.ShoppingList;
import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javax.inject.Inject;

/**
 * This is the view-model for {@link CreateListDialog}.
 *
 * @author cconTreras
 */

public class CreateListViewModel {

  /**
   * Field that exposes the shopping's list title.
   */
  public final ObservableField<String> title = new ObservableField<>("");

  /**
   * Holds a reference to the database.
   */
  private DatabaseReference mRef;

  /**
   * This is a subject that anyone can subscribe to when a new
   * list is created.
   */
  public final Subject<Boolean> subject = PublishSubject.create();

  @Inject public CreateListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  public void createShoppingList() {
    String title = this.title.get();
    if (title != null) {
      ShoppingList shoppingList = new ShoppingList.Builder().withTitle(title).build();
      mRef.push()
          .setValue(shoppingList)
          .addOnCompleteListener(task -> Observable.just(task.isSuccessful()).subscribe(subject));
    }
  }
}
