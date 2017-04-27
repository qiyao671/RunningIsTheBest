package com.qiyao.bysj.runningisthebest.model.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.qiyao.bysj.baselibrary.model.bean.ListResult;
import com.qiyao.bysj.runningisthebest.model.DatabaseHelper;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by qiyao on 2017/4/27.
 */

public class RunDao {
    private Dao<RunBean, Integer> runDaoOpe;
    private DatabaseHelper helper;

    public RunDao(Context context)
    {
        try
        {
            helper = DatabaseHelper.getHelper(context);
            runDaoOpe = helper.getDao(RunBean.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void add(RunBean run)
    {
        try
        {
            runDaoOpe.create(run);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public List<RunBean> listByUserId(int userId) {
        try {
            return runDaoOpe.queryForEq("user_id", userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeList(List<RunBean> list) {
        try {
            runDaoOpe.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RunBean> listAll() {
        try {
            return runDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeAll() {
        try {
            runDaoOpe.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
