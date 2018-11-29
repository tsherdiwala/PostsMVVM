package net.tsherdiwala.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import net.tsherdiwala.mvvmposts.model.Post

class PostViewModel {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody():MutableLiveData<String>{
        return postBody
    }
}