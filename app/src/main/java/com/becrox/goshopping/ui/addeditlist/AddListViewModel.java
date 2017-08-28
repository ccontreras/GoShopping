package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import com.becrox.goshopping.dto.ShoppingList;
import com.google.firebase.database.DatabaseReference;
import com.jakewharton.rxrelay2.PublishRelay;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This is the view-model for {@link AddListDialog}.
 *
 * @author cconTreras
 */

public class AddListViewModel {

  /**
   * Field that exposes the shopping's list title.
   */
  public final ObservableField<String> title = new ObservableField<>("");

  /**
   * Holds a reference to the database.
   */
  private DatabaseReference mRef;

  /**
   * This is a shoppingListCreateSubject that anyone can subscribe to when a new
   * list is created.
   */
  public final PublishRelay<Result> shoppingListCreateSubject = PublishRelay.create();

  @Inject public AddListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  public void createShoppingList() {
    String title = this.title.get();
    if (title != null) {
      ShoppingList shoppingList = new ShoppingList.Builder().withTitle(title).build();
      mRef.push()
          .setValue(shoppingList)
          .addOnCompleteListener(task -> Observable.just(task.isSuccessful() ? Result.success()
              : Result.error(task.getException().getMessage()))
              .subscribe(shoppingListCreateSubject));
    }
  }

  public static class Result {
    public final boolean success;
    @Nullable public final String message;

    private Result(boolean success, @Nullable String message) {
      this.success = success;
      this.message = message;
    }

    public static Result success() {
      return new Result(true, null);
    }

    public static Result error(String error) {
      return new Result(false, error);
    }
  }
}
