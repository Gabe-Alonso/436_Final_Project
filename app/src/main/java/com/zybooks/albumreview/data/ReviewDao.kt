package com.zybooks.albumreview.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews")
    fun getAllReviews(): Flow<List<Review>>

    @Query("SELECT * FROM reviews WHERE id = :id")
    suspend fun getReviewById(id: Int): Review?

    @Insert
    suspend fun insertReview(review: Review)

    @Update
    suspend fun updateReview(review: Review)

    @Query("DELETE FROM reviews WHERE id = :id")
    suspend fun deleteReviewById(id: Int)

}
