package com.aiwithab.kairos.util

/**
 * Application-wide constants
 */
object Constants {
    
    // Network Configuration
    const val NETWORK_TIMEOUT_SECONDS = 60L
    
    // API Endpoints
    const val ENDPOINT_CAREER_PLAN = "/career-plan"
    
    // Default Values
    const val DEFAULT_USER_ID = "123"
    
    // Priority Levels
    const val PRIORITY_HIGH = "High"
    const val PRIORITY_MEDIUM = "Medium"
    const val PRIORITY_LOW = "Low"
    
    // Validation
    const val MIN_TIMELINE_DAYS = 1
    const val MAX_TIMELINE_DAYS = 365
    const val MIN_CATEGORY_LENGTH = 2
    const val MIN_JOB_DESCRIPTION_LENGTH = 10
}
