package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;

import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/18 21:27.
 * 类描述：
 */

public class WriteMomentViewModel implements IViewModel, View.OnClickListener {
    private Fragment fragment;

    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<OnItemBind> imageItemView = new ObservableField<>();
    public ObservableArrayList<String> images = new ObservableArrayList<>();
    public MergeObservableList<String> imagesWithAddBtn = new MergeObservableList<String>()
            .insertList(images)
            .insertItem(null);

    public WriteMomentViewModel(Fragment fragment) {
        this.fragment = fragment;
        imageItemView.set((itemBinding, position, item) -> {
            if (position == images.size()) {
                itemBinding.set(BR.viewModel, R.layout.item_add_image);
            } else {
                itemBinding.set(BR.imageUrl, R.layout.item_image);
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        fragment.startActivityForResult(intent, 2);
    }
}
