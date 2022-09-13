package remake.better.godel.mastery.films.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.domain.Films
import remake.better.godel.mastery.films.repository.FilmsRepository
import remake.better.godel.mastery.films.specification.FilmsSpecification
import remake.better.godel.mastery.films.transformer.FilmsTransformer

@Service
class FilmsServiceImpl(
    private val filmsRepository: FilmsRepository,
    private val filmsTransformer: FilmsTransformer,
    private val filmsSpecification: FilmsSpecification
) : FilmsService {

    override fun findAllFilmsBySpecification(search: SearchRequest, page: Int, size: Int): Page<FilmsResponse> {
        return filmsRepository.findAll(filmsSpecification.toSpecification(search), PageRequest.of(page, size))
            .map { film: Films -> filmsTransformer.transformToResponse(film) }
    }
}