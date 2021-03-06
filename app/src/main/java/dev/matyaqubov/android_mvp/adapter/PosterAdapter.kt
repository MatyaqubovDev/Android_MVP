package dev.matyaqubov.android_mvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import dev.matyaqubov.android_mvp.R
import dev.matyaqubov.android_mvp.activity.MainActivity
import dev.matyaqubov.android_mvp.model.Post
import dev.matyaqubov.android_mvp.utils.Utils
import java.lang.ref.WeakReference

class PosterAdapter(var activity: MainActivity, var list: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onUpdate: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var post = list[position]
        if (holder is PostViewHolder) {
            holder.apply {
                tv_title.text = post.title
                tv_body.text = post.body
                ll_poster.setOnLongClickListener {
                    deletePostDialog(post)
                    false
                }
                b_update.setOnClickListener {
                    activity.callUpdateActivity(post)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.mainPresenter.apiPostDelete(post)
            }

            override fun onNegativeClick() {

            }
        })
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
        var tv_body = view.findViewById<TextView>(R.id.tv_body)
        var ll_poster = view.findViewById<LinearLayout>(R.id.ll_poster)
        var b_update = view.findViewById<Button>(R.id.b_update)
    }

}