package net.tsherdiwala.mvvmposts.base

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel(){



     init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {

        /*val component = (context.applicationContext as MyApplication).component


        when (this) {
            is PostListViewModel -> component.inject(this)
            is PostViewModel -> component.inject(this)
           // is ViewModelFactory -> injector.inject(this)
        }*/
    }
}