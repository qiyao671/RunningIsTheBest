package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/22 14:16.
 * 类描述：
 */

public class ImagePagerViewModel extends BaseObservable implements IViewModel {
    private List<String> imagesUrl;
    private ItemBinding<String> itemView = ItemBinding.of(BR.imageUrl, R.layout.item_image_large);

    public ImagePagerViewModel(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    @Bindable
    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    @Bindable
    public ItemBinding<String> getItemView() {
        return itemView;
    }
}
