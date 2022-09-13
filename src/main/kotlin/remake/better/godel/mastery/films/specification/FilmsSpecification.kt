package remake.better.godel.mastery.films.specification

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.domain.Specification.where
import org.springframework.stereotype.Component
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.domain.Directors
import remake.better.godel.mastery.films.domain.Films
import java.time.LocalDate

@Component
class FilmsSpecification {

    fun toSpecification(criteria: SearchRequest): Specification<Films> {
        return Specification<Films> { root, query, criteriaBuilder ->
            where(likeLastName(criteria.lastName))
                .and(afterYearOrEqual(criteria.releasedFrom))
                .and(beforeYearOrEqual(criteria.releasedUntil))
                .toPredicate(root, query, criteriaBuilder)
        }
    }

    private fun likeLastName(lastName: String?): Specification<Films> {
        return Specification<Films> { root, _, criteriaBuilder ->
            when (lastName) {
                null -> criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                else -> criteriaBuilder.like(
                    criteriaBuilder.lower(root.get<Directors>("director").get("lastName")),
                    "%${lastName.lowercase()}%"
                )
            }
        }
    }

    private fun beforeYearOrEqual(releasedUntil: Int?): Specification<Films> {
        return Specification<Films> { root, _, builder ->
            when (releasedUntil) {
                null -> builder.isTrue(builder.literal(true))
                else -> builder.lessThanOrEqualTo(
                    builder.function(
                        "year",
                        Int::class.java,
                        root.get<LocalDate>("releaseDate")
                    ), releasedUntil
                )
            }
        }
    }

    private fun afterYearOrEqual(releasedFrom: Int?): Specification<Films> {
        return Specification<Films> { root, _, builder ->
            when (releasedFrom) {
                null -> builder.isTrue(builder.literal(true))
                else -> builder.greaterThanOrEqualTo(
                    builder.function("year", Int::class.java, root.get<LocalDate>("releaseDate")), releasedFrom
                )
            }
        }
    }
}