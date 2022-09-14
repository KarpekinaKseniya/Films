package remake.better.godel.mastery.films.transformer

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.domain.Films
import remake.better.godel.mastery.films.helper.dramaFilmEntity
import remake.better.godel.mastery.films.helper.dramaFilmResponse

class FilmsTransformerTest {

    private val transformer: FilmsTransformer = FilmsTransformerImpl()


    companion object {
        @JvmStatic
        fun data(): List<Arguments> = listOf(
            Arguments.of(null, null),
            Arguments.of(dramaFilmEntity(), dramaFilmResponse())
        )
    }

    @ParameterizedTest
    @MethodSource("data")
    fun shouldTransformToResponse(entity: Films?, response: FilmsResponse?) {
        assertThat(transformer.transformToResponse(entity), `is`(response))
    }
}