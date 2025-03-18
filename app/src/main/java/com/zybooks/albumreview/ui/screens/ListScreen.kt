package com.zybooks.albumreview.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.albumreview.data.Review
import com.zybooks.albumreview.viewmodel.ListViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onReviewClick: (Review) -> Unit,
    onAddReviewClick: () -> Unit,
    onSettingsClick: () -> Unit,
    viewModel: ListViewModel = viewModel()
) {
    val reviews by viewModel.reviews.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Album Reviews") },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddReviewClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Review")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(reviews) { review ->
                ReviewCard(
                    review = review,
                    onReviewClick = onReviewClick,
                    onDeleteClick = { reviewToDelete ->
                        viewModel.deleteReview(reviewToDelete.id)
                    }
                )
            }
        }
    }
}

@Composable
fun ReviewCard(
    review: Review,
    onReviewClick: (Review) -> Unit,
    onDeleteClick: (Review) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onReviewClick(review) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = review.albumName, style = MaterialTheme.typography.titleMedium)
                Text(text = "Artist: ${review.artistName}")
                Text(text = "Rating: ${review.rating}/10")
            }
            IconButton(onClick = { onDeleteClick(review) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Review"
                )
            }
        }
    }
}
