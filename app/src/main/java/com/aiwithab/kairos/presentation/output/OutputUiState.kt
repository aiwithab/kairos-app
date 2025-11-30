package com.aiwithab.kairos.presentation.output

import com.aiwithab.kairos.domain.model.PlanDomain

/**
 * UI state for the output screen
 */
data class OutputUiState(
    val plan: PlanDomain,
    val checkedTopics: Set<Int> = emptySet()
) {
    /**
     * Get the number of checked topics
     */
    val checkedCount: Int
        get() = checkedTopics.size
    
    /**
     * Get the total number of topics
     */
    val totalTopics: Int
        get() = plan.getTotalTopicCount()
    
    /**
     * Get the progress as a float between 0 and 1
     */
    val progress: Float
        get() = if (totalTopics > 0) {
            checkedCount.toFloat() / totalTopics.toFloat()
        } else {
            0f
        }
}
