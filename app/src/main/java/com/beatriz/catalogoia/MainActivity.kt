package com.beatriz.catalogoia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beatriz.catalogoia.ui.screens.CatalogoScreen
import com.beatriz.catalogoia.ui.screens.FormularioScreen
import com.beatriz.catalogoia.ui.theme.CatalogoIATheme
import com.beatriz.catalogoia.viewmodel.ModeloIAViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CatalogoIATheme {
                val navController = rememberNavController()
                val viewModel: ModeloIAViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "catalogo"
                ) {
                    composable("catalogo") {
                        CatalogoScreen(
                            viewModel = viewModel,
                            onAdicionar = { navController.navigate("cadastro") },
                            onEditar = { id -> navController.navigate("edicao/$id") }
                        )
                    }

                    composable("cadastro") {
                        FormularioScreen(
                            viewModel = viewModel,
                            modeloId = null,
                            onVoltar = { navController.popBackStack() }
                        )
                    }

                    composable("edicao/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        FormularioScreen(
                            viewModel = viewModel,
                            modeloId = id,
                            onVoltar = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
