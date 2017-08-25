package com.becrox.goshopping.ui.addeditlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.becrox.goshopping.R;
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

  public CreateListDialog() {
  }

  public static CreateListDialog newInstance() {
    return new CreateListDialog();
  }

  @Override public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding =
        DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.create_list_dialog,
            container, false);
    mBinding.setVm(mViewModel);
    return mBinding.getRoot();
  }

  @Override public void onResume() {
    super.onResume();
    mDisposable = mViewModel.subject.subscribe(isSuccessful -> {
      if (isSuccessful) {
        dismiss();
      }
    });
  }

  @Override public void onPause() {
    super.onPause();
    if (mDisposable != null) {
      mDisposable.dispose();
      mDisposable = null;
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }
}
