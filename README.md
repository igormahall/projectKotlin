Notas da Aula
---
Kotlin

- Mais fácil que JAVA
- Kotlin Multiplatform (KMM) -> usa base de código compartilhada (similar ao flutter)
- No Android, roda nativamente (bytecode JVM)
-Jetpack Compose -> multiplataforma
  - Kotlin faz a lógica e o layout (de forma declarativa, nao desenho)

- Tipos de variáveis (exemplo):
	- val => não muda o valor
	- var => muda o valor
	- tipo List, listOf() => lista imutável
	- lista mutável => mutableListOf()
    - setOf => dados únicos
    - endereço: String? = null (pode ser nulo)
    - any: todos os tipos não nulos
    - unit: função sem retorno significativo
    - nothing: sem retorno

- fun => criar função
- column => layout que aparece direto na tela
- "$" => interpolation
- Maps: armazenam itens como pares chave-valor
	- mapa somente leitura: mapOf(); mapOf("apple" to 100, ...)
	  MutableMap<String, Int> => pode mudar de valor
- @Composable => cria interface
- @Preview => serve como ferramenta de teste (apenas no Android Studio)

- Controle de FLuxo e Classes com Interface Jetpack Compose
- data class: classe com dados simples
```kotlin
data class Pessoa(val nome: String, val idade: Int) {
	fun saudacao(): String {
		return "Olá, meu nome é $nome e tenho $idade anos."
	}
}
fun SaudacaoDaPessoa(pessoa: Pessoa) {
	Text(pessoa.saudacao())
}
```

- **Funções Lambda (->)**
  - funções que podem ser tratadas como valores, atribuídas a variáveis e passadas como parâmetros
```kotlin
val saudacao: (String) -> String = { nome -> "Olá, $nome!"}
println(saudacao("Pedro")) // Saída: Olá, Pedro!
```
- A variável saudação é do tipo String lambda String que retorna ...
---

**Primeiro Projeto**
- Criar novo projeto no IntelliJ
- New Project -> Android (procura o plugin no marketplace)
- Install SDK
- Selecionar: Empty Activity
- Adicionar dispositivo virtual
- Project Structure -> Selecionar SDK Android
- Settings -> Build -> Build Tools -> Gradle
	- Habilita "Download external annotation for dependencies"
	- Gradle JVM = Project SDK
- Se der erro de ícone no run, adicionar via Image Asset Studio;
- Habilitar refresh:  Editor -> Compose Live Edit -> "Push Edits Manually on Save (Ctrl+S)"

1) Criar FichaPessoal.kt
- Local: Test\app\src\main\java\com\example\test
```bash
package com.example.test.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FichaPessoal(nome: String, idade: Int, hobbies: List<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Olá, meu nome é $nome e tenho $idade anos.")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Meus hobbies:")
        hobbies.forEach { hobby ->
            Text("- $hobby")
        }
        Text("LocalDateTime.now()")
    }
}

@Preview
@Composable
fun PreviewFicha() {
    FichaPessoal("Maria", 28, listOf("Leitura", "Corrida", "Cinema"))
}
```
- Para atualizar no virtual device: run app (setinha dando volta)

2) Criar pastas dentro de "com.example.test"
- models, services, views
- Move o FichaPessoal.kt pra dentro da pasta 'views'
- Adiciona fun FichaPessoal dentro do Greetins, no MainActivity.kt

3) Na pasta model, criar ClassPessoa.kt
```kotlin
package com.example.test.models
data class Pessoa(val nome: String, val idade: Int) {
    fun apresentar() = "Olá, meu nome é $nome e tenho $idade anos."
    fun podeVotar() = if (idade >=16) "Pode votar" else "Não pode votar"
    fun faixaEtaria() = when {
        idade >= 60 -> "Idoso(a)"
        idade >= 18 -> "Adulto(a)"
        idade >= 12 -> "Jovem"
        else -> "Criança"
    }
}
```

4) Na pasta "views", criar PessoaCard.kt
```kotlin
package com.example.test.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.test.models.Pessoa

@Composable
fun PessoaCard(pessoa: Pessoa) {
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        .padding(16.dp)
    ) {
        Text(pessoa.apresentar(), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(pessoa.podeVotar())
        Text(pessoa.faixaEtaria())
    }
}
```
---
# Aula 04/06
## Coleções e Tratamento de Dados - Jetpack Compose
### List, filter, map, sumOf
- listOf -> cria a lista de itens
- filter -> filtra elementos com base em uma condição
```kotlin
val numeros = listOf(1,2,3,4,5)
val pares = numeros.filter{ it % 2 == 0 }//[2,4]
```