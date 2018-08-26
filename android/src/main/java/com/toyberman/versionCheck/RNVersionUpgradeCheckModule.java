package com.toyberman.versionCheck;

import android.content.pm.PackageManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.crypto.KeyGenerator;

public class RNVersionUpgradeCheckModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNVersionUpgradeCheckModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNVersionUpgradeCheck";
    }


    public boolean isFirstInstall() {
        try {
            long firstInstallTime = this.reactContext.getPackageManager().getPackageInfo(this.reactContext.getPackageName(), 0).firstInstallTime;
            long lastUpdateTime = this.reactContext.getPackageManager().getPackageInfo(this.reactContext.getPackageName(), 0).lastUpdateTime;
            return firstInstallTime == lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }


    public boolean isInstallFromUpdate() {
        try {
            long firstInstallTime = this.reactContext.getPackageManager().getPackageInfo(this.reactContext.getPackageName(), 0).firstInstallTime;
            long lastUpdateTime = this.reactContext.getPackageManager().getPackageInfo(this.reactContext.getPackageName(), 0).lastUpdateTime;
            return firstInstallTime != lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isStoreVersion() {
        // A list with valid installers package name
        List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));
        // The package name of the app that has installed your app
        final String installer = this.reactContext.getPackageManager().getInstallerPackageName(this.reactContext.getPackageName());
        return installer != null && validInstallers.contains(installer);
    }

    @ReactMethod
    public void getVersionData(Callback callback) {

        WritableMap map = Arguments.createMap();

        map.putBoolean("isStoreVersion", isStoreVersion());

        if (isFirstInstall()) {
            map.putBoolean("isFirstLaunch", true);
        } else if (isInstallFromUpdate()) {
            map.putBoolean("isUpdated", true);
        } else {
            map.putBoolean("isSameVersion", true);
        }

        callback.invoke(map);
    }

}
