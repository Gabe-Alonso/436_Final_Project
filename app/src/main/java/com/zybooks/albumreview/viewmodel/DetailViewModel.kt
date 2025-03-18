package com.zybooks.albumreview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.albumreview.data.Review
import com.zybooks.albumreview.data.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ReviewRepository) : ViewModel() {
    private val _review = MutableStateFlow<Review?>(null)
    val review: StateFlow<Review?> get() = _review

    fun loadReview(id: Int) {
        viewModelScope.launch {
            _review.value = repository.getReviewById(id)
        }
    }
}
