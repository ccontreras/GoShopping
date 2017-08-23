package com.becrox.goshopping.presentation.addeditlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.becrox.goshopping.databinding.CreateListDialogBinding;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

/**
 * @author cconTreras
 */

public class CreateListDialog extends DialogFragment {

  @Inject CreateListVM mVM;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidSupportInjection.inject(this);
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    CreateListDialogBinding binding =
        CreateListDialogBinding.inflate(LayoutInflater.from(getActivity()), container, false);
    binding.setVm(mVM);
    return binding.getRoot();
  }
}
