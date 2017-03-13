package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
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
                runBean.setBeginTime(Calendar.getInstance().getTime());
                runBean.setSpendTime(12.8);
                runBean.setDistance(10.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTime());
                runBean.setSpendTime(132.8);
                runBean.setDistance(104.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTime());
                runBean.setSpendTime(122.8);
                runBean.setDistance(140.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTime());
                runBean.setSpendTime(112.8);
                runBean.setDistance(130.0);
                list.add(runBean);
                runBean = new RunBean();
                runBean.setBeginTime(Calendar.getInstance().getTime());
                runBean.setSpendTime(182.8);
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
        return new RunRecordsItemViewModel(item);
    }
}
