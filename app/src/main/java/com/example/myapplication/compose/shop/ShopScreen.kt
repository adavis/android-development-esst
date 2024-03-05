package com.example.myapplication.compose.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ProductsUiState
import com.example.myapplication.R
import com.example.myapplication.data.Product
import com.example.myapplication.ui.theme.AppTheme
import java.text.NumberFormat

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsUiState,
    onProductClick: (productId: Product) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                stringResource(R.string.shop_label).uppercase(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    stringResource(id = R.string.free_shipping_label).uppercase(),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp, horizontal = 4.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (uiState is ProductsUiState.Success) {
            uiState.products.forEach { product ->
                item {
                    ProductItem(
                        product = product,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onProductClick: (product: Product) -> Unit
) {
    ElevatedCard(
        onClick = { onProductClick(product) },
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageFile)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
            Text(
                product.name.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Text(
                stringResource(id = R.string.product_size_label, product.size),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                NumberFormat.getCurrencyInstance().format(product.price),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    AppTheme {
        ProductItem(
            product = Product(
                id = 7384,
                quantity = 59,
                name = "Constance Chavez",
                imageFile = "https://2873199.youcanlearnit.net/images/basil_bottle.webp",
                description = "atomorum",
                size = 22,
                price = 20.3
            ),
            onProductClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    val products =
        listOf(
            Product(
                id = 7384,
                quantity = 59,
                name = "Chavez",
                imageFile = "https://2873199.youcanlearnit.net/images/basil_bottle.webp",
                description = "atomorum",
                size = 22,
                price = 20.3
            )
        )

    AppTheme {
        ShopScreen(
            uiState = ProductsUiState.Success(products),
            onProductClick = {}
        )
    }
}
