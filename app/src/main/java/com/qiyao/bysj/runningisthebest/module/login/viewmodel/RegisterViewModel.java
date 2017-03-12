package com.qiyao.bysj.runningisthebest.module.login.viewmodel;


import android.app.Fragment;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.MainActivity;
import com.trello.rxlifecycle.components.RxFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/6.
 */
public class RegisterViewModel implements IViewModel {
    private Fragment fragment;
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> rePassword = new ObservableField<>();
    public ObservableField<Boolean> isUserNameErrorEnabled = new ObservableField<>(false);
    public ObservableField<String> userNameError = new ObservableField<>();
    public ObservableField<Boolean> isPasswordErrorEnabled = new ObservableField<>(false);
    public ObservableField<String> passwordError = new ObservableField<>();
    public ObservableField<Boolean> isRePwdErrorEnabled = new ObservableField<>(false);
    public ObservableField<String> rePwdError = new ObservableField<>();

    public RegisterViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    public void register() {
        HttpMethods httpMethods = HttpMethods.getInstance();
        httpMethods.register(userName.get(), password.get())
                .subscribeOn(Schedulers.newThread())
                .filter(token -> token != null)
                .compose(((RxFragment)fragment).bindToLifecycle())
                .doOnNext(this::onRegisterSuccess)
                .flatMap(token -> httpMethods.getUserBean())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSaveUser, this::onError);
    }

    private void onRegisterSuccess(String token) {
        SPHelper.saveToken(token);
        AppApplication.instance().loadToken();
    }

    private void onSaveUser(UserBean user) {
        SPHelper.saveUser(user);
        MainActivity.launch(fragment.getActivity());
    }

    private void onError(Throwable e) {
        ToastUtils.showShortToast(e.getMessage());
    }

    public void onClick(View v) {
        String errorMsg = null;
        if (StringUtils.isEmpty(userName.get())) {
            errorMsg = fragment.getString(R.string.error_user_name);
        } else if (StringUtils.isEmpty(password.get())) {
            errorMsg = fragment.getString(R.string.error_pwd_null);
        } else if (password.get().length() > 20 || password.get().length() < 6) {
            errorMsg = fragment.getString(R.string.error_pwd_length);
        } else if (rePassword.get() == null || !rePassword.get().equals(password.get())) {
            errorMsg = fragment.getString(R.string.error_re_pwd);
        }
        if (errorMsg != null) {
            ToastUtils.showLongToast(errorMsg);
        } else {
            register();
        }
    }

    public void onPasswordFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            return;
        }
        if (password.get() != null) {
            int length = password.get().length();
            if (length > 20 || length < 6) {
//            isPasswordErrorEnabled.set(true);
                passwordError.set(fragment.getString(R.string.error_pwd_length));
            } else {
                isPasswordErrorEnabled.set(false);
            }
        } else {
            passwordError.set(fragment.getString(R.string.error_pwd_null));
        }
    }

    public void onUserNameFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            return;
        }
        if (TextUtils.isEmpty(userName.get())) {
            userNameError.set(fragment.getString(R.string.error_user_name));
        }
    }

    public void onRepeatPasswordFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            return;
        }
        if (password.get() != null && rePassword.get() != null) {
            if (!password.get().equals(rePassword.get())) {
                rePwdError.set(fragment.getString(R.string.error_re_pwd));
            } else {
                isRePwdErrorEnabled.set(false);
            }
        } else {
            isRePwdErrorEnabled.set(false);
        }
    }
}
