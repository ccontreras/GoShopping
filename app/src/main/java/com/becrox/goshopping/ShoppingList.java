package com.becrox.goshopping;

import io.reactivex.Observable;

/**
 * @author cconTreras
 */

interface ShoppingList {
  String ROOT = "lists";

  Observable<Boolean> createList(List newList);

  Observable<Boolean> removeList(List list);
}
