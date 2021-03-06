package com.hhit.bamboolibrary.base;

import android.app.Application;

import com.hhit.bamboolibrary.di.AppModule.AppModule;
import com.hhit.bamboolibrary.di.AppModule.HttpModule;
import com.hhit.bamboolibrary.di.component.AppComponent;
import com.hhit.bamboolibrary.di.component.DaggerAppComponent;

/**
 * Created by xiaopeng on 2017/8/6.
 */

/**
 * App直接使用BaseApplication, 在 AndroidManifest.xml 声明 或者继承 BaseApplication
 */
public class BaseApplication extends Application {

    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))//提供application
                .httpModule(new HttpModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
