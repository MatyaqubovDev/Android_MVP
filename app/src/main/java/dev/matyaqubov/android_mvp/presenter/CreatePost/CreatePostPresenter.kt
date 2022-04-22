package dev.matyaqubov.android_mvp.presenter.CreatePost

import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.networking.RetrofitHttp
import dev.matyaqubov.android_mvp.views.CreatePostView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostPresenter(var createPostView: CreatePostView) : CreatePostPresenterImpl {
    override fun apiPostCreate(post: Post) {

        RetrofitHttp.postService.createPost(post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                createPostView.onPostCreateSuccess(post)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                createPostView.onPostCreateFailure(t.toString())
            }

        })
    }
}