package com.becrox.goshopping.ui.addeditlist;

import android.databinding.ObservableField;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class ShoppingListViewModel {

  public final ObservableField<Boolean> isEmpty = new ObservableField<>(false);

  @Inject ShoppingListViewModel() {
  }

  public boolean isEmpty() {
    return isEmpty.get();
  }

  public void setEmpty(boolean isEmpty) {
    this.isEmpty.set(isEmpty);
  }
}
