package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

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
        setDisplayHomeAsUpEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_FULLSCREEN | localLayoutParams.flags);
        }
        //隐藏toolbar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static void launch(Context context, ArrayList<String> imagesUrl, int currentPosition) {
        Bundle args = new Bundle();
        args.putStringArrayList(KEY_IMAGES_URL, imagesUrl);
        args.putInt(KEY_CURRENT_POSITION, currentPosition);
        FragmentContainerActivity.launch(context, ImagePagerFragment.class, args);
    }
}
