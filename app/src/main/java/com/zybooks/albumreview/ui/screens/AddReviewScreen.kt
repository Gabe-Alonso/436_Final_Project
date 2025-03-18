package com.zybooks.albumreview.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddReviewScreen(
    onReviewAdded: (String, String, Float, String) -> Unit,
    onCancel: () -> Unit
) {
    var albumName by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var rating by remember { mutableFloatStateOf(5f) } // Default rating
    var reviewText by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Add Album Review", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = albumName,
            onValueChange = { albumName = it },
            label = { Text("Album Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text("Artist Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Rating: ${rating.toInt()}/10")
        Slider(
            value = rating,
            onValueChange = { rating = it },
            valueRange = 1f..10f,
            steps = 3, // Allows for whole number ratings
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            label = { Text("Your Review") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
            Button(onClick = {
                if (albumName.isNotBlank() && artistName.isNotBlank() && reviewText.isNotBlank()) {
                    onReviewAdded(albumName, artistName, rating, reviewText)
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save Review")
            }
        }
    }
}
