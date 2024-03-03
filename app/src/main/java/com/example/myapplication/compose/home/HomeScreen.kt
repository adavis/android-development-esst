package com.example.myapplication.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(R.drawable.olive_ranch_bg),
            modifier = Modifier.aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            stringResource(R.string.description_text),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            fontSize = 18f.sp,
            lineHeight = 28.sp
        )
        Image(
            painter = painterResource(R.drawable.olive_oils),
            modifier = Modifier.aspectRatio(1f / 1f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}
