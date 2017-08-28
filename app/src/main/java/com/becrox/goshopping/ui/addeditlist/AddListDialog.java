package com.becrox.goshopping.ui.addeditlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.becrox.goshopping.R;
import com.becrox.goshopping.databinding.AddListDialogBinding;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class AddListDialog extends RxDialogFragment {

  @Inject AddListViewModel mViewModel;

  private AddListDialogBinding mBinding;

  public AddListDialog() {
  }

  public static AddListDialog newInstance() {
    return new AddListDialog();
  }

  @Override public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.add_list_dialog,
        container, false);
    mBinding.setVm(mViewModel);
    return mBinding.getRoot();
  }

  @Override public void onResume() {
    super.onResume();
    mViewModel.shoppingListCreateSubject.compose(bindToLifecycle()).subscribe(result -> {
      if (result.success) {
        dismiss();
      } else {
        Toast.makeText(getActivity(), result.message, Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }
}
