package com.mariam.android.challenge.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenComponent(onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(top = 24.dp)
    ) {
        Text("Home screen")
        Button(onClick = {onClick()}) {
            Text("navigate")
        }
    }
}