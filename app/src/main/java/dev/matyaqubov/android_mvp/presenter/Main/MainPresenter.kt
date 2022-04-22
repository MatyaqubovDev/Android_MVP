package dev.matyaqubov.android_mvp.presenter.Main

import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.networking.RetrofitHttp
import dev.matyaqubov.android_mvp.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var mainView: MainView) : MainPresenterImpl {
    override fun apiPostList() {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                mainView.onPostListSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                mainView.onPostListFailure(t.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.toString())
            }
        })
    }

}