package com.heirko.prim.ui.base

import com.heirko.prim.di.HasComponent

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nucleus.view.NucleusSupportFragment
import butterknife.Bind
import butterknife.ButterKnife 

abstract class BaseFragment<P : BasePresenter<*>> : NucleusSupportFragment<P>() {

    @CallSuper
    override fun onCreateView(inflater : LayoutInflater?, container : ViewGroup?, savedInstanceState : Bundle?) : View  {
        val rootView = inflater!!.inflate(getLayoutResource(), container, false)
        ButterKnife.bind(this, rootView)
        return rootView;
    }

    override fun onCreate(bundle: Bundle? ) {
        super.onCreate(bundle);
        inject();
        presenterFactory = presenterFactory
    }

    @CallSuper
    override fun onDestroyView() {
        ButterKnife.unbind(this)
        super.onDestroyView()
        
    }

    fun getBaseActivity() : BaseActivity<*> {
        return activity as BaseActivity<*>;
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context);
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<*>).getComponent())
    }

    protected abstract fun inject()

    protected abstract fun getLayoutResource(): Int

    

}
