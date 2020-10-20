package com.inovaeg.androidtrainingpharos2020.facebooktTask

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inovaeg.androidtrainingpharos2020.Movie
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.facebooktTask.models.Post

class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var postImage : ImageView = itemView.findViewById(R.id.post_image_view)
    var postText : TextView = itemView.findViewById(R.id.post_text_view)

    fun bind(post : Post) {
        if(post.postImage != null) {
            postImage.setImageURI(post.postImage)
            postText.text = post.postText
            postImage.visibility = View.VISIBLE
        } else {
            postImage.setImageResource(R.drawable.scarlett_johansen)
            postText.text = post.postText
        }

    }
}