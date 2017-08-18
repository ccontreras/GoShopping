package com.becrox.goshopping;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author cconTreras
 */

public class ShoppingListTest {

  private ShoppingListManager mShoppingListManager;

  @Mock ShoppingList mShoppingList;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    mShoppingListManager = new ShoppingListManager(mShoppingList);
  }

  @Test public void testShoppingList_isInitialized() {
    assertThat(mShoppingListManager, is(notNullValue()));
  }

  @Test public void testList_isCreated_correctly() {
    List newList = new List.Builder().withTitle("Simple list").build();

    when(mShoppingList.createList(newList)).thenReturn(Observable.just(true));

    TestObserver<Boolean> testObserver = new TestObserver<>();
    mShoppingListManager.createNewList(newList).subscribe(testObserver);

    verify(mShoppingList).createList(newList);
    testObserver.assertSubscribed();
    testObserver.assertComplete();
    testObserver.assertResult(true);
  }

  @Test public void testList_shouldBeRemoved_correctly() {
    List newList = new List.Builder().withTitle("Simple list").build();
  }
}
