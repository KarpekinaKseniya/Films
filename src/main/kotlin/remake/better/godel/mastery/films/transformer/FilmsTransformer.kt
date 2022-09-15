package remake.better.godel.mastery.films.transformer

import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.domain.Films

interface FilmsTransformer {

    fun transformToResponse(film: Films?): FilmsResponse?
}