package com.aiwithab.kairos.data.remote

import com.aiwithab.kairos.data.model.request.PlanRequest
import com.aiwithab.kairos.data.model.response.PlanResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API service interface for career planning endpoints
 */
interface ApiService {
    
    /**
     * Generate a personalized career plan based on job description and timeline
     *
     * @param request Plan request containing user details and requirements
     * @return Plan response with skills and topics
     */
    @POST("/career-plan")
    suspend fun generatePlan(@Body request: PlanRequest): PlanResponse
}
