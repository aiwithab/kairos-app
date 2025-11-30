package com.aiwithab.kairos.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Represents a learning topic within a skill
 *
 * @property topicName Name of the topic
 * @property studyMaterial Recommended study material or resources
 * @property timeline Time allocation for this topic
 * @property priority Priority level (High, Medium, Low)
 * @property bonus Whether this is a bonus/optional topic
 */
data class Topic(
    @SerializedName("topic_name")
    val topicName: String,
    
    @SerializedName("study_material")
    val studyMaterial: String,
    
    @SerializedName("timeline")
    val timeline: String,
    
    @SerializedName("priority")
    val priority: String,
    
    @SerializedName("bonus")
    val bonus: Boolean
)
