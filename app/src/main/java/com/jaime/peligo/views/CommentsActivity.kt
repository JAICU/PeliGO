package com.jaime.peligo.views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaime.peligo.R
import com.jaime.peligo.models.Comment
import com.jaime.peligo.viewmodels.CommentsViewModel

class CommentsActivity : AppCompatActivity() {

    private lateinit var rvComments: RecyclerView
    private lateinit var etMovieName: EditText
    private lateinit var rbRating: RatingBar
    private lateinit var etComment: EditText
    private lateinit var btnSend: Button
    private lateinit var adapter: CommentsAdapter
    private val viewModel: CommentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        rvComments = findViewById(R.id.rvComments)
        etMovieName = findViewById(R.id.etMovieName)
        rbRating = findViewById(R.id.rbRating)
        etComment = findViewById(R.id.etComment)
        btnSend = findViewById(R.id.btnSend)

        rvComments.layoutManager = LinearLayoutManager(this)
        adapter = CommentsAdapter()
        rvComments.adapter = adapter

        viewModel.commentsList.observe(this) { comments ->
            adapter.submitList(comments)
        }

        btnSend.setOnClickListener {
            val movieName = etMovieName.text.toString().trim()
            val rating = rbRating.rating
            val message = etComment.text.toString().trim()

            if (movieName.isNotEmpty() && message.isNotEmpty()) {
                viewModel.addComment(movieName, rating, message)
                etMovieName.text.clear()
                rbRating.rating = 0f
                etComment.text.clear()
            }
        }
    }
}
