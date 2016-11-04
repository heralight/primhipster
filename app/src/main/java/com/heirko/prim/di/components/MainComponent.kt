package com.heirko.prim.di.components

import dagger.Component
import com.heirko.prim.di.ActivityScope
import com.heirko.prim.di.modules.MainModule
import com.heirko.prim.ui.main.MainActivity
import com.heirko.prim.ui.main.MainFragment

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {

  fun inject(activity: MainActivity)
  fun inject(fragment: MainFragment)

}

