package com.beatriz.catalogoia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.beatriz.catalogoia.data.ModeloIA
import com.beatriz.catalogoia.viewmodel.ModeloIAViewModel

@Composable
fun FormularioScreen(
    viewModel: ModeloIAViewModel,
    modeloId: String?,
    onVoltar: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val modeloExistente = modeloId?.let(viewModel::buscarModelo)

    var nome by remember(modeloExistente?.id) {
        mutableStateOf(modeloExistente?.nome.orEmpty())
    }

    var arquitetura by remember(modeloExistente?.id) {
        mutableStateOf(modeloExistente?.arquitetura.orEmpty())
    }

    var descricao by remember(modeloExistente?.id) {
        mutableStateOf(modeloExistente?.descricao.orEmpty())
    }

    var ano by remember(modeloExistente?.id) {
        mutableStateOf(
            if (modeloExistente?.ano != null && modeloExistente.ano > 0) {
                modeloExistente.ano.toString()
            } else {
                ""
            }
        )
    }

    var aplicacoes by remember(modeloExistente?.id) {
        mutableStateOf(modeloExistente?.aplicacoes.orEmpty())
    }

    var erroFormulario by remember {
        mutableStateOf<String?>(null)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(state.erro) {
        state.erro?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.limparMensagens()
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onVoltar
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(
                        text = if (modeloId == null) {
                            "Novo modelo"
                        } else {
                            "Editar modelo"
                        },
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = if (modeloId == null) {
                            "Cadastre uma arquitetura de IA"
                        } else {
                            "Atualize as informações do modelo"
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        },

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        containerColor = MaterialTheme.colorScheme.background

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = if (modeloId == null) {
                    "Cadastre uma arquitetura de Inteligência Artificial."
                } else {
                    "Atualize as informações do modelo."
                },
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = nome,

                onValueChange = {
                    nome = it
                    erroFormulario = null
                },

                label = {
                    Text("Nome do modelo")
                },

                placeholder = {
                    Text("Ex.: ResNet")
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth(),

                isError = erroFormulario?.contains(
                    "nome",
                    ignoreCase = true
                ) == true
            )

            OutlinedTextField(
                value = arquitetura,

                onValueChange = {
                    arquitetura = it
                    erroFormulario = null
                },

                label = {
                    Text("Arquitetura")
                },

                placeholder = {
                    Text("Ex.: Rede residual")
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth(),

                isError = erroFormulario?.contains(
                    "arquitetura",
                    ignoreCase = true
                ) == true
            )

            OutlinedTextField(
                value = descricao,

                onValueChange = {
                    descricao = it
                    erroFormulario = null
                },

                label = {
                    Text("Descrição")
                },

                placeholder = {
                    Text("Explique brevemente o modelo.")
                },

                modifier = Modifier.fillMaxWidth(),

                minLines = 4,

                isError = erroFormulario?.contains(
                    "descrição",
                    ignoreCase = true
                ) == true
            )

            OutlinedTextField(
                value = ano,

                onValueChange = {
                    if (
                        it.all { caractere ->
                            caractere.isDigit()
                        } &&
                        it.length <= 4
                    ) {
                        ano = it
                        erroFormulario = null
                    }
                },

                label = {
                    Text("Ano")
                },

                placeholder = {
                    Text("Ex.: 2015")
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = aplicacoes,

                onValueChange = {
                    aplicacoes = it
                    erroFormulario = null
                },

                label = {
                    Text("Aplicações")
                },

                placeholder = {
                    Text("Ex.: Visão computacional")
                },

                modifier = Modifier.fillMaxWidth(),

                minLines = 3,

                isError = erroFormulario?.contains(
                    "aplicação",
                    ignoreCase = true
                ) == true
            )

            erroFormulario?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {

                    val anoInt = ano.toIntOrNull()

                    erroFormulario = when {

                        nome.isBlank() ->
                            "Informe o nome do modelo."

                        arquitetura.isBlank() ->
                            "Informe a arquitetura."

                        descricao.isBlank() ->
                            "Informe uma descrição."

                        anoInt == null ||
                                anoInt !in 1900..2100 ->
                            "Informe um ano válido entre 1900 e 2100."

                        aplicacoes.isBlank() ->
                            "Informe pelo menos uma aplicação."

                        else ->
                            null
                    }

                    if (erroFormulario == null) {

                        viewModel.salvar(
                            modelo = ModeloIA(
                                id = modeloExistente?.id.orEmpty(),
                                nome = nome,
                                arquitetura = arquitetura,
                                descricao = descricao,
                                ano = anoInt!!,
                                aplicacoes = aplicacoes
                            ),
                            onSuccess = onVoltar
                        )
                    }
                },

                enabled = !state.salvando,

                modifier = Modifier.fillMaxWidth(),

                contentPadding = PaddingValues(
                    vertical = 14.dp
                )
            ) {

                if (state.salvando) {

                    CircularProgressIndicator(
                        modifier = Modifier.height(20.dp),
                        strokeWidth = 2.dp
                    )

                } else {

                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = if (modeloId == null) {
                            "Salvar modelo"
                        } else {
                            "Salvar alterações"
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}