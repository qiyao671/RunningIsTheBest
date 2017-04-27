package com.qiyao.bysj.runningisthebest;

import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.dao.RunDao;

import org.junit.Test;

import java.util.List;

/**
 * Created by qiyao on 2017/4/27.
 */

public class RunDaoTest {
    @Test
    public void add() {
        RunBean runBean = new RunBean();
        runBean.setUserId(SPHelper.loadUser().getId());
        RunDao runDao = new RunDao(AppApplication.getInstance().getApplicationContext());
        runDao.add(runBean);
        List<RunBean> runBeen = runDao.listAll();

    }

    public void list(RunDao runDao) {
        List<RunBean> runBeen = runDao.listAll();
    }
}
