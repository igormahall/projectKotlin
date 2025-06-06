package com.example.test.models

data class Produto(
    val nome: String,
    val preco: Double,
    val emPromocao: Boolean
)

//val json = """
//    {"chave1":"valor1",
//    "chave2":"valor2",
//    "chave3":"valor3",
//    "chave4":"valor4"
//    }
//    """.trimIndent()