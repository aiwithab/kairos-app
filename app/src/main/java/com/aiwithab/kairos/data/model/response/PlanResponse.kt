package com.aiwithab.kairos.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Response model containing the generated career plan
 *
 * @property skills List of skills to learn with associated topics
 */
data class PlanResponse(
    @SerializedName("skills")
    val skills: List<Skill>
)
