package com.qiyao.bysj.baselibrary.common.context;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Handler;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;

@SuppressLint("Registered")
public class GlobalContext extends Application {

	private static GlobalContext _context;

	public final static int CONN_TIMEOUT = 30 * ConstUtils.SEC;
	public final static int READ_TIMEOUT = 30 * ConstUtils.SEC;

	@Override
	public void onCreate() {
		super.onCreate();
		
		_context = this;
	}

	public static GlobalContext getInstance() {
		return _context;
	}
	
/*	public Handler getHandler() {
		return mHandler;
	}

	Handler mHandler = new Handler() {
		
	};*/
}