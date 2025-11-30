package com.aiwithab.kairos.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Represents a skill to be learned
 *
 * @property skillName Name of the skill
 * @property totalDays Total days allocated for this skill
 * @property topics List of topics under this skill
 */
data class Skill(
    @SerializedName("skill_name")
    val skillName: String,
    
    @SerializedName("total_days")
    val totalDays: Int,
    
    @SerializedName("topics")
    val topics: List<Topic>
)
