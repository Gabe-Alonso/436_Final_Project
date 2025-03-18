package com.zybooks.albumreview.data

import com.zybooks.albumreview.data.Review

object ReviewDataSource {
   val reviewList = listOf(
      Review(1, "Alligator Bites Never Heal", "Doechii", 9, "Best rap album of 2024"),
      Review(2, "To Pimp A Butterfly", "Kendrick Lamar", 10, "Masterpiece of 2015"),
      Review(3, "IGOR", "Tyler, the Creator", 10, "A genre-bending album."),
      Review(4, "Inedito", "Antonio Carlos Jobim", 9, "Beautiful Bossa Nova.")
   )
}