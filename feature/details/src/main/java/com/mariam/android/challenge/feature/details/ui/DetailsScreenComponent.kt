package com.mariam.android.challenge.feature.details.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mariam.android.challenge.feature.details.vm.DetailsScreenViewModel

@Composable
fun DetailsScreenComponent(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {
    Text("Details screen")
}