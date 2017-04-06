package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;

import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.item.OfflineMapCityItemViewModel;

/**
 * Created by qiyao on 2017/4/6.
 */

public class OfflineMapManageViewModel extends ACollectionViewModel<OfflineMapCity> implements OfflineMapManager.OfflineMapDownloadListener {
    private OfflineMapManager offlineMapManager;
    public OfflineMapManageViewModel(Fragment fragment) {
        super(fragment, false, false);
//        offlineMapManager = new OfflineMapManager(fragment.getActivity(), this);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
/*        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<OfflineMapCity>> getData(RefreshMode mode) {
                ArrayList<OfflineMapCity> list = offlineMapManager.getDownloadingCityList();
                list.addAll(offlineMapManager.getDownloadOfflineMapCityList());
                return Observable.just(list);
            }
        }.execute();*/
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_offline_map_city, BR.city);
    }

    @Override
    protected IItemViewModel newItemViewModel(OfflineMapCity item) {
        return new OfflineMapCityItemViewModel(getFragment().getActivity(), item, offlineMapManager);
    }

    @Override
    public void onDownload(int i, int i1, String s) {
        for (IItemViewModel itemViewModel : getItems()) {
            ((OfflineMapCityItemViewModel)itemViewModel).notifyDataChanged();
        }
    }

    @Override
    public void onCheckUpdate(boolean b, String s) {

    }

    @Override
    public void onRemove(boolean b, String s, String s1) {

    }
}
