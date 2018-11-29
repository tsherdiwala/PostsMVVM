package net.tsherdiwala.mvvmposts.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import net.tsherdiwala.mvvmposts.model.database.AppDatabase
import net.tsherdiwala.mvvmposts.network.PostApi
import net.tsherdiwala.mvvmposts.ui.post.PostListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val db: AppDatabase, private val postApi: PostApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao(), postApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}