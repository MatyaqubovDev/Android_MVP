package dev.matyaqubov.android_mvp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import dev.matyaqubov.android_mvp.adapter.PosterAdapter
import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.R
import dev.matyaqubov.android_mvp.presenter.Main.MainPresenter
import dev.matyaqubov.android_mvp.utils.Utils
import dev.matyaqubov.android_mvp.views.MainView

class MainActivity : AppCompatActivity(), MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PosterAdapter
    lateinit var mainPresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainPresenter = MainPresenter(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        refreshAdapter(ArrayList())
        mainPresenter.apiPostList()
        var b_add = findViewById<FloatingActionButton>(R.id.f_button)
        b_add.setOnClickListener {
            callCreatePostActivity()
        }
        Utils.setItemTouchHelperRightToLeft(this, recyclerView)


    }

    private fun callCreatePostActivity() {
        val intent = Intent(this, CreatePostActivity::class.java)
        createLauncher.launch(intent)
    }

    fun callUpdateActivity(post: Post) {
        val intent = Intent(this, UpdatePostActivity::class.java)
        intent.putExtra("post", post)
        updateLauncher.launch(intent)
    }

    fun refreshAdapter(posters: ArrayList<Post>) {
        adapter = PosterAdapter(this, posters)
        recyclerView.adapter = adapter
    }

    var createLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            mainPresenter.apiPostList()

        }
    }

    var updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            mainPresenter.apiPostList()

        }
    }

    override fun onPostListSuccess(posts: ArrayList<Post>?) {
        refreshAdapter(posts!!)
    }

    override fun onPostListFailure(error: String) {
        TODO("Not yet implemented")
    }

    override fun onPostDeleteSuccess(post: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        TODO("Not yet implemented")
    }
}