package com.becrox.goshopping.domain;

import com.becrox.goshopping.domain.dto.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author cconTreras
 */

public class ShoppingListServiceTest {

    private ShoppingListService mShoppingListService;

    @Mock
    IShoppingList mShoppingList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mShoppingListService = new ShoppingListService(mShoppingList);
    }

    /**
     * Test that {@link #mShoppingListService} has been initialized correctly.
     */
    @Test
    public void should_shoppingListManager_beInitialized() {
        assertThat(mShoppingListService, is(notNullValue()));
    }

    /**
     * Test that a new list is created correctly.
     */
    @Test
    public void should_createList_seamlessly() {
        List newList = createList();

        when(mShoppingList.createList(newList)).thenReturn(Observable.just(true));

        TestObserver<Boolean> testObserver = new TestObserver<>();
        mShoppingListService.createNewList(newList).subscribe(testObserver);

        verify(mShoppingList).createList(newList);
        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertResult(true);
    }

    @Test
    public void should_createList_fail() {
        List newList = createList();

        when(mShoppingList.createList(newList)).thenReturn(Observable.just(false));

        TestObserver<Boolean> testObserver = new TestObserver<>();
        mShoppingListService.createNewList(newList).subscribe(testObserver);

        verify(mShoppingList).createList(newList);
        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertResult(false);
    }

    /**
     * Test that a list is removed correctly.
     */
    @Test
    public void should_listBeDeleted_seamlessly() {
        List list = createList();

        when(mShoppingList.deleteList(list)).thenReturn(Observable.just(true));

        TestObserver<Boolean> testObserver = new TestObserver<>();
        mShoppingListService.deleteList(list).subscribe(testObserver);

        verify(mShoppingList).deleteList(list);
        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertResult(true);
    }

    @Test
    public void should_deleteList_fail() {
        List list = createList();

        when(mShoppingList.deleteList(list)).thenReturn(Observable.just(false));

        TestObserver<Boolean> testObserver = new TestObserver<>();
        mShoppingListService.deleteList(list).subscribe(testObserver);

        verify(mShoppingList).deleteList(list);
        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertResult(false);
    }

    @Test
    public void should_findList_withId() {
        String id = "abc123";
        String title = "hello world!";
        List list = new List.Builder().withId(id).withTitle(title).build();
        IShoppingList.ListResult expected = new IShoppingList.ListResult(list);

        when(mShoppingList.findById(id)).thenReturn(Observable.just(expected));

        TestObserver<IShoppingList.ListResult> testObserver = new TestObserver<>();
        mShoppingListService.findById(id).subscribe(testObserver);

        verify(mShoppingList).findById(id);
        testObserver.assertSubscribed();
        testObserver.assertResult(expected);
    }

    private List createList() {
        return new List.Builder().withTitle("Simple list").build();
    }
}
