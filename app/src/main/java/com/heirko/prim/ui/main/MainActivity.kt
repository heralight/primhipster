package com.heirko.prim.ui.main

import android.os.Bundle
import com.heirko.prim.di.ActivityScope
import com.heirko.prim.di.components.MainComponent;
import com.heirko.prim.di.HasComponent
import com.heirko.prim.ui.base.BaseActivity
import com.heirko.prim.R
import com.heirko.prim.application.App
import com.heirko.prim.di.components.DaggerMainComponent
import com.heirko.prim.di.modules.MainModule

import nucleus.factory.PresenterFactory 

import javax.inject.Inject

@ActivityScope
class MainActivity : BaseActivity<MainPresenter>(), MainView, HasComponent<MainComponent>{

    @Inject
    lateinit var mainPresenter: MainPresenter

    lateinit var mainComponent: MainComponent

    override fun injectModule() {
        mainComponent = DaggerMainComponent.builder().applicationComponent(App.get(this).getComponent()).mainModule(MainModule(this)).build()
        mainComponent.inject(this)
    }

    override fun getPresenterFactory(): PresenterFactory<MainPresenter>? = PresenterFactory { mainPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun getComponent(): MainComponent {
        return mainComponent
    }

    
}
