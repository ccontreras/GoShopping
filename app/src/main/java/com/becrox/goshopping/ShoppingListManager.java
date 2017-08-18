package com.becrox.goshopping;

import io.reactivex.Observable;

/**
 * @author cconTreras
 */

public class ShoppingListManager {
  private ShoppingList mShoppingList;

  public ShoppingListManager(ShoppingList shoppingList) {
    mShoppingList = shoppingList;
  }

  public Observable<Boolean> createNewList(List newList) {
    return mShoppingList.createList(newList);
  }
}
