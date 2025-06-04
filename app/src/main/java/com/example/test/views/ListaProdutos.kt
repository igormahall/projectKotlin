package com.example.test.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.models.Produto

@Composable
fun ListaProdutos(produtos: List<Produto>) {
    Column(modifier = Modifier.padding(32.dp)) {
        Text("Produtos em Promoção:", style = MaterialTheme.typography.titleMedium)
        produtos.filter { it.emPromocao }.forEach {
            Text("- ${it.nome} por R$%.2f".format(it.preco))
        }
    }
}