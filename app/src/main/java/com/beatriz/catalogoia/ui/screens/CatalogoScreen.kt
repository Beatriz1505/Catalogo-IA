package com.beatriz.catalogoia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.beatriz.catalogoia.data.ModeloIA
import com.beatriz.catalogoia.viewmodel.ModeloIAViewModel

@Composable
fun CatalogoScreen(
    viewModel: ModeloIAViewModel,
    onAdicionar: () -> Unit,
    onEditar: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var modeloParaExcluir by remember { mutableStateOf<ModeloIA?>(null) }

    LaunchedEffect(state.mensagem, state.erro) {
        state.mensagem?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.limparMensagens()
        }

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
                        horizontal = 20.dp,
                        vertical = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Catálogo IA",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Arquiteturas e modelos de Inteligência Artificial",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onAdicionar,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar modelo"
                )
            }
        },

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        containerColor = MaterialTheme.colorScheme.background

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            HeaderCard(
                quantidade = state.modelos.size,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            )

            when {

                state.carregando && state.modelos.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.modelos.isEmpty() -> {
                    EmptyState(
                        onAdicionar = onAdicionar,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),

                        contentPadding = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 96.dp
                        ),

                        verticalArrangement = Arrangement.spacedBy(12.dp)

                    ) {

                        items(
                            items = state.modelos,
                            key = { it.id }
                        ) { modelo ->

                            ModeloCard(
                                modelo = modelo,

                                onEditar = {
                                    onEditar(modelo.id)
                                },

                                onExcluir = {
                                    modeloParaExcluir = modelo
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    modeloParaExcluir?.let { modelo ->

        AlertDialog(

            onDismissRequest = {
                modeloParaExcluir = null
            },

            title = {
                Text("Excluir modelo?")
            },

            text = {
                Text(
                    "O modelo \"${modelo.nome}\" será removido do Firestore. " +
                            "Essa ação não pode ser desfeita."
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        viewModel.excluir(modelo.id) {
                            modeloParaExcluir = null
                        }
                    }
                ) {
                    Text("Excluir")
                }
            },

            dismissButton = {

                OutlinedButton(
                    onClick = {
                        modeloParaExcluir = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Composable
private fun HeaderCard(
    quantidade: Int,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(

                    Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.85f
                            ),

                            MaterialTheme.colorScheme.secondary.copy(
                                alpha = 0.65f
                            )
                        )
                    )
                )

                .padding(20.dp)

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Memory,
                    contentDescription = null,
                    modifier = Modifier.size(42.dp)
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Column {

                    Text(
                        text = "Modelos cadastrados",

                        style = MaterialTheme.typography.titleMedium,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$quantidade modelo(s) no Firestore",

                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
private fun ModeloCard(
    modelo: ModeloIA,
    onEditar: () -> Unit,
    onExcluir: () -> Unit
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.Top

            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(

                        text = modelo.nome,

                        style = MaterialTheme.typography.titleLarge,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = "${modelo.arquitetura} • ${modelo.ano}",

                        color = MaterialTheme.colorScheme.secondary,

                        style = MaterialTheme.typography.labelLarge
                    )
                }

                IconButton(
                    onClick = onEditar
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }

                IconButton(
                    onClick = onExcluir
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = modelo.descricao,

                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = "Aplicações: ${modelo.aplicacoes}",

                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
private fun EmptyState(
    onAdicionar: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(

        modifier = modifier.padding(24.dp),

        contentAlignment = Alignment.Center

    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Icon(

                imageVector = Icons.Default.Memory,

                contentDescription = null,

                modifier = Modifier.size(56.dp),

                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(

                text = "Nenhum modelo cadastrado",

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(

                text = "Adicione o primeiro modelo para começar o catálogo.",

                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onAdicionar
            ) {

                Icon(

                    imageVector = Icons.Default.Add,

                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text("Adicionar modelo")
            }
        }
    }
}