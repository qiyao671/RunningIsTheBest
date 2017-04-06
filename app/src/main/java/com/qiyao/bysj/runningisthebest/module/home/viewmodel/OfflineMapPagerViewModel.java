package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;

import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/6 22:06.
 * 类描述：
 */

public class OfflineMapPagerViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private OnItemBind itemView;
    private List<IViewModel> items;
    private BindingViewPagerAdapter.PageTitles<IViewModel> pageTitles = new BindingViewPagerAdapter.PageTitles<IViewModel>() {
        @Override
        public CharSequence getPageTitle(int position, IViewModel item) {
            if (position == 0) {
                return fragment.getString(R.string.city_list);
            } else {
                return fragment.getString(R.string.download_manage);
            }
        }
    };

    public OfflineMapPagerViewModel(Fragment fragment) {
        this.fragment = fragment;
        itemView = (itemBinding, position, item) -> {
            if (position == 0) {
                itemBinding.set(BR.viewModel, R.layout.view_offline_map_all);
            } else if (position == 1) {
                itemBinding.set(BR.viewModel, R.layout.view_offline_map_manager);
            }
        };
        OfflineMapViewModel offlineMapViewModel = new OfflineMapViewModel(fragment.getActivity());
        items = Arrays.asList(offlineMapViewModel, offlineMapViewModel);
    }

    @Bindable
    public OnItemBind getItemView() {
        return itemView;
    }

    @Bindable
    public List<IViewModel> getItems() {
        return items;
    }

    @Bindable
    public BindingViewPagerAdapter.PageTitles<IViewModel> getPageTitles() {
        return pageTitles;
    }
}
