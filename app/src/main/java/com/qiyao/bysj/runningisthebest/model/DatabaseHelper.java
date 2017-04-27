package com.qiyao.bysj.runningisthebest.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiyao on 2017/4/27.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "running_is_the_best.db";

    private Map<String, Dao> daos = new HashMap<>();

    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, RunBean.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, RunBean.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        context = context.getApplicationContext();
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    @Override
    public synchronized  <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        D dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = (D) daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }
    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        daos.clear();
    }

}
