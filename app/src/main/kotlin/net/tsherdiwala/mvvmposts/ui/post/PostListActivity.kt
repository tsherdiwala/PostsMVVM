package net.tsherdiwala.mvvmposts.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import net.tsherdiwala.mvvmposts.R
import net.tsherdiwala.mvvmposts.databinding.ActivityPostListBinding
import net.tsherdiwala.mvvmposts.injection.ViewModelFactory
import net.tsherdiwala.mvvmposts.base.MyApplication
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel
    private var errorSnackbar: Snackbar? = null

    val postListAdapter: PostListAdapter = PostListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as MyApplication).component.inject(this)

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.postList.adapter = postListAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostListViewModel::class.java)

        //ViewModelProviders.of(this).get(PostViewModel::class.java)

        //ViewModelProviders.of(this).get(PostListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.data.observe(this, Observer {

            if (it != null) {
                postListAdapter.updatePostList(it)
            }
        })

        binding.viewModel = viewModel

    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}