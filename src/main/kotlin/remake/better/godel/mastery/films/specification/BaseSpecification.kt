package remake.better.godel.mastery.films.specification

import org.springframework.data.jpa.domain.Specification
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.domain.Films

interface BaseSpecification {

    fun toSpecification(criteria: SearchRequest): Specification<Films>
}