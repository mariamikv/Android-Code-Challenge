package com.mariam.android.challenge.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mariam.android.challenge.core.ui.R

val ROBOTO_BOLD = FontFamily(
    Font(R.font.roboto_bold),
)
val ROBOTO_LIGHT = FontFamily(
    Font(R.font.roboto_light),
)
val ROBOTO_MEDIUM = FontFamily(
    Font(R.font.roboto_medium),
)

val BODY_MEDIUM: TextStyle = TextStyle(
    fontFamily = ROBOTO_LIGHT,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 17.38.sp,
)

val BODY_TEXT: TextStyle = TextStyle(
    fontFamily = ROBOTO_MEDIUM,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    lineHeight = 23.17.sp,
)

val HEADLINE_1: TextStyle = TextStyle(
    fontFamily = ROBOTO_BOLD,
    fontWeight = FontWeight.W700,
    fontSize = 24.sp,
    lineHeight = 34.75.sp,
)

data class AppTypography(
    val headline_1: TextStyle = HEADLINE_1,
    val body_text: TextStyle = BODY_TEXT,
    val body_medium: TextStyle = BODY_MEDIUM,
)
