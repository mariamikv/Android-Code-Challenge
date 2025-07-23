package com.mariam.android.challenge.feature.home.ui.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mariam.android.challenge.core.ui.theme.components.displayPlaceholder

@Composable
fun HomeScreenSkeletonComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Content(
            width = 150.dp,
            height = 32.dp,
            shape = CircleShape,
        )

        Content(
            width = 170.dp,
            height = 24.dp,
            shape = CircleShape,
        )

        Content(
            width = 200.dp,
            height = 16.dp,
            shape = CircleShape,
        )

        Content(
            width = 200.dp,
            height = 200.dp,
            shape = RoundedCornerShape(24.dp),
        )

        Content(
            width = 100.dp,
            height = 16.dp,
            shape = RoundedCornerShape(24.dp),
        )

        Content(
            width = 170.dp,
            height = 24.dp,
            shape = CircleShape,
        )

        Content(
            width = 150.dp,
            height = 16.dp,
            shape = CircleShape,
        )

        Content(
            width = 200.dp,
            height = 24.dp,
            shape = CircleShape,
        )

        Content(
            width = 200.dp,
            height = 200.dp,
            shape = RoundedCornerShape(24.dp),
        )

        Content(
            width = 170.dp,
            height = 24.dp,
            shape = CircleShape,
        )

        Content(
            width = 150.dp,
            height = 32.dp,
            shape = CircleShape,
        )

        Content(
            width = 150.dp,
            height = 16.dp,
            shape = CircleShape,
        )

        Content(
            width = 200.dp,
            height = 24.dp,
            shape = CircleShape,
        )
    }
}

@Composable
private fun Content(
    height: Dp,
    width: Dp,
    shape: Shape,
) {
    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .displayPlaceholder(
                visible = true,
                shape = shape,
            ),
    )
}