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
import com.google.gson.Gson

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
//    val testeJsonProduto: String = """{"nome":"MrMusculo","preco":"19.98","emPromocao":true}"""
//    val gson = Gson()
//    val objetoFinal = gson.fromJson(testeJsonProduto, Produto::class.java)

    Text("${ com.example.test.views.objetoFinal.nome } - ${ com.example.test.views.objetoFinal.preco } - ${ com.example.test.views.objetoFinal.emPromocao }")
}