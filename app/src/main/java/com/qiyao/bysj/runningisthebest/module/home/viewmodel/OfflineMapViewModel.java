package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.component.OnItemClickListener;
import com.qiyao.bysj.baselibrary.model.event.MessageEvent;
import com.qiyao.bysj.baselibrary.model.event.RxBus;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCitiesItemViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCityItemViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapProvinceItemViewModel;
import com.trello.rxlifecycle.components.RxActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import rx.Observable;

/**
 * Created by qiyao on 2017/4/6.
 */

public class OfflineMapViewModel extends BaseObservable implements IViewModel, OnItemClickListener, OfflineMapManager.OfflineMapDownloadListener, OfflineMapCityItemViewModel.OnStartDownloadListener {
    private Context context;
    private OfflineMapManager offlineMapManager;
    private HashMap<String, OfflineMapCitiesItemViewModel> provinceCities = new HashMap<>();

    public ObservableArrayList<IItemViewModel> cityItems = new ObservableArrayList<>();
    public ObservableArrayList<IItemViewModel> provinceItems = new ObservableArrayList<>();
    private OnItemBind<IItemViewModel> provinceItemView;
    private ItemBinding<IItemViewModel> cityItemView;

    public OfflineMapViewModel(Context context) {
        this.context = context;
        offlineMapManager = new OfflineMapManager(context, this);
        initItems();
        initItemView();
        receiveMassage();
    }

    private void initItems() {
        initProvinceItems();
//        initCityItems();
    }

    private void initCityItems() {
        cityItems.clear();
        List<OfflineMapCity> list = offlineMapManager.getDownloadingCityList();
        list.addAll(offlineMapManager.getDownloadOfflineMapCityList());
        Observable.concat(Observable.from(offlineMapManager.getDownloadingCityList()),
                Observable.from(offlineMapManager.getDownloadOfflineMapCityList()))
                .map(this::createCityItemViewModel)
                .subscribe(cityItems::add, this::onError);
    }

    private void initProvinceItems() {
        provinceItems.clear();
        provinceCities.clear();
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
        return new OfflineMapCityItemViewModel(context, item, offlineMapManager);
    }

    private OfflineMapCitiesItemViewModel createCitiesItemViewModel(List<OfflineMapCity> cities) {
        return new OfflineMapCitiesItemViewModel(context, cities, offlineMapManager, this);
    }

    private IItemViewModel createProvinceItemViewModel(OfflineMapProvince province) {
        OfflineMapProvinceItemViewModel offlineMapProvinceItemViewModel = new OfflineMapProvinceItemViewModel(context, province);
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
        notifyProvinceItemChanged();
        for (IItemViewModel itemViewModel : cityItems) {
            ((OfflineMapCityItemViewModel)itemViewModel).notifyDataChanged();
        }
    }

    @Override
    public void onCheckUpdate(boolean b, String s) {

    }

    @Override
    public void onRemove(boolean b, String s, String s1) {
        notifyProvinceItemChanged();
//        initCityItems();
        ToastUtils.showShortToast(R.string.delete_success);
    }

    private void notifyProvinceItemChanged() {
        Observable.from(provinceCities.entrySet())
                .map(Map.Entry::getValue)
                .subscribe(OfflineMapCitiesItemViewModel::notifyDataSetChanged);
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
            citiesItemViewModel = createCitiesItemViewModel(cities);
            provinceCities.put(provinceName, citiesItemViewModel);
        }
        provinceItems.add(position + 1, citiesItemViewModel);
    }

    @Override
    public void onStartDownload() {
//        initCityItems();
    }

    private void receiveMassage() {
        RxBus.getDefault()
                .toObservable(MessageEvent.class)
                .compose(((RxAppCompatActivity)context).bindToLifecycle())
                .subscribe(event -> {
                        if (event.getName().equals(Constants.EVENT_REFRESH_DOWNLOAD_MAPS)) {
                            initCityItems();
                        }
                });
    }
}
