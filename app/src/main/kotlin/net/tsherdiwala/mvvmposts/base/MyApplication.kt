package net.tsherdiwala.mvvmposts.base

import android.app.Application
import net.tsherdiwala.mvvmposts.injection.component.AppComponent
import net.tsherdiwala.mvvmposts.injection.component.DaggerAppComponent
import net.tsherdiwala.mvvmposts.injection.module.ApplicationModule
import net.tsherdiwala.mvvmposts.injection.module.NetworkModule


class MyApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule)
                .build()

    }


}
