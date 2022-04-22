package dev.matyaqubov.android_mvp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dev.matyaqubov.android_mvp.R
import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.presenter.UpdatePost.UpdatePostPresenter
import dev.matyaqubov.android_mvp.views.UpdatePostView

class UpdatePostActivity : AppCompatActivity(), UpdatePostView {
    lateinit var et_title: EditText
    lateinit var post: Post
    lateinit var et_body: EditText
    lateinit var updatePostPresenter: UpdatePostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        initViews()
    }

    private fun initViews() {
        updatePostPresenter = UpdatePostPresenter(this)
        et_body = findViewById(R.id.et_body)
        et_title = findViewById(R.id.et_title)
        setData()
        val b_cancel = findViewById<Button>(R.id.btn_cancel).also {
            it.setOnClickListener { finish() }
        }
        val b_update = findViewById<Button>(R.id.btn_update).also {
            it.setOnClickListener { updatePost() }
        }
    }

    private fun updatePost() {
        post.body = et_body.text.toString()
        post.title = et_title.text.toString()
        updatePostPresenter.apiPostUpdate(post)
    }

    private fun setData() {
        post = intent.getSerializableExtra("post") as Post
        et_title.setText(post.title)
        et_body.setText(post.body)
    }

    override fun onPostUpdateSuccess(post: Post?) {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onPostUpdateFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        finish()
    }
}