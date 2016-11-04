package com.heirko.prim.ui.main

import com.heirko.prim.di.components.MainComponent
import com.heirko.prim.ui.base.BaseFragment
import com.heirko.prim.ui.base.EmptyPresenter
import com.heirko.prim.R


class MainFragment : BaseFragment<EmptyPresenter>() {

    override fun inject() {
        getComponent(MainComponent::class.java).inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_main
    }

    
}
