package dev.matyaqubov.android_mvp.presenter.CreatePost

import dev.matyaqubov.android_mvp.model.Post

interface CreatePostPresenterImpl {
    fun apiPostCreate(post: Post)
}