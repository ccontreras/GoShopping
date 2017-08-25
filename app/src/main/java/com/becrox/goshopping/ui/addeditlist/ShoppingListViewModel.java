package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import com.google.firebase.database.DatabaseReference;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This is the view-model for {@link ShoppingListActivity}.
 *
 * @author cconTreras
 */

public class ShoppingListViewModel {

  /**
   * Flag that indicates if the empty view should appear.
   */
  public final ObservableField<Boolean> shouldShowEmptyView = new ObservableField<>(false);

  /**
   * A reference to the database.
   */
  private DatabaseReference mRef;

  @Inject ShoppingListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  public boolean shouldShowEmptyView() {
    return shouldShowEmptyView.get();
  }

  /**
   * Shows the empty view. This view displays a text telling
   * the user that he has no shopping list.
   */
  public void showEmptyView() {
    shouldShowEmptyView.set(true);
  }

  /**
   * Hides the empty view.
   */
  public void hideEmptyView() {
    shouldShowEmptyView.set(false);
  }

  /**
   * Removes all the existing lists from database.
   *
   * @return an {@link Observable} with the result.
   */
  public Observable<Boolean> removeAll() {
    return Observable.create(e -> mRef.removeValue().addOnCompleteListener(task -> {
      e.onNext(task.isSuccessful());
      e.onComplete();
    }));
  }
}
