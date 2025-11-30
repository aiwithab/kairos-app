package com.aiwithab.kairos.data.repository

import com.aiwithab.kairos.data.model.request.PlanRequest
import com.aiwithab.kairos.data.model.response.PlanResponse
import com.aiwithab.kairos.data.model.response.Skill
import com.aiwithab.kairos.data.model.response.Topic
import com.aiwithab.kairos.data.remote.ApiService
import com.aiwithab.kairos.domain.model.PlanDomain
import com.aiwithab.kairos.domain.model.SkillDomain
import com.aiwithab.kairos.domain.model.TopicDomain
import com.aiwithab.kairos.domain.repository.PlanRepository
import com.aiwithab.kairos.util.Result

/**
 * Implementation of PlanRepository
 * Handles API communication and data transformation
 *
 * @property apiService The API service for network calls
 */
class PlanRepositoryImpl(
    private val apiService: ApiService
) : PlanRepository {
    
    override suspend fun generatePlan(
        userId: String,
        category: String,
        jobDescription: String,
        timeline: String
    ): Result<PlanDomain> {
        return try {
            val request = PlanRequest(
                userId = userId,
                category = category,
                jobDescription = jobDescription,
                timeline = timeline
            )
            
            val response = apiService.generatePlan(request)
            val domainModel = response.toDomain()
            
            Result.Success(domainModel)
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "Failed to generate plan"
            )
        }
    }
    
    /**
     * Convert data model to domain model
     */
    private fun PlanResponse.toDomain(): PlanDomain {
        return PlanDomain(
            skills = this.skills.map { it.toDomain() }
        )
    }
    
    private fun Skill.toDomain(): SkillDomain {
        return SkillDomain(
            skillName = this.skillName,
            totalDays = this.totalDays,
            topics = this.topics.map { it.toDomain() }
        )
    }
    
    private fun Topic.toDomain(): TopicDomain {
        return TopicDomain(
            topicName = this.topicName,
            studyMaterial = this.studyMaterial,
            timeline = this.timeline,
            priority = this.priority,
            isBonus = this.bonus
        )
    }
}
