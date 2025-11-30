package com.aiwithab.kairos.presentation.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiwithab.kairos.domain.usecase.GeneratePlanUseCase
import com.aiwithab.kairos.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the input screen
 * Handles plan generation logic and UI state management
 *
 * @property generatePlanUseCase Use case for generating plans
 */
class InputViewModel(
    private val generatePlanUseCase: GeneratePlanUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<InputUiState>(InputUiState.Idle)
    val uiState: StateFlow<InputUiState> = _uiState.asStateFlow()
    
    /**
     * Generate a career plan based on user inputs
     */
    fun generatePlan(category: String, jobDescription: String, timeline: String) {
        viewModelScope.launch {
            _uiState.value = InputUiState.Loading
            
            when (val result = generatePlanUseCase(category, jobDescription, timeline)) {
                is Result.Success -> {
                    _uiState.value = InputUiState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = InputUiState.Error(
                        result.message ?: "An unknown error occurred"
                    )
                }
                is Result.Loading -> {
                    // Already in loading state
                }
            }
        }
    }
    
    /**
     * Reset the UI state to idle
     */
    fun resetState() {
        _uiState.value = InputUiState.Idle
    }
}
