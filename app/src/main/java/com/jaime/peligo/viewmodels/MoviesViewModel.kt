package com.jaime.peligo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaime.peligo.core.Constants
import com.jaime.peligo.models.MovieModel
import com.jaime.peligo.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel:ViewModel() {
    private var movieslist= MutableLiveData<List<MovieModel>>()
    val moviesList:LiveData<List<MovieModel>> = movieslist

    fun getAllMovies(){
            viewModelScope.launch(Dispatchers.IO){
                val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
                withContext(Dispatchers.Main){
                    movieslist.value = response.body()!!.results.sortedBy { it.title }
                }
            }
    }

    fun getPopular(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieslist.value = response.body()!!.results.sortedByDescending { (it.popularity).toFloat() }
            }
        }
    }

    fun getTopRated(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieslist.value = response.body()!!.results.sortedByDescending { (it.rating).toFloat() }
            }
        }
    }
    fun getUpcoming(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                movieslist.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }


}