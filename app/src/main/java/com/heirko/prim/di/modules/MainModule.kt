package com.heirko.prim.di.modules

import dagger.Module
import dagger.Provides
import  com.heirko.prim.ui.main.MainActivity

@Module
public class MainModule(val activity: MainActivity) {

    @Provides
    fun provideActivity() : MainActivity{
        return activity
    }

}
