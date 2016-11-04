package com.heirko.prim.environment;

import android.app.Application
import android.os.StrictMode
import com.heirko.prim.BuildConfig;
import com.heirko.prim.di.ForApplication;
import com.facebook.stetho.Stetho;
import timber.log.Timber;
import com.heirko.prim.util.logging.CrashReportingTree; 
import javax.inject.Inject
import javax.inject.Singleton
import rx.schedulers.Schedulers

@Singleton
class EnvironmentConfiguration @Inject constructor() {

    @field:[Inject ForApplication]
    lateinit var app: Application

    fun configure() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())
        Schedulers.io().createWorker().schedule { Stetho.initializeWithDefaults(app) }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

}
