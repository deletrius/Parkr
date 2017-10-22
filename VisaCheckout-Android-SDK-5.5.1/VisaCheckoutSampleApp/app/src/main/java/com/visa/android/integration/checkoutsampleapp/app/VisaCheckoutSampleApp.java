package com.visa.android.integration.checkoutsampleapp.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Copyright © 2016 Visa. All rights reserved.
 */
public class VisaCheckoutSampleApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
