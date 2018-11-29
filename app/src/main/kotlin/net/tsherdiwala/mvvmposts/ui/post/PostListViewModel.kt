package net.tsherdiwala.mvvmposts.ui.post

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.tsherdiwala.mvvmposts.R
import net.tsherdiwala.mvvmposts.model.Post
import net.tsherdiwala.mvvmposts.model.PostDao
import net.tsherdiwala.mvvmposts.network.PostApi

class PostListViewModel(private val postDao: PostDao, private val postApi: PostApi) : ViewModel() {


    var data: MutableLiveData<List<Post>> = MutableLiveData()


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
                .concatMap { dbPostList ->
                    if (dbPostList.isEmpty())
                        postApi.getPosts().concatMap { apiPostList ->
                            postDao.insertAll(*apiPostList.toTypedArray())
                            Observable.just(apiPostList)
                        }
                    else
                        Observable.just(dbPostList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        data.value = postList

    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }
}