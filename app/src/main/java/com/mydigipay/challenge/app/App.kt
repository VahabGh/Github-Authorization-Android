package com.mydigipay.challenge.app

import android.app.Application
import androidx.preference.PreferenceManager
import com.mydigipay.challenge.network.di.accessTokenModule
import com.mydigipay.challenge.network.di.networkModule
import com.mydigipay.challenge.repository.token.TokenRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }

    val appModule = module {
        factory { TokenRepositoryImpl(get()) }
        single(named(APPLICATION_CONTEXT)) { applicationContext }
        single { PreferenceManager.getDefaultSharedPreferences(get()) }
    }

}