package remake.better.godel.mastery.films.repository_specification

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.domain.Films
import remake.better.godel.mastery.films.helper.dramaFilmEntity
import remake.better.godel.mastery.films.helper.fantasyFilmEntity
import remake.better.godel.mastery.films.helper.horrorFilmEntity
import remake.better.godel.mastery.films.helper.humorFilmEntity
import remake.better.godel.mastery.films.helper.mysteryFilmEntity
import remake.better.godel.mastery.films.intergration_tests.config.HSQLConfig
import remake.better.godel.mastery.films.repository.FilmsRepository
import remake.better.godel.mastery.films.specification.BaseSpecification
import remake.better.godel.mastery.films.specification.FilmsSpecification

@DataJpaTest(excludeAutoConfiguration = [TestDatabaseAutoConfiguration::class])
@Import(HSQLConfig::class)
@TestPropertySource(locations = ["classpath:/application-test.properties"])
open class FilmsRepositoryAndSpecificationTest {

    private val filmsSpecification: BaseSpecification = FilmsSpecification()

    @Autowired
    private lateinit var filmsRepository: FilmsRepository

    //@formatter:off
    companion object {
        @JvmStatic
        fun data(): List<Arguments> = listOf(
            Arguments.of(null, null, null, listOf(dramaFilmEntity(), fantasyFilmEntity(), horrorFilmEntity(), humorFilmEntity(), mysteryFilmEntity())),
            Arguments.of("Schuler", 2000, 2005, listOf(mysteryFilmEntity())),
            Arguments.of("Castro", null, null, listOf(dramaFilmEntity(), fantasyFilmEntity())),
            Arguments.of(null, 2000, null, listOf(dramaFilmEntity(), fantasyFilmEntity(), mysteryFilmEntity())),
            Arguments.of(null, null, 1998, listOf(horrorFilmEntity(), humorFilmEntity())),
            Arguments.of("Schuler", 2002, null, listOf(mysteryFilmEntity())),
            Arguments.of("Schuler", null, 2000, listOf(horrorFilmEntity(), humorFilmEntity())),
            Arguments.of(null, 2000, 2002, listOf(dramaFilmEntity(), fantasyFilmEntity()))
        )
    }
    //@formatter:on

    @ParameterizedTest
    @MethodSource("data")
    @Sql(value = ["classpath:integration/db/db_cleanup.sql", "/integration/db/db_data.sql"])
    fun shouldFindAllFilmsByCriteria(
        lastName: String?,
        releasedFrom: Int?,
        releasedUntil: Int?,
        expected: List<Films>
    ) {
        val criteria = SearchRequest(lastName, releasedFrom, releasedUntil)
        val spec: Specification<Films> = filmsSpecification.toSpecification(criteria)

        val actual: Page<Films> = filmsRepository.findAll(spec, PageRequest.of(0, 5))
        assertThat(actual.toList(), `is`(expected))
    }
}