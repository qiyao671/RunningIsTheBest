package com.qiyao.bysj.baselibrary.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import com.qiyao.bysj.baselibrary.support.bindinghelper.IItemViewBindingCreator;
import com.qiyao.bysj.baselibrary.support.bindinghelper.OnLoadMoreListener;
import com.qiyao.bysj.baselibrary.support.bindinghelper.SimpleLoadMoreViewBindingCreator;
import com.qiyao.bysj.baselibrary.support.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.SimpleLoadMoreViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemBinding;
import me.tatarka.bindingcollectionadapter.OnItemBind;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/1 23:53.
 * 类描述：
 */

public abstract class ACollectionViewModel<T> implements IViewModel, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private Fragment fragment;
    private IItemViewBindingCreator<Object> headerViewBindingCreator;
    private IItemViewBindingCreator<Object> footerViewBindingCreator;

    //下拉刷新
    public final ObservableField<SwipeRefreshLayout.OnRefreshListener> onRefreshListener = new ObservableField<>();
    public final ObservableBoolean isRefreshing = new ObservableBoolean(false);
    public final ObservableBoolean isRefreshEnable = new ObservableBoolean(true);

    //加载更多
    private boolean isNextLoadEnable = false;
    private boolean isLoadMoreEnable = true;
    private boolean isLoading = false;
    public final ObservableField<OnLoadMoreListener> onLoadMoreListener = new ObservableField<>();
    private SimpleLoadMoreViewBindingCreator loadMoreViewBindingCreator;

    //data for presenter
    private final ItemBinding itemBinding = ItemBinding.of(createOnItemBind());

    //child viewModel
    public final ObservableArrayList<IItemViewModel> itemViewModels = new ObservableArrayList<>();

    public ACollectionViewModel(Fragment fragment) {
        this.fragment = fragment;

        headerViewBindingCreator = createHeaderViewBindingHelper();
        footerViewBindingCreator = createFooterViewBindingHelper();
        loadMoreViewBindingCreator = createLoadMoreViewBindingHelper();

        initItemViewModelList();

        onRefreshListener.set(this);
        onLoadMoreListener.set(this);
    }

    public ACollectionViewModel(Fragment fragment, boolean isRefreshEnable, boolean isLoadMoreEnable) {
        this(fragment);
        this.isRefreshEnable.set(isRefreshEnable);
        this.isLoadMoreEnable = isLoadMoreEnable;
    }

    //刷新的回调
    @Override
    public void onRefresh() {
        if (isRefreshEnable.get()) {
            isRefreshing.set(true);
            requestData(RefreshMode.refresh);
        }
    }

    //加载更多的回调
    @Override
    public void onLoadMore() {
        if (isLoadMoreEnable && !isLoading && isNextLoadEnable) {
            isLoading = true;
            requestData(RefreshMode.load_more);
        }
    }

    //创建OnItemBind用于创建ItemBinding
    private OnItemBind<IItemViewModel> createOnItemBind(){
        return this::setupItemView;
    }

    //设置position上的item的itemBinding 用于绑定item与它的view
    protected void setupItemView(ItemBinding itemBinding, int position, IItemViewModel item) {
        ViewBindingRes viewBindingRes = getBindingRes(position, item);
        setItemBinding(itemBinding, viewBindingRes);
    }

    //itemBinding.set
    public void setItemBinding(ItemBinding itemBinding, ViewBindingRes viewBindingRes) {
        itemBinding.set(viewBindingRes.getBindingVariableRes(), viewBindingRes.getLayoutRes());
    }

    private ViewBindingRes getBindingRes(int position, IItemViewModel item) {
        switch (item.getItemViewType()) {
            case StaticItemViewModel.TYPE_HEADER:
                return getHeaderRes();
            case StaticItemViewModel.TYPE_FOOTER:
                return getFooterRes();
            case StaticItemViewModel.TYPE_LOAD_MORE:
                return getLoadMoreViewRes();
            default:
                return getItemRes(position, item);
        }
    }

    protected void addListToItemViewModels(RefreshMode mode, List<IItemViewModel> itemViewModelArrayList) {
        if (mode == RefreshMode.load_more) {
            addListAtFootOfItemViewModels(itemViewModelArrayList);
        } else if (mode == RefreshMode.reset) {
            clearItemViewModels();
            itemViewModels.addAll(itemViewModelArrayList);
        } else if (mode == RefreshMode.refresh) {
            addListAtHeadOfItemViewModels(itemViewModelArrayList);
        }
    }

    private void addListAtHeadOfItemViewModels(List<IItemViewModel> itemViewModelArrayList) {
        itemViewModels.addAll(getHeaderViewCount(), itemViewModelArrayList);
    }

    private void addListAtFootOfItemViewModels(List<IItemViewModel> itemViewModelArrayList) {
        int index = itemViewModels.size() - getFooterViewCount() - getLoadMoreViewCount();
        itemViewModels.addAll(index, itemViewModelArrayList);
    }

    protected void clearItemViewModels() {
        itemViewModels.clear();
        addStaticViewModel();
    }

    private void initItemViewModelList() {
        requestData(RefreshMode.reset);
    }

    private void addStaticViewModel() {
        //添加header view model
        if (getHeaderViewCount() > 0) {
            IItemViewModel headerViewModel = headerViewBindingCreator.genItemViewModel(null);
            itemViewModels.add(0, headerViewModel == null ? new StaticItemViewModel(StaticItemViewModel.TYPE_HEADER) : headerViewModel);
        }

        //添加footer view model
        if (getFooterViewCount() > 0) {
         IItemViewModel footerViewModel = footerViewBindingCreator.genItemViewModel(null);
            itemViewModels.add(footerViewModel == null ? new StaticItemViewModel(StaticItemViewModel.TYPE_FOOTER) : footerViewModel);
        }

        //添加load more view model
        addLoadMoreViewModel();

        // TODO: 2017/2/10 添加empty view
    }

    private SimpleLoadMoreViewModel addLoadMoreViewModel() {
        int itemCount = itemViewModels.size() - (getHeaderViewCount() + getFooterViewCount());
        if (getLoadMoreViewCount() > 0 && itemCount > 0 && isLoadMoreEnable) {
            SimpleLoadMoreViewModel loadMoreViewModel = loadMoreViewBindingCreator.genItemViewModel(null);
            itemViewModels.add(loadMoreViewModel);
            return loadMoreViewModel;
        }
        return null;
    }

    protected IItemViewBindingCreator<Object> createHeaderViewBindingHelper() {
        return null;
    }

    protected IItemViewBindingCreator<Object> createFooterViewBindingHelper() {
        return null;
    }

    protected SimpleLoadMoreViewBindingCreator createLoadMoreViewBindingHelper() {
        return new SimpleLoadMoreViewBindingCreator(fragment.getActivity());
    }

