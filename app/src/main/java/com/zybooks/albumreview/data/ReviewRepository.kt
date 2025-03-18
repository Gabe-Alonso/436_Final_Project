package com.zybooks.albumreview.data

import com.zybooks.albumreview.data.Review
import com.zybooks.albumreview.data.ReviewDao

import kotlinx.coroutines.flow.Flow

class ReviewRepository(private val reviewDao: ReviewDao) {

    fun getReviews(): Flow<List<Review>> {
        return reviewDao.getAllReviews()
    }

    suspend fun getReviewById(id: Int): Review? {
        return reviewDao.getReviewById(id)
    }

    suspend fun addReview(review: Review) {
        reviewDao.insertReview(review)
    }

    suspend fun updateReview(updatedReview: Review) {
        reviewDao.updateReview(updatedReview)
    }

    suspend fun deleteReview(id: Int) {
        reviewDao.deleteReviewById(id)
    }
}
