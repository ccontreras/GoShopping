package com.becrox.goshopping.presentation.addeditlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.google.firebase.database.FirebaseDatabase;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

/**
 * This activity shows a list of created shopping.
 *
 * @author cconTreras
 */
public class AddListActivity extends AppCompatActivity {

  @Inject FirebaseDatabase mFirebaseDatabase;

  FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder> mFirebaseRecyclerAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DatabaseReference ref = mFirebaseDatabase.getReference().child("shopping-lists");

    mFirebaseRecyclerAdapter =
        new FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder>(ShoppingList.class,
            R.layout.shopping_list_item, ShoppingListHolder.class, ref) {
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
