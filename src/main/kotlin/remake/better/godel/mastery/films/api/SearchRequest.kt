package remake.better.godel.mastery.films.api

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Min


data class SearchRequest(
    @JsonProperty("lastName")
    val lastName: String?,
    @JsonProperty("releasedFrom")
    @field:Min(value = 1800, message = "Released From should be more than 1800")
    val releasedFrom: Int?,
    @JsonProperty("releasedUntil")
    @field:Min(value = 1800, message = "Released Until should be more than 1800")
    val releasedUntil: Int?
)