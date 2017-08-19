package com.becrox.goshopping.domain;

import com.becrox.goshopping.domain.dto.List;

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

    public Observable<Boolean> deleteList(List list) {
        return mShoppingList.deleteList(list);
    }

    public Observable<ShoppingList.ListResult> findById(String id) {
        return mShoppingList.findById(id);
    }
}
