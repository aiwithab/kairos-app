package com.aiwithab.kairos.domain.usecase

import com.aiwithab.kairos.domain.model.PlanDomain
import com.aiwithab.kairos.domain.repository.PlanRepository
import com.aiwithab.kairos.util.Constants
import com.aiwithab.kairos.util.Result
import com.aiwithab.kairos.util.isValidCategory
import com.aiwithab.kairos.util.isValidJobDescription
import com.aiwithab.kairos.util.isValidTimeline

/**
 * Use case for generating a career plan
 * Encapsulates business logic and validation
 *
 * @property repository The plan repository
 */
class GeneratePlanUseCase(
    private val repository: PlanRepository
) {
    
    /**
     * Execute the use case to generate a plan
     *
     * @param category Job category
     * @param jobDescription Job description text
     * @param timeline Timeline in days (as string)
     * @return Result containing PlanDomain or validation/network error
     */
    suspend operator fun invoke(
        category: String,
        jobDescription: String,
        timeline: String
    ): Result<PlanDomain> {
        // Validate inputs
        val validationError = validateInputs(category, jobDescription, timeline)
        if (validationError != null) {
            return Result.Error(
                exception = IllegalArgumentException(validationError),
                message = validationError
            )
        }
        
        // Call repository
        return repository.generatePlan(
            userId = Constants.DEFAULT_USER_ID,
            category = category.trim(),
            jobDescription = jobDescription.trim(),
            timeline = timeline.trim()
        )
    }
    
    /**
     * Validate user inputs
     * @return Error message if validation fails, null if valid
     */
    private fun validateInputs(
        category: String,
        jobDescription: String,
        timeline: String
    ): String? {
        return when {
            !category.isValidCategory() -> 
                "Category must be at least ${Constants.MIN_CATEGORY_LENGTH} characters"
            
            !jobDescription.isValidJobDescription() -> 
                "Job description must be at least ${Constants.MIN_JOB_DESCRIPTION_LENGTH} characters"
            
            !timeline.isValidTimeline() -> 
                "Timeline must be between ${Constants.MIN_TIMELINE_DAYS} and ${Constants.MAX_TIMELINE_DAYS} days"
            
            else -> null
        }
    }
}
