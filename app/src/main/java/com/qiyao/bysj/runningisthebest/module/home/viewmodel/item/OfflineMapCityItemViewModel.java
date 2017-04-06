package com.qiyao.bysj.runningisthebest.module.home.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapStatus;
import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapCityItemViewModel implements IItemViewModel, View.OnClickListener {
    private Context context;

    private OfflineMapManager offlineMapManager;

    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt status = new ObservableInt();
    public ObservableDouble size = new ObservableDouble();
    public ObservableInt completeCode = new ObservableInt();

    private ObservableField<OfflineMapCity> city = new ObservableField<>();

    public OfflineMapCityItemViewModel(Context context, OfflineMapCity city, OfflineMapManager offlineMapManager) {
        this.context = context;
        this.city.set(city);
        this.offlineMapManager = offlineMapManager;
        setInfo();
    }

    private void setInfo() {
        this.completeCode.set(city.get().getcompleteCode());
        this.name.set(city.get().getCity());
        this.size.set((city.get().getSize() / ConstUtils.MB));
        this.status.set(city.get().getState());
    }

    public void notifyDataChanged() {
        setInfo();
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }


    @Override
    public void onClick(View v) {
        switch (status.get()) {
            case OfflineMapStatus.UNZIP:
            case OfflineMapStatus.SUCCESS:
                // 解压中,下载成功啥不干
                break;
            case OfflineMapStatus.LOADING:
                pauseDownload();
                break;
            case OfflineMapStatus.PAUSE:
            case OfflineMapStatus.CHECKUPDATES:
            case OfflineMapStatus.ERROR:
            case OfflineMapStatus.WAITING:
//			case OfflineMapStatus.NEW_VERSION:
            default:
                startDownload();
//					Toast.makeText(mContext, "SD卡空间不多了", 1000).show();
                // 在暂停中点击，表示要开始下载
                // 在默认状态点击，表示开始下载
                // 在等待中点击，表示要开始下载
                // 要开始下载状态改为等待中，再回调中会自己修改
                break;
        }
    }


    private synchronized void startDownload() {
        try {
            offlineMapManager.downloadByCityCode(city.get().getCode());
//            setInfo(city);
        } catch (AMapException e) {
            ToastUtils.showShortToast(e.getErrorMessage());
            e.printStackTrace();
        }
    }

    private synchronized void pauseDownload() {
        offlineMapManager.pause();
//		amapManager.pauseByName(getCityName());
        //暂停下载之后，开始下一个等待中的任务
        offlineMapManager.restart();
    }

}
