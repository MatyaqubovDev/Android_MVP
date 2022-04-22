package dev.matyaqubov.android_mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import dev.matyaqubov.android_mvp.R
import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.presenter.CreatePost.CreatePostPresenter
import dev.matyaqubov.android_mvp.views.CreatePostView

class CreatePostActivity : AppCompatActivity(), CreatePostView {
    lateinit var createPostPresenter: CreatePostPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initViews()
    }

    private fun initViews() {
        createPostPresenter = CreatePostPresenter(this)
        val et_title = findViewById<EditText>(R.id.et_title)
        var et_body = findViewById<EditText>(R.id.et_body)
        var b_cancel = findViewById<Button>(R.id.btn_cancel)
        var b_post = findViewById<Button>(R.id.btn_post)
        b_cancel.setOnClickListener {
            finish()
        }
        b_post.setOnClickListener {
            var title = et_title.text.toString()
            var body = et_body.text.toString()
            var post = Post(1, 2, title, body)
            createPostPresenter.apiPostCreate(post)
        }
    }

    override fun onPostCreateSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onPostCreateFailure(error: String) {
        finish()
    }
}