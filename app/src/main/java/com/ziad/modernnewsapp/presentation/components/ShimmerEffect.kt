package com.ziad.modernnewsapp.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// =========================================================================
// Modifier Extension untuk Menerapkan Shimmer
// =========================================================================
fun Modifier.shimmerEffect(): Modifier = composed {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "shimmerAnimation"
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateAnimation.value - 200, y = translateAnimation.value - 200),
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    )
}

// =========================================================================
// Shimmer Loading Item (untuk satu baris berita)
// =========================================================================
@Composable
fun ShimmerNewsItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        // Shimmer untuk gambar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Sesuaikan tinggi dengan gambar berita kamu
                .clip(RoundedCornerShape(12.dp))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Shimmer untuk judul
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f) // Lebih pendek dari full width
                .height(20.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Shimmer untuk deskripsi/tanggal
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f) // Lebih pendek lagi
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    }
}

// =========================================================================
// Shimmer List (untuk seluruh daftar berita)
// =========================================================================
@Composable
fun ShimmerNewsList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) { // Tampilkan 5 item shimmer saat loading
            ShimmerNewsItem()
        }
    }
}