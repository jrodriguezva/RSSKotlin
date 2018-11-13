package com.jrodiriguezva.rsskotlin.di.component

import android.app.Application
import com.jrodiriguezva.rsskotlin.AndroidApplication
import com.jrodiriguezva.rsskotlin.data.datasource.local.FeedDatabase
import com.jrodiriguezva.rsskotlin.di.module.ActivityBindingModule
import com.jrodiriguezva.rsskotlin.di.module.AppModule
import com.jrodiriguezva.rsskotlin.di.module.DaoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, DaoModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun dataBase(): FeedDatabase

    fun inject(app: AndroidApplication)
}