package net.tsherdiwala.mvvmposts.injection.component

import dagger.Component
import net.tsherdiwala.mvvmposts.injection.module.ApplicationModule
import net.tsherdiwala.mvvmposts.injection.module.NetworkModule
import net.tsherdiwala.mvvmposts.ui.post.PostListActivity
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class),(ApplicationModule::class)])
interface AppComponent {



    /**
     * inject required dependency into PostListActivity
     */
    fun inject(postListActivity: PostListActivity)

}