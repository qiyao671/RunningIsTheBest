package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.model.event.MessageEvent;
import com.qiyao.bysj.baselibrary.model.event.RxBus;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.WriteMomentImageItemViewModel;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.ui.RxGalleryListener;
import cn.finalteam.rxgalleryfinal.ui.base.IMultiImageCheckedListener;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
                itemBinding.set(BR.viewModel , R.layout.item_image);
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
        //得到图片多选的事件
        RxGalleryListener.getInstance().setMultiImageCheckedListener(new IMultiImageCheckedListener() {
            @Override
            public void selectedImg(Object t, boolean isChecked) {
                //这个主要点击或者按到就会触发，所以不建议在这里进行Toast
            }
            @Override
            public void selectedImgMax(Object t, boolean isChecked, int maxSize) {
            }
        });
    }

    private void addImagesToList(IItemViewModel itemViewModel) {
        images.add(itemViewModel);
        if (images.size() >= 9) {
            imagesWithAddBtn.removeItem(addImageItemViewModel);
        }
    }

    public void publishMoment() {
        MomentBean momentBean = new MomentBean();
        momentBean.setContent(content.get());
        List<String> pictures = new ArrayList<>();
        Observable.from(images)
                .map(itemViewModel -> ((WriteMomentImageItemViewModel) itemViewModel).imageUri.get())
                .subscribe(pictures::add);
        HttpMethods.getInstance()
                .publishMoment(momentBean, pictures)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(ToastUtils::showShortToast)
                .subscribe(s -> RxBus.getDefault().post(new MessageEvent(Constants.EVENT_PUBLISH_MOMENT_SUCCESS)), error -> ToastUtils.showShortToast(error.getMessage()));
    }
}
