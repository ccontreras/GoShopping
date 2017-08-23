package com.becrox.goshopping.data;

import com.becrox.goshopping.dto.ShoppingList;

import io.reactivex.Observable;

/**
 * @author cconTreras
 */

public interface IShoppingListManager {

  /**
   * Creates a new list.
   *
   * @param newList the new list to be created.
   * @return an {@link Observable} with the result of the transaction.
   */
  Observable<Boolean> createList(ShoppingList newList);

  /**
   * Deletes a list.
   *
   * @param list the list to be deleted.
   * @return an {@link Observable} with the transaction result.
   */
  Observable<Boolean> deleteList(ShoppingList list);

  /**
   * Retrieves a list with the specified <code>id</code> if exists.
   *
   * @param id the id of the list to find.
   * @return the found list.
   */
  Observable<ListResult> findById(String id);

  class ListResult {
    public final ShoppingList mList;

    public ListResult(ShoppingList list) {
      mList = list;
    }
  }
}
