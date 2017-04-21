package com.qiyao.bysj.runningisthebest.common;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 19:51.
 * 类描述：
 */

public class Constants {
    public static final String PATTER_DATE = "yyyy-MM-dd";
    public static final int FLAG_TOTAL_RUN_WEEK = 3;
    public static final int FLAG_TOTAL_RUN_MONTH = 2;
    public static final int FLAG_TOTAL_RUN = 1;

    public static final String TYPE_WEEK = "WEEK";
    public static final String TYPE_MONTH = "MONTH";
    public static final String TYPE_TOTAL = "TOTAL";

    public static final int DEFAULT_PAGE_SIZE = 15;

    public static final String EVENT_REFRESH_DOWNLOAD_MAPS = "EVENT_REFRESH_DOWNLOAD_MAPS";

    public static final String EVENT_REFRESH_FRIENDS_LIST = "EVENT_REFRESH_FRIENDS_LIST";

    public static final String EVENT_PUBLISH_MOMENT_SUCCESS = "EVENT_PUBLISH_MOMENT_SUCCESS";

    //添加好友
    public final static int FRIEND_STATUS_ADD_FRIENDS = 0;

    //已经成为好友
    public final static int FRIEND_STATUS_HAS_BEEN_FRIENDS = 1;

    //是我自己
    public final static int FRIEND_STATUS_IS_MYSELF = 2;

    //不是好友
    public final static int FRIEND_STATUS_NOT_FRIEND = 3;
}
