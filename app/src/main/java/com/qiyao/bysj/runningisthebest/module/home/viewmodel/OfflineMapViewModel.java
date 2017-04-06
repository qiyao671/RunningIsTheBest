package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.component.OnItemClickListener;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCitiesItemViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCityItemViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapProvinceItemViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import rx.Observable;

/**
 * Created by qiyao on 2017/4/6.
 */

public class OfflineMapViewModel implements IViewModel, OnItemClickListener, OfflineMapManager.OfflineMapDownloadListener {
    private Fragment fragment;
    private OfflineMapManager offlineMapManager;
    private HashMap<String, OfflineMapCitiesItemViewModel> provinceCities = new HashMap<>();

    private ObservableArrayList<IItemViewModel> cityItems = new ObservableArrayList<>();
    private ObservableArrayList<IItemViewModel> provinceItems = new ObservableArrayList<>();
    private OnItemBind<IItemViewModel> provinceItemView;
    private ItemBinding<IItemViewModel> cityItemView;

    public OfflineMapViewModel(Fragment fragment) {
        this.fragment = fragment;
        offlineMapManager = new OfflineMapManager(fragment.getActivity(), this);
        initItems();
        initItemView();
    }

    private void initItems() {
        initProvinceItems();
        initCityItems();
    }

    private void initCityItems() {
        List<OfflineMapCity> list = offlineMapManager.getDownloadingCityList();
        list.addAll(offlineMapManager.getDownloadOfflineMapCityList());
        Observable.concat(Observable.from(offlineMapManager.getDownloadingCityList()),
                Observable.from(offlineMapManager.getDownloadOfflineMapCityList()))
                .map(this::createCityItemViewModel)
                .subscribe(cityItems::add, this::onError);

    }

    private void initProvinceItems() {
        Observable.from(offlineMapManager.getOfflineMapProvinceList())
                .map(this::createProvinceItemViewModel)
                .subscribe(provinceItems::add, this::onError);
    }

    private void initItemView() {
        initProvinceItemView();
        initCityItemView();
    }

    private void initProvinceItemView() {
        provinceItemView = (itemBinding, position, item) -> {
            if (item.getItemViewType().equals(OfflineMapCitiesItemViewModel.TYPE_CITIES)) {
                itemBinding.set(BR.viewModel, R.layout.view_offline_map_city_list);
            } else if (item.getItemViewType().equals(OfflineMapProvinceItemViewModel.TYPE_PROVINCE)) {
                itemBinding.set(BR.viewModel, R.layout.item_offline_map_province);
            }
        };
    }

    private void initCityItemView() {
        cityItemView = ItemBinding.of(BR.city, R.layout.item_offline_map_city);
    }

    private IItemViewModel createCityItemViewModel(OfflineMapCity item) {
        return new OfflineMapCityItemViewModel(fragment.getActivity(), item, offlineMapManager);
    }

    private IItemViewModel createCitiesItemViewModel(List<OfflineMapCity> cities) {
        return new OfflineMapCitiesItemViewModel(fragment.getActivity(), cities, offlineMapManager);
    }

    private IItemViewModel createProvinceItemViewModel(OfflineMapProvince province) {
        OfflineMapProvinceItemViewModel offlineMapProvinceItemViewModel = new OfflineMapProvinceItemViewModel(fragment.getActivity(), province);
        offlineMapProvinceItemViewModel.setOnProvinceClickListener(this);
        return offlineMapProvinceItemViewModel;
    }

    @Override
    public void onItemClick(IItemViewModel itemViewModel) {
        if (itemViewModel.getItemViewType().equals(OfflineMapProvinceItemViewModel.TYPE_PROVINCE)) {
            OfflineMapProvinceItemViewModel provinceItemViewModel = (OfflineMapProvinceItemViewModel) itemViewModel;
            onProvinceClick(provinceItemViewModel);
        }
    }

    private void onError(Throwable throwable) {
        ToastUtils.showShortToast(throwable.getMessage());
    }

    @Override
    public void onDownload(int i, int i1, String s) {
        Observable.from(provinceCities.entrySet())
                .map(Map.Entry::getValue)
                .subscribe(OfflineMapCitiesItemViewModel::notifyDataSetChanged);
        for (IItemViewModel itemViewModel : cityItems) {
            ((OfflineMapCityItemViewModel)itemViewModel).notifyDataChanged();
        }
    }

    @Override
    public void onCheckUpdate(boolean b, String s) {

    }

    @Override
    public void onRemove(boolean b, String s, String s1) {

    }

    @Bindable
    public OnItemBind<IItemViewModel> getProvinceItemView() {
        return provinceItemView;
    }

    @Bindable
    public ItemBinding<IItemViewModel> getCityItemView() {
        return cityItemView;
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
                .subscribe(itemViewModel -> provinceItems.remove(itemViewModel));
    }

    private void unfoldProvince(OfflineMapProvinceItemViewModel provinceItemViewModel) {
        int position = provinceItems.indexOf(provinceItemViewModel);
        String provinceName = provinceItemViewModel.getProvinceName();
        OfflineMapCitiesItemViewModel citiesItemViewModel = provinceCities.get(provinceName);
        if (citiesItemViewModel == null) {
            List<OfflineMapCity> cities = provinceItemViewModel.getCities();
            citiesItemViewModel = new OfflineMapCitiesItemViewModel(fragment.getActivity(), cities, offlineMapManager);
            provinceCities.put(provinceName, citiesItemViewModel);
        }
        provinceItems.add(position + 1, citiesItemViewModel);
    }
}
