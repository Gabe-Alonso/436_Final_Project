package com.zybooks.albumreview.viewmodel

import androidx.lifecycle.viewModelScope
import com.zybooks.albumreview.data.Review
import kotlinx.coroutines.launch

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.asLiveData
import com.zybooks.albumreview.data.AppDatabase
import com.zybooks.albumreview.data.ReviewRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val reviewDao = database.reviewDao()

    private val repository = ReviewRepository(reviewDao)

    val reviews: LiveData<List<Review>> = repository.getReviews().asLiveData()

    fun addReview(review: Review) {
        viewModelScope.launch {
            repository.addReview(review)
        }
    }

    fun updateReview(review: Review) {
        viewModelScope.launch {
            repository.updateReview(review)
        }
    }

    fun deleteReview(id: Int) {
        viewModelScope.launch {
            repository.deleteReview(id)
        }
    }
}
