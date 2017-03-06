package com.qiyao.bysj.runningisthebest.module.login.viewmodel;


import android.app.Fragment;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;

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
