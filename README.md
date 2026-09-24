````markdown
# Catálogo de Modelos de IA

Aplicativo Android desenvolvido em Kotlin com Jetpack Compose e Firebase Firestore para o cadastro e gerenciamento de modelos e arquiteturas de Inteligência Artificial.

O projeto foi desenvolvido como atividade acadêmica, seguindo o tema **“Catálogo de Modelos de IA: CRUD de Arquiteturas de Redes Neurais”**.

## Tema

O aplicativo apresenta um catálogo de arquiteturas e modelos de Inteligência Artificial, permitindo cadastrar, visualizar, editar e excluir informações.

Alguns exemplos de modelos que podem ser cadastrados:

- ResNet
- Inception
- BERT

## Objetivo

O objetivo do aplicativo é demonstrar, de forma prática, a utilização de um CRUD em uma aplicação Android, com os dados armazenados de forma persistente no Firebase Firestore.

## Tecnologias utilizadas

- Kotlin
- Jetpack Compose
- Android Studio
- Firebase Firestore
- Material 3
- Gradle

## Funcionalidades

- Cadastro de modelos de IA
- Listagem dos modelos cadastrados
- Edição de modelos
- Exclusão de modelos
- Persistência dos dados no Firebase Firestore
- Validação dos campos do formulário
- Tratamento básico de carregamento e erros

## Dados cadastrados

Cada modelo possui as seguintes informações:

| Campo | Descrição |
|---|---|
| Nome | Nome do modelo de IA |
| Arquitetura | Tipo ou estrutura da arquitetura |
| Descrição | Explicação sobre o modelo |
| Ano | Ano relacionado ao modelo |
| Aplicações | Principais áreas de utilização |

## Estrutura do Firebase Firestore

Os dados são armazenados na coleção:

`modelos_ia`

Cada documento da coleção possui os campos:

```text
nome
arquitetura
descricao
ano
aplicacoes
````

## Configuração do projeto

Para executar o projeto, é necessário configurar o Firebase.

### 1. Criar um projeto no Firebase

Crie um projeto no [Firebase](https://firebase.google.com/) e acesse o Firebase Console.

### 2. Adicionar o aplicativo Android

Adicione um aplicativo Android utilizando o seguinte package:

```text
com.beatriz.catalogoia
```

### 3. Baixar o arquivo do Firebase

Baixe o arquivo:

```text
google-services.json
```

Depois, coloque o arquivo dentro da pasta:

```text
app/google-services.json
```

### 4. Ativar o Cloud Firestore

No Firebase Console, acesse o **Cloud Firestore** e crie o banco de dados.

### 5. Sincronizar o projeto

Abra o projeto no Android Studio e aguarde a sincronização do Gradle.

### 6. Executar

Execute o aplicativo em um dispositivo físico ou emulador Android.

## Demonstração

### Aplicativo

Adicione aqui as imagens mostrando o funcionamento do aplicativo.

> 📷 **Imagem 1:** Tela principal com os modelos cadastrados.

> 📷 **Imagem 2:** Tela de cadastro de um novo modelo.

> 📷 **Imagem 3:** Tela de edição de um modelo.

> 📷 **Imagem 4:** Dados cadastrados no Firebase Firestore.

### Vídeo

No vídeo são demonstrados:

* Configuração e funcionamento do aplicativo;
* Cadastro de um modelo;
* Listagem dos modelos;
* Edição de um modelo;
* Exclusão de um modelo;
* Persistência dos dados no Firebase Firestore.

## Estrutura do projeto

```text
CatalogoIA
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.beatriz.catalogoia
│           │       ├── data
│           │       ├── ui
│           │       ├── viewmodel
│           │       └── MainActivity.kt
│           │
│           └── res
│
├── firestore.rules
├── README.md
└── google-services.json
```

## Autora

**Beatriz Galdino Torres**

Projeto desenvolvido para fins acadêmicos.
