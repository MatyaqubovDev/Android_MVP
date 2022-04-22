package dev.matyaqubov.android_mvp.presenter.UpdatePost

import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.networking.RetrofitHttp
import dev.matyaqubov.android_mvp.views.CreatePostView
import dev.matyaqubov.android_mvp.views.UpdatePostView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePostPresenter(var updatePostView: UpdatePostView) : UpdatePostPresenterImpl {
    override fun apiPostUpdate(post: Post) {

        RetrofitHttp.postService.updatePost(post.id, post)!!.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                updatePostView.onPostUpdateSuccess(post)
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                updatePostView.onPostUpdateFailure(t.toString())
            }

        })
    }
}