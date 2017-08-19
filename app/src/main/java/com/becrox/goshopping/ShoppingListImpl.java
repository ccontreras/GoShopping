package com.becrox.goshopping;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;

/**
 * The {@link ShoppingList} implementation class.
 *
 * @author cconTreras
 */

public class ShoppingListImpl implements ShoppingList {
    /* The name of the collection list in the database. */
    private static final String COLLECTION = "lists";
    /* A reference to the root database. */
    private DatabaseReference mDatabase;

    public ShoppingListImpl(DatabaseReference reference) {
        mDatabase = reference;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<Boolean> createList(List newList) {
        return Observable.create(e -> {
            String id = mDatabase.child(COLLECTION).push().getKey();
            newList.setId(id);
            mDatabase.child(COLLECTION)
                    .child(id)
                    .setValue(newList)
                    .addOnCompleteListener(task -> {
                        e.onNext(task.isSuccessful());
                        e.onComplete();
                    });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<Boolean> deleteList(List list) {
        return Observable.create(e -> {
            mDatabase.child(COLLECTION)
                    .child(list.getId())
                    .removeValue()
                    .addOnCompleteListener(task -> {
                        e.onNext(task.isSuccessful());
                        e.onComplete();
                    });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observable<ListResult> findById(String id) {
        return Observable.create(e -> {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    e.onNext(new ListResult(dataSnapshot.getValue(List.class)));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(databaseError.toException());
                }
            };
            mDatabase.child(COLLECTION).child(id).addValueEventListener(listener);
            e.setCancellable(() -> mDatabase.removeEventListener(listener));
        });
    }
}
