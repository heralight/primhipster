package com.heirko.prim.di.components

import android.content.Context
import com.google.gson.Gson
import dagger.Component
import com.heirko.prim.application.App
import com.heirko.prim.di.ForApplication
import com.heirko.prim.di.modules.AndroidModule
import com.heirko.prim.di.modules.ApplicationModule
import com.heirko.prim.domain.executors.ThreadExecutor
//import com.heirko.prim.di.components.DaggerApplicationComponent
import com.heirko.prim.network.OkHttpInterceptorsModule
import com.heirko.prim.environment.EnvironmentModule
import com.heirko.prim.storage.Storage
import com.heirko.prim.util.gson.GsonModule
import retrofit2.Retrofit
import javax.inject.Singleton

import org.greenrobot.eventbus.EventBus

// android-hipster-needle-component-injection-import

@Singleton
@Component(modules = arrayOf(
                            ApplicationModule::class,
                            AndroidModule::class,
                            OkHttpInterceptorsModule::class,
                            GsonModule::class,
                            EnvironmentModule::class))
interface ApplicationComponent {

    fun provideThreadExecutor(): ThreadExecutor

    fun provideStorage(): Storage

    fun provideRetrofit(): Retrofit

    @ForApplication
    fun provideContext(): Context

    fun provideGson(): Gson

    fun inject(app: App)

    fun provideEventBus(): EventBus

    // android-hipster-needle-component-injection-method

    object Initializer {
        fun init(app: App): ApplicationComponent {
            return DaggerApplicationComponent.builder().androidModule(AndroidModule()).okHttpInterceptorsModule(OkHttpInterceptorsModule()).gsonModule(GsonModule()).applicationModule(ApplicationModule(app)).environmentModule(EnvironmentModule(app)).build()
        }
    }

}