package com.aiwithab.kairos.data.model.request

import com.google.gson.annotations.SerializedName

/**
 * Request model for generating a career plan
 *
 * @property userId Unique identifier for the user
 * @property category Job category (e.g., "Android", "Backend", "Web3")
 * @property jobDescription Full job description text
 * @property timeline Number of days available for preparation
 */
data class PlanRequest(
    @SerializedName("user_id")
    val userId: String,
    
    @SerializedName("category")
    val category: String,
    
    @SerializedName("job_description")
    val jobDescription: String,
    
    @SerializedName("timeline")
    val timeline: String
)
