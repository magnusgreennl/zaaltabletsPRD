package com.mendix.nativetemplate;

import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;
import android.os.Build;
import android.view.View;

import androidx.annotation.Nullable;

import com.mendix.mendixnative.activity.MendixReactActivity;
import com.mendix.mendixnative.config.AppUrl;
import com.mendix.mendixnative.react.MendixApp;
import com.mendix.mendixnative.react.MxConfiguration;

public class MainActivity extends MendixReactActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getLifecycle().addObserver(new MendixActivityObserver(this));
        Boolean hasDeveloperSupport = ((MainApplication) getApplication()).getUseDeveloperSupport();
        mendixApp = new MendixApp(AppUrl.getUrlFromResource(this), MxConfiguration.WarningsFilter.none, hasDeveloperSupport, false);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyApp::MyWakelockTag");
        wakeLock.acquire();
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        View decorView =getWindow().getDecorView();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//hide nav bar
                    |View.SYSTEM_UI_FLAG_FULLSCREEN//hide status bar
                    |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } 
    }
}
