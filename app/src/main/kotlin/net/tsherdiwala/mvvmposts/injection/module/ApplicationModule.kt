package net.tsherdiwala.mvvmposts.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import net.tsherdiwala.mvvmposts.injection.ViewModelFactory
import net.tsherdiwala.mvvmposts.model.database.AppDatabase
import net.tsherdiwala.mvvmposts.network.PostApi
import javax.inject.Singleton


@Module
class ApplicationModule internal constructor(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    fun provideViewModelFactory(db: AppDatabase, postApi: PostApi) : ViewModelFactory{
        return ViewModelFactory(db, postApi)
    }
}