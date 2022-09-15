package remake.better.godel.mastery.films.transformer

import org.springframework.stereotype.Component
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.domain.Films

@Component
class FilmsTransformerImpl : FilmsTransformer {

    override fun transformToResponse(film: Films?): FilmsResponse? {
        return when (film) {
            null -> null
            else -> FilmsResponse(
                film.director.firstName,
                film.director.lastName,
                film.director.birthDate,
                film.name,
                film.releaseDate,
                film.genre
            )
        }
    }
}