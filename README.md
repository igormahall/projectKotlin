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
- comentário:
```kotlin
/* ... */
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
- map -> transforma cada item
```kotlin
val numeros = listOf(1,2,3,4,5)
val dobro = numeros.map { it * 2} // [2, 4, 6, 8, 10]
```
- sortedBy -> ordena com base em algo (nomes.sortedBy {it});
- sumOf -> soma com base em uma propriedade (produtos.sumOf {it.preco});

### Data class - simples e poderosa
- toString() -> representação em texto
- equals()
- copy()

### Lazy column: vai carregando à medida que for necessário
- items: pertence ao LazyColumn
- componentes: Text(), Button(), Image()
- remember: lembrar e salvar o texto pra depois

- Card: agrupa conteúdo relacionado
- Row: horizontal | Column: vertical
- LazyRow/LazyColumn
- Scaffold: estrutura básica de telas:
  - TopAppBar: barra do topo
  - BottomNavigation: barra da parte de baixo
  - FloatingActionButton
- Snackbar: notificação transitória que aparece na parte inferior da tela
---
## De volta ao projeto

1) Criar o tipo Produto (data class Produto)
   - Criar ClassProduto.kt em 'models'
```kotlin
data class Produto(
val nome: String,
val preco: Double,
val emPromocao: Boolean
)
```
2) Criar ListadeProdutos.kt dentro da pasta 'views'

3) MainActivity.kt
```kotlin
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
```

# Aula 05/06
Requisições simuladas e Manipulação de JSON
(ainda não vamos conectar com a api...só pegar o json)
```kotlin
val testeJsonProduto: String = """{"nome":"MrMusculo","preco":"19.98","emPromocao":true}"""
```
- Gson: faz a mudança entre Kotlin Object e JSON
- ir em 'app/build.gradle.kts' e adicionar no dependencies: 
```bash
implementation("com.google.code.gson:gson:2.10.1")
```
- Depois disso, precisa dar build -> sync gradle project (pode clicar com o direito no martelo > restart)
- Adicionar no models -> ClassProduto
```kotlin
val json = """
    {"chave1":"valor1",
    "chave2":"valor2",
    "chave3":"valor3",
    "chave4":"valor4"
    }
    """
    .trimIndent()
```
- Adicionar no views -> ListaProdutos
```kotlin
val testeJsonProduto: String = """{"nome":"MrMusculo","preco":"19.98","emPromocao":true}"""
val gson = Gson()
val objetoFinal = gson.fromJson(testeJsonProduto, Produto::class.java)
```

- Adicionar no MainAcitivity, dentro de greetings
```kotlin
Text("${ com.example.test.views.objetoFinal.nome } - ${ com.example.test.views.objetoFinal.preco } - ${ com.example.test.views.objetoFinal.emPromocao }")
```

## Mao na massa
1) services -> produtoService.kt
2) views -> ListaAPIprodutos.kt
3) atualizar Greetings na MainActivity

- Caminho: API -> JSON -> SERVICE (<->MODEL) -> VIEWS -> MainActivity
---

# Aula 06/06
Habilitando para receber API do Django
1) Vai no build.gradle.kts(module:app)
```bash
plugins {
    kotlin("plugin.serialization") version "1.9.10"
}
dependencies {
    implementation("io.ktor:ktor-client-core:3.0.0")
    implementation("io.ktor:ktor-client-android:3.0.0")
    implementation("io.ktor:ktor-client-logging:3.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")
    implementation("org.slf4j:slf4j-android:1.7.36")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.compose.foundation:foundation:1.5.0")
}
```
- Depois precisa: Rebuild (sync)

2) Criar apiClasses.kt (no models)
   - Precisa ter SaleGET e SalePOST, para poder trazer as informações de forma correta (no GET)
   - os modelos (data class) criados aqui são necessários para ler o json (precisa bater certinho)
   - fiz dois modelos pq Sale é uma tabela associativa (puxa dados de outras tabelas)

3) Criar ApiService (no services)
   - Aqui que vai usar o Ktor (HttpClient)

4) Criar apiClientView.kt (no views)
   - A primeira funçao é pro cliente individual
   - A segunda função "abraça" todos os clientes (usando uma lazycolumn)

5) Criar SwipePager.kt (no views)
   - Usando Swipe, para exibir as views rolando a tela para o lado
   - Criando com 4 páginas iguais no momento...depois iremos atualizar

6) Atualizar MainAcitivity.kt