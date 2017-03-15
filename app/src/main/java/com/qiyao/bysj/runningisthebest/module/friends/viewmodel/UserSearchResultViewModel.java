package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.component.bindinghelper.ViewBindingRes;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.BR;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.List;

import rx.Observable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/12 22:58.
 * 类描述：
 */

public class UserSearchResultViewModel extends ACollectionViewModel<UserBean> {
    private String query;
    public UserSearchResultViewModel(Fragment fragment, String query) {
        super(fragment, false, false);
        this.query = query;
    }

    @Override
    protected void requestData(RefreshMode refreshMode) {
        /*new APagingTask(refreshMode) {
            @Override
            protected Observable getData(RefreshMode mode) {
                List<UserBean> list = new ArrayList<UserBean>();
                UserBean user = new UserBean();
                user.setUsername(query);
                list.add(user);
                user = new UserBean();
                user.setUsername("awers");
                list.add(user);
                user = new UserBean();
                user.setUsername("casfa");
                list.add(user);
                user = new UserBean();
                user.setUsername("猜猜看");
                list.add(user);
                user = new UserBean();
                user.setUsername("爱啊欸");
                list.add(user);
                user = new UserBean();
                user.setUsername("欸容皮u");
                list.add(user);
                user = new UserBean();
                user.setUsername("iciasm");
                list.add(user);
                user = new UserBean();
                user.setUsername("caes");
                list.add(user);
                return Observable.just(list);
            }
        }.execute();*/

        new APagingTask(refreshMode) {
            @Override
            protected Observable<List<UserBean>> getData(RefreshMode mode) {
                return HttpMethods.getInstance().getUsersByUserName(query);
            }
        }.execute();
    }

    @Override
    protected ViewBindingRes getItemRes(int position, IItemViewModel item) {
        return new ViewBindingRes(R.layout.item_user_search_result, BR.viewModel);
    }

    @Override
    protected IItemViewModel newItemViewModel(UserBean item) {
        return new UserSearchResultItemViewModel(getFragment().getActivity(), item);
    }

    public void executeQuery(String query) {
        this.query = query;
        requestData(RefreshMode.reset);
    }
}
