package remake.better.godel.mastery.films.api

data class SearchRequest(
    val lastName: String?,
    val releasedFrom: Int?,
    val releasedUntil: Int?
)