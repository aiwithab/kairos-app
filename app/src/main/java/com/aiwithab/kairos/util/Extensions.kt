package com.aiwithab.kairos.util

/**
 * Extension functions for common operations
 */

/**
 * Checks if a string is a valid category
 */
fun String.isValidCategory(): Boolean {
    return this.trim().length >= Constants.MIN_CATEGORY_LENGTH
}

/**
 * Checks if a string is a valid job description
 */
fun String.isValidJobDescription(): Boolean {
    return this.trim().length >= Constants.MIN_JOB_DESCRIPTION_LENGTH
}

/**
 * Checks if a string is a valid timeline (numeric and within range)
 */
fun String.isValidTimeline(): Boolean {
    val days = this.toIntOrNull() ?: return false
    return days in Constants.MIN_TIMELINE_DAYS..Constants.MAX_TIMELINE_DAYS
}

/**
 * Safely converts string to Int, returns null if invalid
 */
fun String.toIntSafe(): Int? {
    return this.toIntOrNull()
}
