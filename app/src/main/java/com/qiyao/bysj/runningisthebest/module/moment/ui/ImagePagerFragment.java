package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentImagePagerBinding;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.ImagePagerViewModel;

import java.util.ArrayList;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/22 14:08.
 * 类描述：
 */

public class ImagePagerFragment extends ADataBindingFragment {
    private static final String KEY_IMAGES_URL = "IMAGES_URL";
    private static final String KEY_CURRENT_POSITION = "CURRENT_POSITION";
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new ImagePagerViewModel(arguments.getStringArrayList(KEY_IMAGES_URL));
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_image_pager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int current = getArguments().getInt(KEY_CURRENT_POSITION, 0);
        ((FragmentImagePagerBinding)getBinding()).viewPager.setCurrentItem(current);
    }

    public static void launch(Context context, ArrayList<String> imagesUrl, int currentPosition) {
        Bundle args = new Bundle();
        args.putStringArrayList(KEY_IMAGES_URL, imagesUrl);
        args.putInt(KEY_CURRENT_POSITION, currentPosition);
        FragmentContainerActivity.launch(context, ImagePagerFragment.class, args);
    }
}
