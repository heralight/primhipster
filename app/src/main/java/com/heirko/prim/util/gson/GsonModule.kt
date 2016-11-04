package com.heirko.prim.util.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.joda.money.CurrencyUnit
import org.joda.money.Money


import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class GsonModule {

    @Provides
    @Singleton
    fun provideDefaultGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Money::class.java, MoneyTypeConverter())
        gsonBuilder.registerTypeAdapter(CurrencyUnit::class.java, CurrencyUnitTypeConverter()) 
        
        return gsonBuilder
    }

    @Provides
    @Singleton
    fun provideGson(gsonBuilder: GsonBuilder): Gson {
        return gsonBuilder.create()
    }

}
