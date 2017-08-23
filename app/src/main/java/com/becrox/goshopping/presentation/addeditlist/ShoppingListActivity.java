package com.becrox.goshopping.presentation.addeditlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.becrox.goshopping.R;
import com.becrox.goshopping.dto.ShoppingList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

/**
 * This activity shows a list of created shopping.
 *
 * @author cconTreras
 */
public class ShoppingListActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder> mFirebaseRecyclerAdapter;

  @Inject DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
  @Inject DatabaseReference mRef;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mFirebaseRecyclerAdapter =
        new FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder>(ShoppingList.class,
            R.layout.shopping_list_item, ShoppingListHolder.class, mRef) {
          @Override
          protected void populateViewHolder(ShoppingListHolder viewHolder, ShoppingList model,
              int position) {
            viewHolder.bind(model);
          }
        };

    RecyclerView shoppingList = ((RecyclerView) findViewById(R.id.shoppingList));
    LinearLayoutManager lm = new LinearLayoutManager(this);
    shoppingList.setLayoutManager(lm);
    shoppingList.setAdapter(mFirebaseRecyclerAdapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mFirebaseRecyclerAdapter.cleanup();
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return mFragmentDispatchingAndroidInjector;
  }

  static class ShoppingListHolder extends RecyclerView.ViewHolder {
    TextView mShoppingListTitleText;

    public ShoppingListHolder(View itemView) {
      super(itemView);
      mShoppingListTitleText = ((TextView) itemView.findViewById(R.id.shoppingListTitle));
    }

    void bind(ShoppingList shoppingList) {
      mShoppingListTitleText.setText(shoppingList.getTitle());
    }
  }
}
