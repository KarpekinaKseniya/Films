package remake.better.godel.mastery.films.api

import remake.better.godel.mastery.films.domain.Genres
import java.time.LocalDate

data class FilmsResponse(
    val directorFirstName: String,
    val directorLastName: String,
    val directorBirthDate: LocalDate,
    val filmName: String,
    val filmReleaseDate: LocalDate,
    val filmGenre: Genres
)