//    protected abstract ArrayList<IItemViewModel> generateItemViewModelList(ArrayList<T> items);
//
//    protected abstract ArrayList<T> obtainDataSource(RefreshMode refreshMode);

    protected abstract void requestData(RefreshMode refreshMode);

    protected abstract ViewBindingRes getItemRes(int position, IItemViewModel item);

    protected abstract IItemViewModel newItemViewModel(T item);

    public ViewBindingRes getHeaderRes() {
        return headerViewBindingCreator == null ? null : headerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getFooterRes() {
        return footerViewBindingCreator == null ? null :footerViewBindingCreator.genViewBindingRes();
    }

    public ViewBindingRes getLoadMoreViewRes() {
        return loadMoreViewBindingCreator == null ? null :loadMoreViewBindingCreator.genViewBindingRes();
    }

    public ItemBinding getItemBinding() {
        return itemBinding;
    }

    private int getHeaderViewCount() {
        return getHeaderRes() == null ? 0 : 1;
    }

    private int getFooterViewCount() {
        return getFooterRes() == null ? 0 : 1;
    }

    private int getLoadMoreViewCount() {
        return getLoadMoreViewRes() == null || loadMoreViewBindingCreator.genItemViewModel(null) == null ? 0 : 1;
    }

    public Fragment getFragment() {
        return fragment;
    }

    private SimpleLoadMoreViewModel getLoadMoreViewModel() {
        if (itemViewModels.size() == 0) {
            return null;
        }
        IItemViewModel loadMoreViewModel = itemViewModels.get(itemViewModels.size() - 1);
        if (loadMoreViewModel != null
                && loadMoreViewModel.getItemViewType().equals(StaticItemViewModel.TYPE_LOAD_MORE) ) {
            return (SimpleLoadMoreViewModel)loadMoreViewModel;
        }
        return null;
    }

    public boolean isRefreshing() {
        return isRefreshing.get();
    }

    public void setRefreshing(boolean isRefreshing) {
        this.isRefreshing.set(isRefreshing);
    }

    public boolean isRefreshEnable() {
        return isRefreshEnable.get();
    }

    public void setRefreshEnable(boolean isRefreshEnable) {
        this.isRefreshEnable.set(isRefreshEnable);
    }

    public boolean isNextLoadEnable() {
        return isNextLoadEnable;
    }

    public void setNextLoadEnable(boolean nextLoadEnable) {
        isNextLoadEnable = nextLoadEnable;
    }

    public boolean isLoadMoreEnable() {
        return isLoadMoreEnable;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        isLoadMoreEnable = loadMoreEnable;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public enum RefreshMode {
        /**
         * 重设数据
         */
        reset,
        /**
         * 上拉，加载更多
         */
        load_more,
        /**
         * 下拉，刷新最新
         */
        refresh
    }

    protected abstract class APagingTask implements Observer<List<IItemViewModel>> {
        protected RefreshMode mode;

        public APagingTask(RefreshMode mode) {
            this.mode = mode;
        }

        protected abstract Observable<List<T>> getData(RefreshMode mode);

        public void execute() {
            prepare(mode);
            // TODO: 2017/2/13 设置loading界面 加载完成或加载更多
            executeTask(getData(mode));
        }

        protected void prepare(RefreshMode mode) {

        }

        protected void executeTask(Observable<List<T>> result) {
            result
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .flatMap(Observable::from)
                    .map(ACollectionViewModel.this::newItemViewModel)
                    .toList()
                    .compose(((RxFragment) fragment).bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }

        protected void handleNewItemViewModels(RefreshMode mode, List<IItemViewModel> newItemViewModels){}

        @Override
        public void onError(Throwable e) {
            // TODO: 2017/2/13 加载失败
            Log.e("TAG", "onError: " + e.getMessage());
        }

        @Override
        public void onCompleted() {
            SimpleLoadMoreViewModel loadMoreViewModel = getLoadMoreViewModel();
            if (itemViewModels.isEmpty()) {
                // TODO: 2017/2/15 设置empty view
            } else{
                if (loadMoreViewModel == null && getLoadMoreViewCount() > 0) {    //如果数据不为空且loadMoreViewModel为null则添加loadMoreViewModel
                    loadMoreViewModel = addLoadMoreViewModel();
                }
                if (isLoadMoreEnable && isLoading) {
                    isLoading = false;

                    if (loadMoreViewModel != null) {
                        if (isNextLoadEnable) {
                            loadMoreViewModel.loadMore();
                        } else {
                            loadMoreViewModel.noMore();
                        }
                    }
                }
            }

            if (isRefreshEnable.get() && isRefreshing.get()) {
                isRefreshing.set(false);
            }

        }

        @Override
        public void onNext(List<IItemViewModel> iItemViewModels) {
            handleNewItemViewModels(mode, iItemViewModels);
            if (!iItemViewModels.isEmpty()) {
                addListToItemViewModels(mode, iItemViewModels);
            }
        }
    }
}
