package com.heirko.prim.di.modules

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import android.content.SharedPreferences
import com.google.gson.Gson

import org.greenrobot.eventbus.EventBus
import dagger.Module
import dagger.Provides
import com.heirko.prim.application.App
import com.heirko.prim.di.ForApplication;
import com.heirko.prim.domain.executors.JobExecutor
import com.heirko.prim.domain.executors.ThreadExecutor
import com.heirko.prim.storage.Storage

import rx.Scheduler
import rx.schedulers.Schedulers
// android-hipster-needle-module-provides-import


@Module
class ApplicationModule(val application: App) {

    @ForApplication
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @ForApplication
    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(): ThreadExecutor {
        var ex = JobExecutor()
       // Schedulers.from(ex);
        return  ex;
    }

    @Provides
    @Singleton
    fun provideStorage(gson: Gson, preferences: SharedPreferences): Storage {
        return Storage(preferences, gson)
    }

    
    @Provides
    @Singleton
    fun provideBus(): EventBus {
        return EventBus.getDefault();
    }
    

    // android-hipster-needle-module-provides-method
}