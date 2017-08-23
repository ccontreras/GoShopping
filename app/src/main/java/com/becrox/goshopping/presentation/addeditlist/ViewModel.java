package com.becrox.goshopping.presentation.addeditlist;

import com.becrox.goshopping.data.IShoppingListManager;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class ViewModel {

  @Inject IShoppingListManager mShoppingListManager;

  @Inject public ViewModel() {
  }
}
