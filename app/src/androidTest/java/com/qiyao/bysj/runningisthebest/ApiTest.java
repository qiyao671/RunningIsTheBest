package com.qiyao.bysj.runningisthebest;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/6 20:42.
 * 类描述：
 */
@RunWith(AndroidJUnit4.class)
public class ApiTest {
    @Test
    public void getBestRunTest() {
        HttpMethods.getInstance().getBestRun()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BestRunBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("best_run", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("best_run", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(BestRunBean bestRunBean) {
                        Log.d("best_run", "onNext: " + bestRunBean.getFarthestLogInfo().getMomentContent());
                    }
                });
    }
}
