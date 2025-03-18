package com.zybooks.albumreview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zybooks.albumreview.data.Review
import com.zybooks.albumreview.data.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AddReviewViewModel(private val repository: ReviewRepository) : ViewModel() {
    private val _albumName = MutableStateFlow("")
    val albumName: StateFlow<String> get() = _albumName

    private val _artistName = MutableStateFlow("")
    val artistName: StateFlow<String> get() = _artistName

    private val _reviewText = MutableStateFlow("")
    val reviewText: StateFlow<String> get() = _reviewText

    private val _rating = MutableStateFlow(0)
    val rating: StateFlow<Int> get() = _rating

    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri: StateFlow<String?> get() = _imageUri

    fun setAlbumName(name: String) {
        _albumName.value = name
    }

    fun setArtistName(name: String) {
        _artistName.value = name
    }

    fun setReviewText(text: String) {
        _reviewText.value = text
    }

    fun setRating(value: Int) {
        _rating.value = value
    }

    fun setImageUri(uri: String?) {
        _imageUri.value = uri
    }

    fun saveReview() {
        viewModelScope.launch {
            val newReview = Review(
                albumName = _albumName.value,
                artistName = _artistName.value,
                reviewText = _reviewText.value,
                rating = _rating.value,
                imageUri = _imageUri.value
            )
            repository.addReview(newReview)
        }
    }


}
