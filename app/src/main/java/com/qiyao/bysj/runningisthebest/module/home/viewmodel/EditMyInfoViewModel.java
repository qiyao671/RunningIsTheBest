package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aigestudio.wheelpicker.IWheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.ArrayList;
import java.util.Date;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/15.
 */

public class EditMyInfoViewModel extends BaseObservable
        implements IViewModel, View.OnClickListener {
    public static final String KEY_USER_BEAN = "KEY_USER_BEAN";
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

        if (userBean.getUsername() != null) {
            userName = userBean.getUsername();
        }
        if (userBean.getId() != null) {
            userId = String.valueOf(userBean.getId());
        }

        profileUrl.set(userBean.getProfile());
        sex.set(userBean.getSex());
        height.set(String.valueOf(userBean.getHeight()));
        weight.set(String.valueOf(userBean.getWeight()));

        location.set(userBean.getLocation());
        setBirthday(userBean.getBirthday());
    }

    private void setBirthday(Long birthday) {
        if (birthday != null) {
            this.birthday.set(TimeUtils.millis2String(birthday, Constants.PATTER_DATE));
        }
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
            case R.id.location:
                showLocationPicker();
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

    private void showPicker(IWheelPicker picker, MaterialDialog.SingleButtonCallback positiveCallback) {

        new MaterialDialog.Builder(fragment.getActivity())
                .customView((View)picker, false)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(positiveCallback)
                .show();
    }

    private void showDatePicker() {
        WheelDatePicker datePicker = new WheelDatePicker(fragment.getActivity());
        datePicker.setCyclic(false);
        datePicker.setAtmospheric(true);
        datePicker.setCurved(true);
        datePicker.setSelectedYear(2000);
        datePicker.setSelectedMonth(1);
        datePicker.setSelectedDay(1);
        showPicker(datePicker, (dialog, which) -> updateBirthday(datePicker.getCurrentDate()));
    }


    private void showLocationPicker() {
/*        WheelAreaPicker areaPicker = new WheelAreaPicker(fragment.getActivity());
        areaPicker.setCyclic(false);
        areaPicker.setAtmospheric(true);
        areaPicker.setCurved(true);
        showPicker(areaPicker, (dialog, which) -> updateArea(areaPicker.getCity()));*/
    }

    private void updateSex(String sex) {
        this.sex.set(sex);
        userBean.setSex(sex);
    }

    private void updateHeight(int height) {
        this.height.set(String.valueOf(height));
        userBean.setHeight((double)height);
    }

    private void updateWeight(int weight) {
        this.weight.set(String.valueOf(weight));
        userBean.setWeight((double)weight);
    }

    private void updateBirthday(Date birthday) {
        setBirthday(birthday.getTime());
        userBean.setBirthday(birthday.getTime());
    }

    private void updateArea(String area) {
        this.location.set(area);
        userBean.setLocation(area);
    }

    public void submitUpdate() {
        HttpMethods.getInstance()
                .updateUserInfo(userBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ToastUtils::showShortToast, e -> ToastUtils.showShortToast(e.getMessage()));
    }
}
