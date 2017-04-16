package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.WriteMomentImageItemViewModel;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/18 21:27.
 * 类描述：
 */

public class WriteMomentViewModel implements IViewModel, View.OnClickListener {
    private Fragment fragment;

    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<OnItemBind<IItemViewModel>> imageItemView = new ObservableField<>();
    private ObservableArrayList<IItemViewModel> images = new ObservableArrayList<>();
    private final WriteMomentAddImageItemViewModel addImageItemViewModel = new WriteMomentAddImageItemViewModel(this);
    public MergeObservableList<IItemViewModel> imagesWithAddBtn = new MergeObservableList<IItemViewModel>()
            .insertList(images)
            .insertItem(addImageItemViewModel);

    public WriteMomentViewModel(Fragment fragment) {
        this.fragment = fragment;
        imageItemView.set((itemBinding, position, item) -> {
            if (item.getItemViewType().equals(WriteMomentAddImageItemViewModel.TYPE_ADD_IMAGE)) {
                itemBinding.set(BR.viewModel, R.layout.item_add_image);
            } else {
                itemBinding.set(BR.imageUrl, R.layout.item_image);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_image:
                addImage();
                break;
        }
    }

    private void addImage() {
        int maxSize = 9 - images.size();
        RxGalleryFinal.with(fragment.getActivity())
                .image()
                .multiple()
                .maxSize(maxSize)
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        Observable.from(imageMultipleResultEvent.getResult())
                                .map((Func1<MediaBean, IItemViewModel>) mediaBean -> new WriteMomentImageItemViewModel(fragment.getActivity(), mediaBean.getOriginalPath()))
                                .subscribe(WriteMomentViewModel.this::addImagesToList);
                    }
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
//                        Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                    }
                }).openGallery();
    }

    private void addImagesToList(IItemViewModel itemViewModel) {
        images.add(itemViewModel);
        if (images.size() >= 9) {
            imagesWithAddBtn.removeItem(addImageItemViewModel);
        }
    }
}
