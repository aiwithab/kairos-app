package com.aiwithab.kairos.presentation.input

import com.aiwithab.kairos.domain.model.PlanDomain

/**
 * UI state for the input screen
 */
sealed class InputUiState {
    
    /**
     * Initial idle state
     */
    object Idle : InputUiState()
    
    /**
     * Loading state while generating plan
     */
    object Loading : InputUiState()
    
    /**
     * Success state with generated plan
     */
    data class Success(val plan: PlanDomain) : InputUiState()
    
    /**
     * Error state with error message
     */
    data class Error(val message: String) : InputUiState()
}
