package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsViewModel extends ACollectionViewModel<RunBean> {
    public RunRecordsViewModel(Fragment fragment) {
        super(fragment, false, true);
    }
    private int page;

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<RunBean>> getData(RefreshMode mode) {
                if (mode == RefreshMode.refresh || mode == RefreshMode.reset) {
                    page = 1;
                } else{
                    page ++;
                }

                return HttpMethods.getInstance()
                        .getMyRunRecords(page, Constants.DEFAULT_PAGE_SIZE)
                        .doOnNext(result -> setNextLoadEnable(result.isHasNextPage()))
                        .map(ListResultBean::getList);
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_run_record, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(RunBean item) {
        return new RunRecordsItemViewModel(getFragment(), item);
    }
}
