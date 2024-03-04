package com.example.myapplication.compose.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.ColorDarkBlue

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(
            span = { GridItemSpan(2) },
        ) {
            Image(
                painter = painterResource(R.drawable.olive_field),
                modifier = Modifier
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
        item(
            span = { GridItemSpan(2) }
        ) {
            Text(
                stringResource(R.string.shop_label).uppercase(),
                modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = ColorDarkBlue,
                fontWeight = FontWeight.Bold
            )
        }
        (0..4).forEach {
            item(key = it) {
                Product()
            }
        }
    }
}

@Composable
fun Product() {
    ElevatedCard(
        onClick = { /*TODO*/ },
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bold_bottle),
                modifier = Modifier.aspectRatio(1f / 1f),
                contentScale = ContentScale.Inside,
                contentDescription = null
            )
            Text(
                "Bold".uppercase(),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "500 ml bottle",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
            Text(
                "$24.50",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    AppTheme {
        Product()
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    AppTheme {
        ShopScreen()
    }
}
