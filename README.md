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

- fun => criar função
- column => layout que aparece direto na tela
- "$" => interpolation
- Maps: armazenam itens como pares chave-valor
	- mapa somente leitura: mapOf(); mapOf("apple" to 100, ...)
	  MutableMap<String, Int> => pode mudar de valor
- @Composable => cria interface
- @Preview => serve como ferramenta de teste (apenas no Android Studio)

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