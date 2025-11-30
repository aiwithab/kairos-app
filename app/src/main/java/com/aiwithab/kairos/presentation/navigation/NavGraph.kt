package com.aiwithab.kairos.presentation.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aiwithab.kairos.data.remote.NetworkModule
import com.aiwithab.kairos.data.repository.PlanRepositoryImpl
import com.aiwithab.kairos.domain.usecase.GeneratePlanUseCase
import com.aiwithab.kairos.presentation.input.InputScreen
import com.aiwithab.kairos.presentation.input.InputUiState
import com.aiwithab.kairos.presentation.input.InputViewModel
import com.aiwithab.kairos.presentation.output.OutputScreen
import com.aiwithab.kairos.presentation.output.OutputViewModel

/**
 * Navigation routes
 */
object Routes {
    const val INPUT = "input"
    const val OUTPUT = "output"
}

/**
 * Main navigation graph for the app
 */
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    // Create dependencies
    val repository = remember { PlanRepositoryImpl(NetworkModule.apiService) }
    val useCase = remember { GeneratePlanUseCase(repository) }
    val inputViewModel = remember { InputViewModel(useCase) }
    val outputViewModel = remember { OutputViewModel() }
    
    NavHost(
        navController = navController,
        startDestination = Routes.INPUT
    ) {
        composable(Routes.INPUT) {
            val uiState by inputViewModel.uiState.collectAsState()
            
            // Navigate to output when plan is generated
            LaunchedEffect(uiState) {
                if (uiState is InputUiState.Success) {
                    val plan = (uiState as InputUiState.Success).plan
                    outputViewModel.setPlan(plan)
                    navController.navigate(Routes.OUTPUT) {
                        // Don't add to back stack to prevent going back to loading state
                        popUpTo(Routes.INPUT) { inclusive = false }
                    }
                }
            }
            
            InputScreen(
                viewModel = inputViewModel,
                onPlanGenerated = { /* Navigation handled by LaunchedEffect */ }
            )
        }
        
        composable(Routes.OUTPUT) {
            OutputScreen(viewModel = outputViewModel)
        }
    }
}
