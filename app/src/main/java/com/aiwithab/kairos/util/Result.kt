package com.aiwithab.kairos.util

/**
 * A generic wrapper for handling success and error states
 */
sealed class Result<out T> {
    
    /**
     * Represents a successful result
     * @property data The successful data
     */
    data class Success<T>(val data: T) : Result<T>()
    
    /**
     * Represents an error result
     * @property exception The exception that occurred
     * @property message Optional error message
     */
    data class Error(
        val exception: Throwable,
        val message: String? = exception.message
    ) : Result<Nothing>()
    
    /**
     * Represents a loading state
     */
    object Loading : Result<Nothing>()
    
    /**
     * Returns true if this is a Success result
     */
    val isSuccess: Boolean
        get() = this is Success
    
    /**
     * Returns true if this is an Error result
     */
    val isError: Boolean
        get() = this is Error
    
    /**
     * Returns true if this is a Loading result
     */
    val isLoading: Boolean
        get() = this is Loading
    
    /**
     * Returns the data if Success, null otherwise
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }
}
