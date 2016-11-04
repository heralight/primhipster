package com.heirko.prim.application

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

import com.heirko.prim.R
import com.heirko.prim.di.ForApplication
import com.heirko.prim.di.components.ApplicationComponent
import com.heirko.prim.environment.EnvironmentConfiguration
import kotlin.properties.Delegates

import com.jakewharton.threetenabp.AndroidThreeTen 

import com.github.johnkil.print.PrintConfig
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import com.bumptech.glide.Glide

import javax.inject.Inject


class App : Application() {

    var graph:ApplicationComponent? = null

    var refWatcher:RefWatcher? = null

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @field:[Inject ForApplication]
    lateinit var context: Context

    @Inject
    lateinit var environmentConfiguration: EnvironmentConfiguration

    override fun onCreate() {
        super.onCreate()

        LeakCanary.install(this)
        refWatcher = LeakCanary.install(this)

        
        AndroidThreeTen.init(this) 
        PrintConfig.initDefault(assets, "fonts/MaterialIcons-Regular.ttf")
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.ttf").setFontAttrId(R.attr.fontPath).build()) 

        graph = createComponent()
        environmentConfiguration.configure()
    }

    companion object {
        @JvmStatic fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    fun getComponent(): ApplicationComponent {
        if (graph == null) {
            createComponent()
        }
        return graph!!
    }

    private fun createComponent(): ApplicationComponent {
        val applicationComponent = ApplicationComponent.Initializer.init(this)
        applicationComponent.inject(this)
        return applicationComponent
    }

    fun recreateComponents() {
        graph = ApplicationComponent.Initializer.init(this)
        graph!!.inject(this)
        environmentConfiguration.configure()
    }

    fun refWatcher(): RefWatcher? {
        return refWatcher
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

}
