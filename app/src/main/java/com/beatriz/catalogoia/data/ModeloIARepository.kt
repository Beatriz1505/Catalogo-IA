package com.beatriz.catalogoia.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ModeloIARepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("modelos_ia")

    suspend fun listar(): Result<List<ModeloIA>> = runCatching {
        collection
            .orderBy("nome")
            .get()
            .await()
            .documents
            .map { document ->
                ModeloIA(
                    id = document.id,
                    nome = document.getString("nome").orEmpty(),
                    arquitetura = document.getString("arquitetura").orEmpty(),
                    descricao = document.getString("descricao").orEmpty(),
                    ano = (document.getLong("ano") ?: 0L).toInt(),
                    aplicacoes = document.getString("aplicacoes").orEmpty()
                )
            }
    }

    suspend fun adicionar(modelo: ModeloIA): Result<Unit> = runCatching {
        val dados = mapOf(
            "nome" to modelo.nome.trim(),
            "arquitetura" to modelo.arquitetura.trim(),
            "descricao" to modelo.descricao.trim(),
            "ano" to modelo.ano,
            "aplicacoes" to modelo.aplicacoes.trim()
        )

        collection.add(dados).await()
        Unit
    }

    suspend fun atualizar(modelo: ModeloIA): Result<Unit> = runCatching {
        require(modelo.id.isNotBlank()) { "ID do modelo não informado." }

        val dados = mapOf(
            "nome" to modelo.nome.trim(),
            "arquitetura" to modelo.arquitetura.trim(),
            "descricao" to modelo.descricao.trim(),
            "ano" to modelo.ano,
            "aplicacoes" to modelo.aplicacoes.trim()
        )

        collection.document(modelo.id).set(dados).await()
        Unit
    }

    suspend fun excluir(id: String): Result<Unit> = runCatching {
        require(id.isNotBlank()) { "ID do modelo não informado." }
        collection.document(id).delete().await()
        Unit
    }
}
