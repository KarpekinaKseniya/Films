package remake.better.godel.mastery.films.service

import org.springframework.data.domain.Page
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.api.SearchRequest

interface FilmsService {

    fun findAllFilmsBySpecification(search: SearchRequest, page: Int, size: Int): Page<FilmsResponse>
}
