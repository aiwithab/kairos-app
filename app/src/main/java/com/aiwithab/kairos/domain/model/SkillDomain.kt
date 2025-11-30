package com.aiwithab.kairos.domain.model

/**
 * Domain model representing a skill
 *
 * @property skillName Name of the skill
 * @property totalDays Total days allocated
 * @property topics List of topics under this skill
 */
data class SkillDomain(
    val skillName: String,
    val totalDays: Int,
    val topics: List<TopicDomain>
) {
    /**
     * Get number of topics in this skill
     */
    fun getTopicCount(): Int = topics.size
    
    /**
     * Get high priority topics for this skill
     */
    fun getHighPriorityTopics(): List<TopicDomain> {
        return topics.filter { it.isHighPriority }
    }
    
    /**
     * Get bonus topics for this skill
     */
    fun getBonusTopics(): List<TopicDomain> {
        return topics.filter { it.isBonus }
    }
}
