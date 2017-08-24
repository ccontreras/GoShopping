package com.becrox.goshopping.ui.addeditlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.becrox.goshopping.databinding.CreateListDialogBinding;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class CreateListDialog extends DialogFragment {

  @Inject CreateListViewModel mViewModel;

  private CreateListDialogBinding mBinding;
  private Disposable mDisposable;

  @Override public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(STYLE_NO_TITLE, 0);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding =
        CreateListDialogBinding.inflate(LayoutInflater.from(getActivity()), container, false);
    mBinding.setVm(mViewModel);
    return mBinding.getRoot();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mDisposable = mViewModel.onShoppingListCreate(isSuccessful -> {
      dismiss();
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mDisposable != null) mDisposable.dispose();
  }
}
