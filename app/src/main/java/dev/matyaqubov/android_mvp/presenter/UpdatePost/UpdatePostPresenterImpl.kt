package dev.matyaqubov.android_mvp.presenter.UpdatePost

import dev.matyaqubov.android_mvp.model.Post

interface UpdatePostPresenterImpl {
    fun apiPostUpdate(post: Post)
}