package dev.matyaqubov.android_mvp.views

import dev.matyaqubov.android_mvp.model.Post

interface CreatePostView {
    fun onPostCreateSuccess(post: Post?)
    fun onPostCreateFailure(error: String)


}