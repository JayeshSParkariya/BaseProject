package hightecit.appname.kuwait;

import android.app.Application;
import android.content.res.Configuration;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Parth
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    public static boolean isLTR = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ActiveAndroid.initialize(this);
        isLTR = Utils.getLanguageDirection(this).equalsIgnoreCase("ltr");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Utils.setMyLocale(this, Utils.getLanguage(mInstance).equalsIgnoreCase("en") ? "en" : "ar");
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}