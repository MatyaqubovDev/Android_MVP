package dev.matyaqubov.android_mvp.views

import dev.matyaqubov.android_mvp.model.Post

interface UpdatePostView {
    fun onPostUpdateSuccess(post: Post?)
    fun onPostUpdateFailure(error: String)


}