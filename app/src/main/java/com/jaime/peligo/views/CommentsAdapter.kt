package com.jaime.peligo.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaime.peligo.R
import com.jaime.peligo.models.Comment

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private var comments: List<Comment> = emptyList()

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMovieName: TextView = view.findViewById(R.id.tvMovieName)
        val rbRating: RatingBar = view.findViewById(R.id.rbRating)
        val tvMessage: TextView = view.findViewById(R.id.tvMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.tvMovieName.text = comment.movieName
        holder.rbRating.rating = comment.rating
        holder.tvMessage.text = comment.message
    }

    override fun getItemCount() = comments.size

    fun submitList(newComments: List<Comment>) {
        comments = newComments
        notifyDataSetChanged()
    }
}
