package com.aiwithab.kairos.presentation.input

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiwithab.kairos.presentation.components.LoadingIndicator

/**
 * Input screen for entering job details and generating a plan
 *
 * @param viewModel The input view model
 * @param onPlanGenerated Callback when plan is successfully generated
 */
@Composable
fun InputScreen(
    viewModel: InputViewModel,
    onPlanGenerated: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var category by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }
    var timeline by remember { mutableStateOf("") }
    
    // Handle success state
    LaunchedEffect(uiState) {
        if (uiState is InputUiState.Success) {
            onPlanGenerated()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "AI Interview Prep",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))
        
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category (Android, Backend, Web3...)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is InputUiState.Loading
        )
        
        Spacer(Modifier.height(12.dp))
        
        OutlinedTextField(
            value = jobDescription,
            onValueChange = { jobDescription = it },
            label = { Text("Paste Job Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            enabled = uiState !is InputUiState.Loading,
            maxLines = 8
        )
        
        Spacer(Modifier.height(12.dp))
        
        OutlinedTextField(
            value = timeline,
            onValueChange = { timeline = it },
            label = { Text("Timeline (Days)") },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is InputUiState.Loading
        )
        
        Spacer(Modifier.height(20.dp))
        
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.generatePlan(category, jobDescription, timeline)
            },
            enabled = uiState !is InputUiState.Loading &&
                    category.isNotBlank() &&
                    jobDescription.isNotBlank() &&
                    timeline.isNotBlank()
        ) {
            Text("Generate Plan")
        }
        
        Spacer(Modifier.height(20.dp))
        
        when (val state = uiState) {
            is InputUiState.Loading -> {
                LoadingIndicator(message = "Generating your personalized plan...")
            }
            is InputUiState.Error -> {
                Text(
                    text = "Error: ${state.message}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            else -> { /* Idle or Success */ }
        }
    }
}
