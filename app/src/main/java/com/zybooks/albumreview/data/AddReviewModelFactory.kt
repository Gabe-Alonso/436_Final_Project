package com.zybooks.albumreview.data


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zybooks.albumreview.viewmodel.AddReviewViewModel

class AddReviewViewModelFactory(private val repository: ReviewRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddReviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddReviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
