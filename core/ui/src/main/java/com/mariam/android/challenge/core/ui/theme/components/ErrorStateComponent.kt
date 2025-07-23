package com.mariam.android.challenge.core.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mariam.android.challenge.core.ui.R
import com.mariam.android.challenge.core.ui.theme.AndroidCodeChallengeTheme

@Composable
fun ErrorStateComponent(
    modifier: Modifier = Modifier,
    onRetryActionClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContentTextComponent(
            text = stringResource(id = R.string.error_state_title),
            color = AndroidCodeChallengeTheme.colors.text,
            style = AndroidCodeChallengeTheme.typography.headline_1,
        )

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = onRetryActionClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            )
        ) {
            ContentTextComponent(
                text = stringResource(id = R.string.retry_button),
                color = AndroidCodeChallengeTheme.colors.text,
                style = AndroidCodeChallengeTheme.typography.body_medium,
            )
        }
    }
}