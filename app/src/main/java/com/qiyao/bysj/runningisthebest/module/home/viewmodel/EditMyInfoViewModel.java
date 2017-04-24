package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.model.event.MessageEvent;
import com.qiyao.bysj.baselibrary.model.event.RxBus;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.LocationJsonBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.HomeFragment;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import rx.Observable;
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
    public ObservableField<String> signature = new ObservableField<>();

    private List<String> provinces = new ArrayList<>();
    private List<List<String>> cities = new ArrayList<>();

    public EditMyInfoViewModel(Fragment fragment, UserBean userBean) {
        this.fragment = fragment;
        this.userBean = userBean;

        initLocationSource();

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
        signature.set(userBean.getSignature());
        setBirthday(userBean.getBirthday());

    }

    private void initLocationSource() {
        Observable.just("location.json")
                .observeOn(Schedulers.io())
                .compose(((RxFragment) fragment).bindToLifecycle())
                .map(s -> MyAppUtils.readTextFileToStringFromAssets(fragment.getActivity(), s, "UTF-8"))
                .map(s -> new Gson().fromJson(s, LocationJsonBean[].class))
                .subscribe(this::setLocationLists, e -> Log.e("init location", "initLocationSource: " + e.getMessage(), e));
    }

    private void setLocationLists(LocationJsonBean[] provinceArray) {
        for (int i = 1; i < provinceArray.length - 1; i++) {
            LocationJsonBean province = provinceArray[i];
            provinces.add(province.getName());
            List<String> provinceCities = new ArrayList<>();
//            List<List<String>> provinceCityAreas = new ArrayList<>();
            if (province.getSub() != null) {
                for (int j = 1; j < province.getSub().size() - 1; j++) {
                    LocationJsonBean city = province.getSub().get(j);
                    provinceCities.add(city.getName());
/*                    List<String> cityAreas = new ArrayList<>();
                    if (city.getSub() != null) {
                        for (int k = 1; k < city.getSub().size() - 1; k++) {
                            cityAreas.add(city.getSub().get(k).getName());
                        }
                    }
                    provinceCityAreas.add(cityAreas);*/
                }
            }
            cities.add(provinceCities);
//            areas.add(provinceCityAreas);
        }
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
                pickImage();
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
            case R.id.signature:
                showSignatureFillDialog();
                break;
        }
    }

    private void pickImage() {
/*        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image*//*");

        fragment.startActivityForResult(pickIntent, EditMyInfoFragment.CODE_PICK_IMAGE);*/
        RxGalleryFinal
                .with(fragment.getActivity())
                .image()
                .radio()
                .cropWithAspectRatio(1f, 1f)
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        profileUrl.set(imageRadioResultEvent.getResult().getCropPath());
                    }
                })
                .openGallery();
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
                .itemsCallback(listCallback)
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
        TimePickerView pvTime = new TimePickerView
                .Builder(fragment.getActivity(), (date, v) -> updateBirthday(date))
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build();
        Calendar date = Calendar.getInstance();
        date.set(2000, 1, 1);
        pvTime.setDate(date);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }


    private void showLocationPicker() {
        @SuppressWarnings("unchecked")
        OptionsPickerView<String> pvOptions = new OptionsPickerView
                .Builder(fragment.getActivity(), (options1, options2, options3, v) -> updateLocation(options1, options2))
                .build();
        pvOptions.setPicker(provinces, cities);
        pvOptions.show();
    }

    private void showSignatureFillDialog() {
        new MaterialDialog.Builder(fragment.getActivity())
                .title(R.string.fill_signature)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .negativeText(R.string.cancel)
                .input(null, null, false, (dialog, input) -> updateSignature(String.valueOf(input)))
                .show();
    }

    private void updateSex(String sex) {
        this.sex.set(sex);
        userBean.setSex(sex);
    }

    private void updateHeight(int height) {
        this.height.set(String.valueOf(height));
        userBean.setHeight((double) height);
    }

    private void updateWeight(int weight) {
        this.weight.set(String.valueOf(weight));
        userBean.setWeight((double) weight);
    }

    private void updateBirthday(Date birthday) {
        setBirthday(birthday.getTime());
        userBean.setBirthday(birthday.getTime());
    }

    private void updateLocation(int p, int c) {
        String location = provinces.get(p) + "·" + cities.get(p).get(c);
        this.location.set(location);
        userBean.setLocation(location);
    }

    private void updateSignature(String signature) {
        this.signature.set(signature);
        userBean.setSignature(signature);
    }

    public void submitUpdate() {
        Observable<String> observable = HttpMethods.getInstance()
                .updateUserInfo(userBean);
        if (!profileUrl.get().equals(userBean.getProfile())) {
            observable = Observable.merge(HttpMethods.getInstance()
                    .uploadProfile(Uri.parse(profileUrl.get())), observable);
        }

        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSubmitSuccess, e -> ToastUtils.showShortToast(e.getMessage()), () -> ToastUtils.showShortToast("信息修改成功"));
    }

    private void onSubmitSuccess(String msg) {
        RxBus.getDefault().post(new MessageEvent(HomeFragment.EVENT_EDIT_INFO, true));
    }
}
