package com.pjsoft.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pjsoft.fakestoreapp.components.ErrorState
import com.pjsoft.fakestoreapp.models.Product
import com.pjsoft.fakestoreapp.services.ProductService
import com.pjsoft.fakestoreapp.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(id: Int) {
    var product by remember { mutableStateOf<Product?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }


    LaunchedEffect(id) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = withContext(Dispatchers.IO) { service.getProductByid(id) }
            product = result
            loading = false
        } catch (e: Exception) {
            loading = false
            error = true
            Log.e("ProductDetail", e.toString())
        }
    }

    when {
        loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = EvergreenShadow)
            }
        }

        error -> {
            ErrorState(
                message = "Error loading product. Please try again.",
                onRetry = {
                    error = false
                    loading = true
                }
            )
        }

        else -> {
            product?.let { p ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(AlmondCream)
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = p.image,
                        contentDescription = p.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .clip(RoundedCornerShape(24.dp))
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = p.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = EvergreenShadow,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "$${p.price}",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = ForestTint,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = p.description,
                        style = MaterialTheme.typography.bodyMedium.copy(color = SoftBlack)
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = EvergreenShadow)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = AlmondCream,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Add to Cart", color = AlmondCream)
                    }
                }
            } ?: ErrorState(message = "Product not found.")
        }
    }
}