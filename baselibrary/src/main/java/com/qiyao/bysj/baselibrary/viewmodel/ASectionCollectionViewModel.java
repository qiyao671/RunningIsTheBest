package com.qiyao.bysj.baselibrary.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/2/3.
 */

public abstract class ASectionCollectionViewModel<H, T> extends ACollectionViewModel<T> {
    public static final String TYPE_SECTION_HEADER = "TYPE_SECTION_HEADER";
    private HashMap<H, List<T>> sectionedItems;

    public ASectionCollectionViewModel(Fragment fragment) {
        super(fragment);
    }

    public ASectionCollectionViewModel(Fragment fragment, boolean isRefreshEnable, boolean isLoadMoreEnable) {
        super(fragment, isRefreshEnable, isLoadMoreEnable);
    }

    protected abstract boolean isSectionHeader(int position, IItemViewModel itemViewModel);

    // FIXME: 2017/2/3 是否需要这个函数
    protected abstract H headerOfSection(int section);

    //获得item所在section的header
    protected abstract H headerForSectionOfItem(T item);

    //获得Section的数量
//    protected abstract int numberOfSections();

    //获得某section中item的数量
//    protected abstract int numberOfItemsInSection(int section);
//
//    //获得某section中第index个item的ViewModel
//    protected abstract IItemViewModel itemViewModelAtIndexInSection(int index, int section);
//
//    //获得某section中header的ViewModel
//    protected abstract IItemViewModel headerViewModelOfSection(int section);

//    //设置数据源
//    protected abstract void setupDataSource(ArrayList<T> items);

    //设置item的layout和对应的binding variable
    protected abstract ViewBindingRes getSectionItemRes(int position, IItemViewModel item);

    //设置sectionItem的layout和对应的binding variable
    protected abstract ViewBindingRes getSectionHeaderRes(int position, IItemViewModel item);

    //创建sectionHeader的ItemViewModel
    protected abstract IItemViewModel newSectionHeaderItemViewModel(H header);

    protected abstract int compareToHeaders(H h, H h2);

    protected abstract int compareToItems(T h, T h2);

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        if (isSectionHeader(position, item)) {
            return getSectionHeaderRes(position, item);
        } else {
            return getSectionItemRes(position, item);
        }
    }

    public HashMap<H, List<T>> getSectionedItems() {
        return sectionedItems;
    }

    /*    //对items进行分组
    protected HashMap<H, ArrayList<T>> groupItems(Collection<T> items) {
        HashMap<H, ArrayList<T>> sectionedItems = new HashMap<>();
        for (T item : items) {
            H header = headerForSectionOfItem(item);
            if (sectionedItems.containsKey(header)) {
                sectionedItems.get(header).add(item);
            } else {
                ArrayList<T> list = new ArrayList<>();
                list.add(item);
                sectionedItems.put(header, list);
            }
        }
        return sectionedItems;
    }*/

    /*protected ArrayList<IItemViewModel> generateItemViewModelList() {
        ArrayList<IItemViewModel> itemViewModels = new ArrayList<>();
        for (int section = 0; section < numberOfSections(); section++) {
            itemViewModels.add(headerViewModelOfSection(section));
            for (int index = 0; index < numberOfItemsInSection(section); index++) {
                itemViewModels.add(itemViewModelAtIndexInSection(index, section));
            }
        }
        return itemViewModels;
    }*/

    /**
     * 第一种情况：可以添加数据，默认每次加载的必定是整个section且section的header不会重复
     * 第二种情况：不可添加数据，请override updateItemViewModels函数
     */
    protected abstract class ASectionTask extends APagingTask {
        private List<IItemViewModel> newItemViewModels = new ArrayList<>();

        public ASectionTask(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected void executeTask(Observable<List<T>> result) {
            result
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .filter(list -> list!=null && !list.isEmpty())
                    .flatMap(Observable::from)
                    .groupBy(ASectionCollectionViewModel.this::headerForSectionOfItem)
                    .doOnNext(htGroupedObservable -> sectionedItems.put(htGroupedObservable.getKey(), new ArrayList<>()))
                    .flatMap(htGroupedObservable -> htGroupedObservable.toSortedList(ASectionCollectionViewModel.this::compareToItems))
                    .compose(((RxFragment) getFragment()).bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::addItemsToSectionedItems, super::onError, this::sectionedItemsToItemViewModels);
        }

        @Override
        protected void prepare(RefreshMode mode) {
            if (sectionedItems == null) {
                sectionedItems = new HashMap<>();
            }
        }

        @Override
        public void onCompleted() {
            handleNewItemViewModels(mode, newItemViewModels);
            addListToItemViewModels(mode, newItemViewModels);
            super.onCompleted();
        }

        private void addItemsToSectionedItems(List<T> ts) {
            sectionedItems.get(headerForSectionOfItem(ts.get(0))).addAll(ts);
        }

        private void sectionedItemsToItemViewModels() {
            Observable.just(sectionedItems)
                    .flatMap(sectionedItems -> Observable.from(sectionedItems.keySet()))
                    .toSortedList(ASectionCollectionViewModel.this::compareToHeaders)
                    .flatMap(Observable::from)
                    .subscribe(h -> {
                        newItemViewModels.add(newSectionHeaderItemViewModel(h));
                        Observable.from(sectionedItems.get(h))
                                .map(ASectionCollectionViewModel.this::newItemViewModel)
                                .subscribe(newItemViewModels::add);
                    }, super::onError, this::onCompleted);
        }
    }
}
