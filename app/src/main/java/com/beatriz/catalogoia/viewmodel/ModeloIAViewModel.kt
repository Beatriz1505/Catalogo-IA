package com.beatriz.catalogoia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatriz.catalogoia.data.ModeloIA
import com.beatriz.catalogoia.data.ModeloIARepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ModeloIAUiState(
    val modelos: List<ModeloIA> = emptyList(),
    val carregando: Boolean = false,
    val salvando: Boolean = false,
    val mensagem: String? = null,
    val erro: String? = null
)

class ModeloIAViewModel(
    private val repository: ModeloIARepository = ModeloIARepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ModeloIAUiState())
    val uiState: StateFlow<ModeloIAUiState> = _uiState.asStateFlow()

    init {
        carregarModelos()
    }

    fun carregarModelos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(carregando = true, erro = null)

            repository.listar()
                .onSuccess { modelos ->
                    _uiState.value = _uiState.value.copy(
                        modelos = modelos,
                        carregando = false
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        carregando = false,
                        erro = exception.message ?: "Não foi possível carregar os modelos."
                    )
                }
        }
    }

    fun buscarModelo(id: String): ModeloIA? {
        return _uiState.value.modelos.firstOrNull { it.id == id }
    }

    fun salvar(
        modelo: ModeloIA,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                salvando = true,
                erro = null,
                mensagem = null
            )

            val resultado = if (modelo.id.isBlank()) {
                repository.adicionar(modelo)
            } else {
                repository.atualizar(modelo)
            }

            resultado
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        salvando = false,
                        mensagem = if (modelo.id.isBlank()) {
                            "Modelo cadastrado com sucesso."
                        } else {
                            "Modelo atualizado com sucesso."
                        }
                    )
                    carregarModelos()
                    onSuccess()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        salvando = false,
                        erro = exception.message ?: "Não foi possível salvar o modelo."
                    )
                }
        }
    }

    fun excluir(
        id: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                salvando = true,
                erro = null
            )

            repository.excluir(id)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        salvando = false,
                        mensagem = "Modelo excluído com sucesso."
                    )
                    carregarModelos()
                    onSuccess()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        salvando = false,
                        erro = exception.message ?: "Não foi possível excluir o modelo."
                    )
                }
        }
    }

    fun limparMensagens() {
        _uiState.value = _uiState.value.copy(
            mensagem = null,
            erro = null
        )
    }
}
