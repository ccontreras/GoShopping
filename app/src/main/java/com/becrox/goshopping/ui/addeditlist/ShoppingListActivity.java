package com.becrox.goshopping.ui.addeditlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.becrox.goshopping.R;
import com.becrox.goshopping.databinding.ActivityAddListBinding;
import com.becrox.goshopping.dto.ShoppingList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
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

  private FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder> mAdapter;

  @Inject DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
  @Inject DatabaseReference mRef;
  @Inject ShoppingListViewModel mViewModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);

    ActivityAddListBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_add_list);
    setSupportActionBar(binding.toolbar);

    mAdapter = new FirebaseRecyclerAdapter<ShoppingList, ShoppingListHolder>(ShoppingList.class,
        R.layout.shopping_list_item, ShoppingListHolder.class, mRef) {
      @Override protected void populateViewHolder(ShoppingListHolder viewHolder, ShoppingList model,
          int position) {
        viewHolder.bind(model);
      }
    };

    RecyclerView shoppingList = ((RecyclerView) findViewById(R.id.shoppingList));
    LinearLayoutManager lm = new LinearLayoutManager(this);
    shoppingList.setLayoutManager(lm);
    shoppingList.setAdapter(mAdapter);

    // Add a listener when data is first loaded and check if some items
    // were returned. If no items returned, then notify the view model to
    // show the empty view.
    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        if (!dataSnapshot.hasChildren()) {
          mViewModel.showEmptyView();
        } else {
          mViewModel.hideEmptyView();
        }
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        // no action
      }
    });

    // Add an OnClickListener in order to show the create dialog.
    binding.fab.setOnClickListener(view -> AddListDialog.newInstance()
        .show(getSupportFragmentManager(), "create_list_dialog"));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mAdapter.cleanup();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.shopping_list_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_remove_all:
        mViewModel.removeAll().subscribe(success -> {
          if (success) {
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
          }
        });
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return mFragmentDispatchingAndroidInjector;
  }

  /**
   * This is the ViewHolder to bind a shopping list item.
   */
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
