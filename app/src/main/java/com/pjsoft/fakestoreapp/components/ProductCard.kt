package com.pjsoft.fakestoreapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pjsoft.fakestoreapp.models.Product
import com.pjsoft.fakestoreapp.models.Rating
import com.pjsoft.fakestoreapp.ui.theme.*

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MistyBeige),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = EvergreenShadow,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = ForestTint
                    )
                )
            }

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = EvergreenShadow,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview(){
    val testProduct = Product(
        id = 1,
        title = "Camiseta de prueba",
        price = 19.99,
        description = "Camiseta cómoda y de alta calidad.",
        category = "Ropa",
        image = "https://ejemplo.com/camiseta.png",
        rating = Rating(rate = 4.5, count = 120)

)
    FakeStoreAppTheme {
        ProductCard(
            product = testProduct,
            onClick = {}
        )
    }
}


