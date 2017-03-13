package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.app.Fragment;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.common.utils.PinyinUtils;
import com.qiyao.bysj.baselibrary.component.bindinghelper.IItemViewBindingCreator;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ASectionCollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.List;

import rx.Observable;

/**
 * Created by qiyao on 2017/3/10.
 */

public class FriendsViewModel extends ASectionCollectionViewModel<String, UserBean> {
    private boolean hasInit;
    public FriendsViewModel(Fragment fragment) {
        super(fragment, false, false);
    }

    @Override
    protected boolean isSectionHeader(int position, IItemViewModel itemViewModel) {
        return itemViewModel.getItemViewType().equals(TYPE_SECTION_HEADER);
    }

    @Override
    protected String headerOfSection(int section) {
        return null;
    }

    @Override
    protected String headerForSectionOfItem(UserBean item) {
        return PinyinUtils.getPinyinFirstLetter(item.getUsername());
    }

    @Override
    protected ViewBindingRes getSectionItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_friend, BR.viewModel);
    }

    @Override
    protected ViewBindingRes getSectionHeaderRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_section_header_friend, BR.viewModel);
    }

    @Override
    protected IItemViewModel newSectionHeaderItemViewModel(String header) {
        return new FriendsSectionHeaderItemViewModel(header);
    }

    @Override
    protected int compareToHeaders(String s, String s2) {
        char c = s.charAt(0);
        char c1 = s2.charAt(0);
        if (c < c1) {
            return -1;
        } else if (c == c1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    protected int compareToItems(UserBean user, UserBean user2) {
        String s = PinyinUtils.ccs2Pinyin(user.getUsername());
        String s1 = PinyinUtils.ccs2Pinyin(user2.getUsername());
        return s.compareToIgnoreCase(s1);
    }

    @Override
    protected IItemViewBindingCreator<UserBean> createHeaderViewBindingHelper() {
        return new IItemViewBindingCreator<UserBean>() {
            @NonNull
            @Override
            public ViewBindingRes genViewBindingRes() {
                return new ViewBindingRes(R.layout.item_header_friends, BR.viewModel);
            }

            @NonNull
            @Override
            public IItemViewModel genItemViewModel(UserBean item) {
                return new FriendsHeaderViewModel();
            }
        };
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
//        new ASectionTask(refreshMode) {
//            @Override
//            protected Observable<List<UserBean>> getData(RefreshMode mode) {
//                List<UserBean> list = new ArrayList<UserBean>();
//                UserBean user = new UserBean();
//                user.setUsername("aweads");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("awers");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("casfa");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("猜猜看");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("爱啊欸");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("欸容皮u");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("iciasm");
//                list.add(user);
//                user = new UserBean();
//                user.setUsername("caes");
//                list.add(user);
//                return Observable.just(list);
//            }
//        }.execute();
        new GetFriends(refreshMode).execute();
    }

    @Override
    protected IItemViewModel newItemViewModel(UserBean item) {
        return new FriendItemViewModel(getFragment().getActivity(), item);
    }

    @Override
    protected void beforeRequest() {
        new ASectionTask(RefreshMode.reset) {
            @Override
            protected Observable<List<UserBean>> getData(RefreshMode mode) {
                return Observable.just(SPHelper.loadFriends());
            }
        }.execute();
    }

    private class GetFriends extends ASectionTask {
        GetFriends(RefreshMode mode) {
            super(mode);
        }

        @Override
        protected Observable<List<UserBean>> getData(RefreshMode mode) {
            if (!hasInit) {
                return Observable.just(SPHelper.loadFriends());
            }
            HttpMethods httpMethods = HttpMethods.getInstance();
            return httpMethods.getFriends()
                    .map(ListResultBean::getList)
                    .doOnNext(SPHelper::saveFriends);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            if (!hasInit) {
                hasInit = true;
            }
        }
    }
}