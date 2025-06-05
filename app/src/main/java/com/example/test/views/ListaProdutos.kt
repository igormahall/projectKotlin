package com.example.test.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.models.Produto
import com.google.gson.Gson

@Composable
fun ListaProdutos(produtos: List<Produto>) {
    Column(modifier = Modifier.padding(32.dp)) {
        Text("Produtos em Promoção:", style = MaterialTheme.typography.titleMedium)
        produtos.filter { it.emPromocao }.forEach {
            Text("- ${it.nome} por R$%.2f".format(it.preco))
        }
        Spacer(modifier = Modifier.height(10.dp))
        val total = produtos.filter { it.emPromocao }.sumOf { it.preco }
        val media = produtos.filter { it.emPromocao }.map { it.preco }.average()
        val maisCaro = produtos.filter { it.emPromocao }.maxByOrNull { it.preco }

        Text("Valor Total: R$%.2f".format(total), fontWeight = FontWeight.Bold)
        Text("Valor da Média: R$%.2f".format(media))
        Text("Produto mais caro: ${maisCaro?.nome}, ${maisCaro?.preco}")
    }
}

val testeJsonProduto: String = """{"nome":"MrMusculo","preco":"19.98","emPromocao":true}"""
val gson = Gson()
val objetoFinal = gson.fromJson(testeJsonProduto, Produto::class.java)
//
//@Preview
//@Composable
//fun VerJson(){
//    Text("${ objetoFinal.nome } - ${ objetoFinal.preco } - ${ objetoFinal.emPromocao }")
//}