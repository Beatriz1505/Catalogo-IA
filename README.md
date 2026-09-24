# Catálogo de Modelos de IA

Aplicativo Android desenvolvido em Kotlin com Jetpack Compose e Firebase Firestore para o cadastro e gerenciamento de modelos e arquiteturas de Inteligência Artificial.

O projeto foi desenvolvido como atividade acadêmica, seguindo o tema **“Catálogo de Modelos de IA: CRUD de Arquiteturas de Redes Neurais”**.

## Tema

O aplicativo apresenta um catálogo de arquiteturas e modelos de Inteligência Artificial, permitindo cadastrar, visualizar, editar e excluir informações.

Alguns exemplos de modelos que podem ser cadastrados:

* ResNet
* Inception
* BERT

## Objetivo

O objetivo do aplicativo é demonstrar, de forma prática, a utilização de um CRUD em uma aplicação Android, com os dados armazenados de forma persistente no Firebase Firestore.

## Tecnologias utilizadas

* Kotlin
* Jetpack Compose
* Android Studio
* Firebase Firestore
* Material 3
* Gradle

## Funcionalidades

* Cadastro de modelos de IA
* Listagem dos modelos cadastrados
* Edição de modelos
* Exclusão de modelos
* Persistência dos dados no Firebase Firestore
* Validação dos campos do formulário
* Tratamento básico de carregamento e erros

## Dados cadastrados

Cada modelo possui as seguintes informações:

| Campo       | Descrição                        |
| ----------- | -------------------------------- |
| Nome        | Nome do modelo de IA             |
| Arquitetura | Tipo ou estrutura da arquitetura |
| Descrição   | Explicação sobre o modelo        |
| Ano         | Ano relacionado ao modelo        |
| Aplicações  | Principais áreas de utilização   |

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
```

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

<img width="360" height="800" alt="WhatsApp Image 2026-09-24 at 12 02 27 AM" src="https://github.com/user-attachments/assets/1d2588bd-6a53-4af5-9f94-a0d6c3aea6bb" />
<img width="360" height="800" alt="WhatsApp Image 2026-09-24 at 12 02 28 AM" src="https://github.com/user-attachments/assets/7d258cd9-1301-40c8-9e34-81341a9ffad7" />
<img width="360" height="800" alt="WhatsApp Image 2026-09-24 at 12 02 27 AM (1)" src="https://github.com/user-attachments/assets/c1ce52af-8979-45f9-96ef-ab0204116aeb" />
<img width="360" height="800" alt="WhatsApp Image 2026-09-24 at 12 02 28 AM (1)" src="https://github.com/user-attachments/assets/759fe2b8-ead7-4af1-ac99-adb0c51928c1" />
<img width="1440 " height="1024" alt="WhatsApp Image 2026-09-24 at 12 02 13 AM" src="https://github.com/user-attachments/assets/7b545612-9fba-435b-a09a-eeaec7a0e74f" />


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
