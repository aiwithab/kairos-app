package com.aiwithab.kairos.domain.model

import com.aiwithab.kairos.util.Constants

/**
 * Domain model representing a learning topic
 *
 * @property topicName Name of the topic
 * @property studyMaterial Recommended study material
 * @property timeline Time allocation
 * @property priority Priority level
 * @property isBonus Whether this is a bonus topic
 */
data class TopicDomain(
    val topicName: String,
    val studyMaterial: String,
    val timeline: String,
    val priority: String,
    val isBonus: Boolean
) {
    /**
     * Check if this is a high priority topic
     */
    val isHighPriority: Boolean
        get() = priority.equals(Constants.PRIORITY_HIGH, ignoreCase = true)
    
    /**
     * Check if this is a medium priority topic
     */
    val isMediumPriority: Boolean
        get() = priority.equals(Constants.PRIORITY_MEDIUM, ignoreCase = true)
    
    /**
     * Check if this is a low priority topic
     */
    val isLowPriority: Boolean
        get() = priority.equals(Constants.PRIORITY_LOW, ignoreCase = true)
    
    /**
     * Get priority as an integer for sorting (High=3, Medium=2, Low=1)
     */
    fun getPriorityValue(): Int {
        return when {
            isHighPriority -> 3
            isMediumPriority -> 2
            isLowPriority -> 1
            else -> 0
        }
    }
}
