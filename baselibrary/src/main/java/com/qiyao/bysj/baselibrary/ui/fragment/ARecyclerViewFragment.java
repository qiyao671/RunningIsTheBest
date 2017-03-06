package com.qiyao.bysj.baselibrary.ui.fragment;

import com.qiyao.bysj.baselibrary.R;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:00.
 * 类描述：
 */

public abstract class ARecyclerViewFragment extends AbsDataBindingFragment {
    /*private FragmentRecyclerViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        if (getViewModel() != null) {
            binding.setViewModel(getViewModel());
        }
        return binding.getRoot();
    }*/

    @Override
    protected int layoutRes() {
        return R.layout.fragment_recycler_view;
    }
}
