package dev.matyaqubov.android_mvp.presenter.Main

import dev.matyaqubov.android_mvp.model.Post

interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
}