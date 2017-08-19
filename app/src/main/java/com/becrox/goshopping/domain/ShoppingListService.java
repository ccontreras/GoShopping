package com.becrox.goshopping.domain;

import com.becrox.goshopping.domain.dto.List;

import io.reactivex.Observable;

/**
 * @author cconTreras
 */

public class ShoppingListService {
    private IShoppingList mShoppingList;

    public ShoppingListService(IShoppingList shoppingList) {
        mShoppingList = shoppingList;
    }

    public Observable<Boolean> createNewList(List newList) {
        return mShoppingList.createList(newList);
    }

    public Observable<Boolean> deleteList(List list) {
        return mShoppingList.deleteList(list);
    }

    public Observable<IShoppingList.ListResult> findById(String id) {
        return mShoppingList.findById(id);
    }
}
