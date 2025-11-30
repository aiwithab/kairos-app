package com.aiwithab.kairos.domain.model

/**
 * Domain model representing a complete career plan
 * This is UI-agnostic and contains business logic
 *
 * @property skills List of skills in the plan
 */
data class PlanDomain(
    val skills: List<SkillDomain>
) {
    /**
     * Get all topics across all skills
     */
    fun getAllTopics(): List<TopicDomain> {
        return skills.flatMap { it.topics }
    }
    
    /**
     * Get total number of topics
     */
    fun getTotalTopicCount(): Int {
        return getAllTopics().size
    }
    
    /**
     * Get total days for the entire plan
     */
    fun getTotalDays(): Int {
        return skills.sumOf { it.totalDays }
    }
    
    /**
     * Get high priority topics
     */
    fun getHighPriorityTopics(): List<TopicDomain> {
        return getAllTopics().filter { it.isHighPriority }
    }
}
