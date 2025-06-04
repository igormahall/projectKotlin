package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.models.Pessoa
import com.example.test.models.Produto
import com.example.test.ui.theme.TestTheme
import com.example.test.views.FichaPessoal
import com.example.test.views.ListaProdutos
import com.example.test.views.PessoaCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val produtos = listOf(
        Produto("Arroz", 8.0, false),
        Produto("Feijão", 10.5, true),
        Produto("Café", 15.1, true),
        Produto("Leite", 9.99, true),
        Produto("Azeite", 89.99, false),
        Produto("Queijo", 12.87, false),
        Produto("Batata Escovada", 5.99, true)
    )

    ListaProdutos(produtos)
}