package com.aiwithab.kairos.domain.repository

import com.aiwithab.kairos.domain.model.PlanDomain
import com.aiwithab.kairos.util.Result

/**
 * Repository interface for career plan operations
 * This defines the contract for data operations without implementation details
 */
interface PlanRepository {
    
    /**
     * Generate a career plan based on user inputs
     *
     * @param userId User identifier
     * @param category Job category
     * @param jobDescription Full job description
     * @param timeline Timeline in days
     * @return Result containing PlanDomain or error
     */
    suspend fun generatePlan(
        userId: String,
        category: String,
        jobDescription: String,
        timeline: String
    ): Result<PlanDomain>
}
