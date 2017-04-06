package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.qiyao.bysj.baselibrary.component.OnItemClickListener;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCitiesItemViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapProvinceItemViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapDownloadViewModel extends ACollectionViewModel<OfflineMapProvince> implements OfflineMapManager.OfflineMapDownloadListener, OnItemClickListener {
    private OfflineMapManager offlineMapManager;
    private HashMap<String, OfflineMapCitiesItemViewModel> provinceCities = new HashMap<>();

    public OfflineMapDownloadViewModel(Fragment fragment) {
        super(fragment, false, false);
        //构造OfflineMapManager对象
        offlineMapManager = new OfflineMapManager(getFragment().getActivity(), this);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<OfflineMapProvince>> getData(RefreshMode mode) {
                return Observable.just(offlineMapManager.getOfflineMapProvinceList());
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        if (item.getItemViewType().equals(OfflineMapCitiesItemViewModel.TYPE_CITIES)) {
            return new ViewBindingRes(R.layout.view_offline_map_city_list, BR.viewModel);
        } else if (item.getItemViewType().equals(OfflineMapProvinceItemViewModel.TYPE_PROVINCE)) {
            return new ViewBindingRes(R.layout.item_offline_map_province, BR.viewModel);
        } else {
            return null;
        }
    }

    @Override
    protected IItemViewModel newItemViewModel(OfflineMapProvince item) {
        OfflineMapProvinceItemViewModel offlineMapProvinceItemViewModel = new OfflineMapProvinceItemViewModel(getFragment().getActivity(), item);
        offlineMapProvinceItemViewModel.setOnProvinceClickListener(this);
        return offlineMapProvinceItemViewModel;
    }


    @Override
    public void onDownload(int i, int i1, String s) {
        Observable.from(provinceCities.entrySet())
                .map(Map.Entry::getValue)
                .subscribe(OfflineMapCitiesItemViewModel::notifyDataSetChanged);
    }

    @Override
    public void onCheckUpdate(boolean b, String s) {

    }

    @Override
    public void onRemove(boolean b, String s, String s1) {

    }

    @Override
    public void onItemClick(IItemViewModel itemViewModel) {
        if (itemViewModel.getItemViewType().equals(OfflineMapProvinceItemViewModel.TYPE_PROVINCE)) {
            OfflineMapProvinceItemViewModel provinceItemViewModel = (OfflineMapProvinceItemViewModel) itemViewModel;
            onProvinceClick(provinceItemViewModel);
        }
    }

    private void onProvinceClick(OfflineMapProvinceItemViewModel provinceItemViewModel) {
        if (provinceItemViewModel.isFold.get()) {
            foldProvince(provinceItemViewModel);
        } else {
            unfoldProvince(provinceItemViewModel);
        }
    }

    private void foldProvince(OfflineMapProvinceItemViewModel provinceItemViewModel) {
        String provinceName = provinceItemViewModel.getProvinceName();
        Observable.just(provinceName)
                .map(name -> provinceCities.get(name))
                .subscribe(this::removeItem);
    }

    private void unfoldProvince(OfflineMapProvinceItemViewModel provinceItemViewModel) {
        int position = indexOf(provinceItemViewModel);
        String provinceName = provinceItemViewModel.getProvinceName();
        OfflineMapCitiesItemViewModel citiesItemViewModel = provinceCities.get(provinceName);
        if (citiesItemViewModel == null) {
            List<OfflineMapCity> cities = provinceItemViewModel.getCities();
            citiesItemViewModel = new OfflineMapCitiesItemViewModel(getFragment().getActivity(), cities, offlineMapManager);
            provinceCities.put(provinceName, citiesItemViewModel);
        }
        addItem(position + 1, citiesItemViewModel);
    }
}
