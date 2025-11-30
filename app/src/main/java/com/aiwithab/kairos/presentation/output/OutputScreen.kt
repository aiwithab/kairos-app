package com.aiwithab.kairos.presentation.output

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiwithab.kairos.presentation.components.ProgressHeader
import com.aiwithab.kairos.presentation.components.TopicCard

/**
 * Output screen displaying the generated plan with progress tracking
 *
 * @param viewModel The output view model
 */
@Composable
fun OutputScreen(
    viewModel: OutputViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    uiState?.let { state ->
        val allTopics = state.plan.getAllTopics()
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Title
            Text(
                text = "Generated Interview Prep Plan",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(Modifier.height(20.dp))
            
            // Progress Header
            ProgressHeader(
                checkedCount = state.checkedCount,
                totalCount = state.totalTopics,
                progress = state.progress
            )
            
            Spacer(Modifier.height(24.dp))
            
            Text(
                text = "Topics:",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))
            
            // Topic Cards
            allTopics.forEachIndexed { index, topic ->
                TopicCard(
                    topic = topic,
                    isChecked = state.checkedTopics.contains(index),
                    onCheckedChange = { viewModel.toggleTopicChecked(index) }
                )
            }
            
            Spacer(Modifier.height(30.dp))
        }
    }
}
