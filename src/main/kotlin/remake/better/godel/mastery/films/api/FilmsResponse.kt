package remake.better.godel.mastery.films.api

import com.fasterxml.jackson.annotation.JsonProperty
import remake.better.godel.mastery.films.domain.Genres
import java.time.LocalDate

data class FilmsResponse(
    @JsonProperty("directorFirstName")
    val directorFirstName: String,
    @JsonProperty("directorLastName")
    val directorLastName: String,
    @JsonProperty("directorBirthDate")
    val directorBirthDate: LocalDate,
    @JsonProperty("filmName")
    val filmName: String,
    @JsonProperty("filmReleaseDate")
    val filmReleaseDate: LocalDate,
    @JsonProperty("filmGenre")
    val filmGenre: Genres
)