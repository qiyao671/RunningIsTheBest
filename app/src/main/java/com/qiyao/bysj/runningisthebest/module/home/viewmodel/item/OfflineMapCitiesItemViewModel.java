package com.qiyao.bysj.runningisthebest.module.home.viewmodel.item;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import rx.Observable;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapCitiesItemViewModel extends BaseObservable implements IItemViewModel {
    private Context context;

    private ItemBinding<IItemViewModel> itemBinding;
    public ObservableArrayList<IItemViewModel> cities = new ObservableArrayList<>();

    public static final String TYPE_CITIES = "CITIES";

    public OfflineMapCitiesItemViewModel(Context context, List<OfflineMapCity> cityList, OfflineMapManager offlineMapManager) {
        this.context = context;
        initItems(cityList, offlineMapManager);
        initItemView();
    }

    private void initItems(List<OfflineMapCity> cityList, OfflineMapManager offlineMapManager) {
        Observable.from(cityList)
                .map(city -> createCityItemViewModel(city, offlineMapManager))
                .subscribe(cities::add);
    }

    private void initItemView() {
        itemBinding = ItemBinding.of(BR.city, R.layout.item_offline_map_city);
    }

    private IItemViewModel createCityItemViewModel(OfflineMapCity item, OfflineMapManager offlineMapManager) {
        return new OfflineMapCityItemViewModel(context, item, offlineMapManager);
    }

    @Bindable
    public ItemBinding<IItemViewModel> getItemBinding() {
        return itemBinding;
    }

    @Override
    public String getItemViewType() {
        return TYPE_CITIES;
    }

    public void notifyDataSetChanged() {
        for (IItemViewModel city : cities) {
            ((OfflineMapCityItemViewModel)city).notifyDataChanged();
        }
    }
}
