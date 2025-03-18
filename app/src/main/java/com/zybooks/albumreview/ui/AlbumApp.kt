package com.zybooks.albumreview.ui

import DetailScreen
import SettingsScreen
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.zybooks.albumreview.data.Review
import com.zybooks.albumreview.ui.screens.ListScreen
import com.zybooks.albumreview.ui.screens.AddReviewScreen
import com.zybooks.albumreview.viewmodel.ListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.albumreview.data.AddReviewViewModelFactory
import com.zybooks.albumreview.data.AppDatabase
import com.zybooks.albumreview.data.ReviewRepository
import com.zybooks.albumreview.viewmodel.AddReviewViewModel
import com.zybooks.albumreview.viewmodel.SettingsViewModel

@Composable
fun AlbumApp(settingsViewModel: SettingsViewModel) {
    val navController = rememberNavController()
    var selectedReview by remember { mutableStateOf<Review?>(null) }

    NavHost(navController, startDestination = "list") {
        composable("list") {
            val listViewModel: ListViewModel = viewModel()
            ListScreen(
                onReviewClick = { review ->
                    selectedReview = review
                    navController.navigate("detail")
                },
                onAddReviewClick = { navController.navigate("addReview") },
                onSettingsClick = { navController.navigate("settings") },
                viewModel = listViewModel
            )
        }
        composable("detail") {
            selectedReview?.let { review ->
                DetailScreen(review, onBack = { navController.popBackStack() })
            }
        }
        composable("addReview") {
            val context = LocalContext.current
            val database = AppDatabase.getDatabase(context)
            val repository = ReviewRepository(database.reviewDao())
            val addReviewViewModel: AddReviewViewModel = viewModel(
                factory = AddReviewViewModelFactory(repository)
            )
            AddReviewScreen(
                onReviewAdded = { albumName, artistName, rating, reviewText ->
                    addReviewViewModel.setAlbumName(albumName)
                    addReviewViewModel.setArtistName(artistName)
                    addReviewViewModel.setRating(rating.toInt())
                    addReviewViewModel.setReviewText(reviewText)

                    addReviewViewModel.saveReview()

                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(viewModel = settingsViewModel, onBack = { navController.popBackStack() })
        }
    }
}
