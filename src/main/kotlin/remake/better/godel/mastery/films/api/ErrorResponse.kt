package remake.better.godel.mastery.films.api

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("statusCode")
    val statusCode: Int,
    @JsonProperty("message")
    val message: String,
    @JsonProperty("description")
    val description: String
)