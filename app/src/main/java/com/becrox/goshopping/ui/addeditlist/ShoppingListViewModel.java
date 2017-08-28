package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import com.google.firebase.database.DatabaseReference;
import com.jakewharton.rxrelay2.PublishRelay;
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
  public final ObservableField<Boolean> showEmptyView = new ObservableField<>(true);

  /**
   * A reference to the database.
   */
  private DatabaseReference mRef;

  /**
   * A shoppingListCreateSubject where anyone can subscribe to in order to listen when
   * the {@link AddListDialog} is requested to show.
   */
  public final PublishRelay<Object> showAddListDialogSubject = PublishRelay.create();

  @Inject public ShoppingListViewModel(DatabaseReference ref) {
    mRef = ref;
  }

  /**
   * Shows the empty view. This view displays a text telling
   * the user that he has no shopping list.
   */
  public void showEmptyView() {
    showEmptyView.set(true);
  }

  /**
   * Hides the empty view.
   */
  public void hideEmptyView() {
    showEmptyView.set(false);
  }

  /**
   * Notifies the {@link #showAddListDialogSubject} subscribers in order to
   * show the {@link AddListDialog}.
   */
  public void showAddListDialog() {
    Observable.just(true).subscribe(showAddListDialogSubject);
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
