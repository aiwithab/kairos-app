package com.aiwithab.kairos.presentation.output

import androidx.lifecycle.ViewModel
import com.aiwithab.kairos.domain.model.PlanDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the output screen
 * Manages progress tracking and topic completion state
 */
class OutputViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow<OutputUiState?>(null)
    val uiState: StateFlow<OutputUiState?> = _uiState.asStateFlow()
    
    /**
     * Initialize the output screen with a plan
     */
    fun setPlan(plan: PlanDomain) {
        _uiState.value = OutputUiState(plan = plan)
    }
    
    /**
     * Toggle the checked state of a topic
     */
    fun toggleTopicChecked(topicIndex: Int) {
        _uiState.update { currentState ->
            currentState?.let { state ->
                val newCheckedTopics = if (topicIndex in state.checkedTopics) {
                    state.checkedTopics - topicIndex
                } else {
                    state.checkedTopics + topicIndex
                }
                state.copy(checkedTopics = newCheckedTopics)
            }
        }
    }
    
    /**
     * Check if a topic is checked
     */
    fun isTopicChecked(topicIndex: Int): Boolean {
        return _uiState.value?.checkedTopics?.contains(topicIndex) ?: false
    }
    
    /**
     * Reset all checked topics
     */
    fun resetProgress() {
        _uiState.update { it?.copy(checkedTopics = emptySet()) }
    }
}
