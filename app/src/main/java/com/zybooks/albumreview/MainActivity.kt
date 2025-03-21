package com.zybooks.albumreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.albumreview.ui.AlbumApp
import com.zybooks.albumreview.ui.theme.AlbumReviewTheme
import com.zybooks.albumreview.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         val settingsViewModel: SettingsViewModel = viewModel()
         val darkModeEnabled by settingsViewModel.darkModeEnabled.collectAsState(initial = false)

         AlbumReviewTheme(useDarkTheme = darkModeEnabled) {
            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
            ) {
               AlbumApp(settingsViewModel)
            }
         }
      }
   }
}

