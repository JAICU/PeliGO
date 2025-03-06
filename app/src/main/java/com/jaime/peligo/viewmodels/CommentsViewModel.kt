package com.jaime.peligo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.jaime.peligo.models.Comment

class CommentsViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _commentsList = MutableLiveData<List<Comment>>()
    val commentsList: LiveData<List<Comment>> = _commentsList

    init {
        loadComments()
    }

    fun addComment(movieName: String, rating: Float, message: String) {
        val comment = hashMapOf(
            "movieName" to movieName,
            "rating" to rating,
            "message" to message
        )

        db.collection("comments").add(comment)
    }

    private fun loadComments() {
        db.collection("comments")
            .orderBy("movieName") // Puedes cambiar el orden
            .addSnapshotListener { value, _ ->
                val comments = value?.documents?.map { doc ->
                    Comment(
                        movieName = doc.getString("movieName") ?: "",
                        rating = doc.getDouble("rating")?.toFloat() ?: 0f,
                        message = doc.getString("message") ?: ""
                    )
                } ?: emptyList()
                _commentsList.postValue(comments)
            }
    }
}
