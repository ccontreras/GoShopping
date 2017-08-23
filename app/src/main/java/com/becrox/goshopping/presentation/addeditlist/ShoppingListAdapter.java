package com.becrox.goshopping.presentation.addeditlist;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.becrox.goshopping.databinding.ShoppingListItemBinding;
import com.becrox.goshopping.dto.ShoppingList;
import java.util.List;

/**
 * @author cconTreras
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.VH> {

  private List<ShoppingList> mShoppingLists;
  private Context mContext;

  public ShoppingListAdapter(Context context, List<ShoppingList> shoppingLists) {
    mShoppingLists = shoppingLists;
    mContext = context;
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    ShoppingListItemBinding binding =
        ShoppingListItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
    binding.setVm(new ViewModel(mContext));
    VH vh = new VH(binding.getRoot(), binding);
    return vh;
  }

  @Override public int getItemCount() {
    return mShoppingLists != null ? mShoppingLists.size() : 0;
  }

  @Override public void onBindViewHolder(VH holder, int position) {
    ShoppingList shoppingList = mShoppingLists.get(position);
    holder.getBinding().getVm().setShoppingList(shoppingList);
  }

  /**
   * This is the view holder for the adapter.
   *
   * @see RecyclerView.ViewHolder
   */
  static class VH extends RecyclerView.ViewHolder {
    private final ShoppingListItemBinding mBinding;

    public VH(View itemView, ShoppingListItemBinding binding) {
      super(itemView);
      mBinding = binding;
    }

    public ShoppingListItemBinding getBinding() {
      return mBinding;
    }
  }

  /**
   * This is the view-model for this adapter.
   */
  public static class ViewModel {

    Context mContext;
    ObservableField<ShoppingList> mShoppingList;

    public ViewModel(Context context) {
      mContext = context;
    }

    public void setShoppingList(ShoppingList shoppingList) {
      mShoppingList.set(shoppingList);
    }

    /**
     * Navigates onto the detail screen.
     */
    public void goToDetailScreen() {
      // TODO go to shopping list detail screen.
    }
  }
}
