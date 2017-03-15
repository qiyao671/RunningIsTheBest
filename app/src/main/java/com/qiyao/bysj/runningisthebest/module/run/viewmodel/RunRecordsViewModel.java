package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsViewModel extends ACollectionViewModel<RunBean> {
    public RunRecordsViewModel(Fragment fragment) {
        super(fragment, false, true);
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<RunBean>> getData(RefreshMode mode) {
                List<RunBean> list = new ArrayList<>();
                RunBean runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTimeInMillis());
                runBean.setSpendTime(1010L);
                runBean.setDistance(10.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTimeInMillis());
                runBean.setSpendTime(193191L);
                runBean.setDistance(104.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTimeInMillis());
                runBean.setSpendTime(122L);
                runBean.setDistance(140.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTimeInMillis());
                runBean.setSpendTime(112L);
                runBean.setDistance(130.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTimeInMillis());
                runBean.setSpendTime(182L);
                runBean.setDistance(160.0);
                list.add(runBean);

                return Observable.just(list);
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
