package com.heirko.prim.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import com.heirko.prim.application.App
import nucleus.view.NucleusAppCompatActivity
import butterknife.ButterKnife 
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity<P : BasePresenter<*>?> : NucleusAppCompatActivity<P>() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        injectModule()
        ButterKnife.bind(this) 
        presenterFactory = presenterFactory
    }

    protected abstract fun injectModule()

    protected abstract fun getLayoutResource(): Int

    @CallSuper
    override fun onDestroy() {
        ButterKnife.unbind(this) 
        super.onDestroy()
        App.get(this).refWatcher?.watch(this)
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    

}
