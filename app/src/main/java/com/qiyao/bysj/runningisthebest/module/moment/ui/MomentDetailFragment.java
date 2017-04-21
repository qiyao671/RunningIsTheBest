package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.qiyao.bysj.baselibrary.common.utils.KeyboardUtils;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentDetailViewModel;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentDetailFragment extends ADataBindingFragment implements IMomentDetailView {
    private static final String KEY_MOMENT = "MOMENT";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.detail);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new MomentDetailViewModel(this, arguments.getParcelable(KEY_MOMENT), this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_moment_detail;
    }

    public static void launch(Context context, MomentBean momentBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MOMENT, momentBean);
        FragmentContainerActivity.launch(context, MomentDetailFragment.class, bundle);
    }

    @Override
    public void showKeyboard() {
        EditText etComment = (EditText) getActivity().findViewById(R.id.et_comment);
        KeyboardUtils.showSoftInput(etComment);
    }
}
