package com.hhit.bamboolibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.mvp.IPresenter;

import javax.inject.Inject;

/**
 * Created by xiaopeng on 2017/8/6.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 注入Presenter: Presenter派生类的构造函数添加 @Inject注解 完成注入
     */
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        BaseApplication app =  (BaseApplication)this.getApplication();
        AppComponent appComponent = app.getAppComponent();
        injectComponent(appComponent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    /**
     * 子类提供Layout Id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 子类 初始化 依赖注入
     * @param appComponent 把全局appComponent传送给子类
     */
    protected abstract void injectComponent(AppComponent appComponent);
}
