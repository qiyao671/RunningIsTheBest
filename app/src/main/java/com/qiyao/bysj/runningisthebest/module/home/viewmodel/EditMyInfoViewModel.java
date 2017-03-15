package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by qiyao on 2017/3/15.
 */

public class EditMyInfoViewModel extends BaseObservable
        implements IViewModel, View.OnClickListener {
    public static final String KEY_USER_BEAN = "KEY_USER_BEAN";
    public ArrayList<String> weights = new ArrayList<>();
    private Fragment fragment;

    private UserBean userBean;

    private String userName;
    private String userId;
    public ObservableField<String> profileUrl = new ObservableField<>();
    public ObservableField<String> sex = new ObservableField<>();
    public ObservableField<String> height = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String> birthday = new ObservableField<>();
    public ObservableField<String> location = new ObservableField<>();

    public EditMyInfoViewModel(Fragment fragment, UserBean userBean) {
        this.fragment = fragment;
        this.userBean = userBean;

        if (userBean == null) {
            return;
        }

        userName = userBean.getUsername();
        userId = String.valueOf(userBean.getId());

        profileUrl.set(userBean.getProfile());
        if (userBean.getSex() != null) {
            sex.set(userBean.getSex());
        }
        height.set(String.valueOf(userBean.getHeight()));
        weight.set(String.valueOf(userBean.getWeight()));
        birthday.set(TimeUtils.millis2String(userBean.getBirthday(), Constants.PATTER_DATE));
        location.set(userBean.getLocation());

        initSelectList();
    }

    private void initSelectList() {

    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_profile:
                pickPicture();
                break;
            case R.id.sex:
                showSexChooseDialog();
                break;
            case R.id.height:
                showHeightChooseDialog();
                break;
            case R.id.weight:
                showWeightChooseDialog();
                break;
            case R.id.birthday:
                showDatePicker();
                break;
        }
    }

    private void pickPicture() {

    }

    private void showSexChooseDialog() {
        new MaterialDialog.Builder(fragment.getActivity())
                .title(R.string.select)
                .items(R.array.sexes)
                .itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
                    updateSex(String.valueOf(text));
                    return true;
                })
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .show();
    }

    private void showListDialog(ArrayList list, @NonNull MaterialDialog.ListCallback listCallback) {
        new MaterialDialog.Builder(fragment.getActivity())
                .title(R.string.select)
                .items(list)
                .itemsCallback( listCallback)
                .positiveText(R.string.cancel)
                .show();
    }

    private void showHeightChooseDialog() {
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = 30; i < 251; i++) {
            heights.add(i);
        }
        showListDialog(heights, (dialog, view, which, text) -> updateHeight(Integer.parseInt(String.valueOf(text))));
    }

    private void showWeightChooseDialog() {
        ArrayList<Integer> weights = new ArrayList<>();
        for (int i = 5; i < 151; i++) {
            weights.add(i);
        }
        showListDialog(weights, (dialog, view, which, text) -> updateWeight(Integer.parseInt(String.valueOf(text))));
    }

    private void showDatePicker() {
        new MaterialDialog.Builder(fragment.getActivity())
                .customView(R.layout.view_date_picker, false)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        WheelDatePicker datePicker = (WheelDatePicker) dialog.getCustomView();
                        birthday.set(TimeUtils.date2String(datePicker.getCurrentDate(), Constants.PATTER_DATE));
                    }
                })
                .show();
    }

    private void updateSex(String sex) {
        this.sex.set(sex);
        userBean.setSex(sex);
        updateUser();

    }

    private void updateHeight(int height) {
        this.height.set(String.valueOf(height));
        userBean.setHeight(height);
        updateUser();
    }

    private void updateWeight(int weight) {
        this.height.set(String.valueOf(weight));
        userBean.setWeight(weight);
        updateUser();
    }


    private void updateUser() {
        // TODO: 2017/3/15 更新机制？
    }
}
