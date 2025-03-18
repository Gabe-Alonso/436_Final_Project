package com.zybooks.albumreview.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class Review(
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
   val albumName: String,
   val artistName: String,
   val rating: Int,
   val reviewText: String,
   val imageUri: String? = null
)
