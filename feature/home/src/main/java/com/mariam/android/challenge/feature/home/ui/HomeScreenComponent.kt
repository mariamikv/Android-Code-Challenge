package com.mariam.android.challenge.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.ui.theme.AndroidCodeChallengeTheme
import com.mariam.android.challenge.core.ui.theme.components.ContentTextComponent
import com.mariam.android.challenge.core.ui.theme.components.ErrorStateComponent
import com.mariam.android.challenge.core.ui.theme.models.UiState
import com.mariam.android.challenge.feature.home.ui.skeleton.HomeScreenSkeletonComponent
import com.mariam.android.challenge.feature.home.vm.HomeScreenViewModel

@Composable
fun HomeScreenComponent(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigationActionClick: (String, String) -> Unit,
) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AndroidCodeChallengeTheme.colors.background),
    ) {
        when {
            state.value.data != null -> {
                Content(
                    item = state.value.data,
                    onNavigationActionClick = onNavigationActionClick,
                )
            }
            state.value.uiState is UiState.Error -> {
                ErrorStateComponent(
                    modifier = Modifier.fillMaxSize(),
                    onRetryActionClick = {
                        viewModel.retryItems()
                    },
                )
            }
            state.value.uiState == UiState.Loading -> {
                HomeScreenSkeletonComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .padding(16.dp),
                )
            }
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    item: ItemStateModel?,
    onNavigationActionClick: (String, String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            item?.let {
                ItemComponent(
                    item = it,
                    onNavigationActionClick = onNavigationActionClick
                )
            }
        }
    }
}

@Composable
private fun ItemComponent(
    item: ItemStateModel,
    onNavigationActionClick: (String, String) -> Unit,
) {
    when (item) {
        is ItemStateModel.Text -> {
            ContentTextComponent(
                text = item.title,
                color = AndroidCodeChallengeTheme.colors.text,
                style = AndroidCodeChallengeTheme.typography.body_medium,
            )
        }

        is ItemStateModel.Image -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.src)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onNavigationActionClick(item.title, item.src)
                            },
                        ),
                    contentScale = ContentScale.Fit,
                )

                ContentTextComponent(
                    text = item.title,
                    color = AndroidCodeChallengeTheme.colors.text,
                    style = AndroidCodeChallengeTheme.typography.body_medium,
                )
            }
        }

        is ItemStateModel.Section -> {
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                ContentTextComponent(
                    text = item.title,
                    color = AndroidCodeChallengeTheme.colors.text,
                    style = AndroidCodeChallengeTheme.typography.body_text,
                )
                item.items.forEach { child ->
                    ItemComponent(
                        item = child,
                        onNavigationActionClick = onNavigationActionClick,
                    )
                }
            }
        }

        is ItemStateModel.Page -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                ContentTextComponent(
                    text = item.title,
                    color = AndroidCodeChallengeTheme.colors.text,
                    style = AndroidCodeChallengeTheme.typography.headline_1,
                )
                item.items.forEach { child ->
                    ItemComponent(
                        item = child,
                        onNavigationActionClick = onNavigationActionClick,
                    )
                }
            }
        }
    }
}
