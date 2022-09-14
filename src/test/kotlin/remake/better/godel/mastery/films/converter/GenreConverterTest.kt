package remake.better.godel.mastery.films.converter

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import remake.better.godel.mastery.films.domain.Genres
import kotlin.test.assertFailsWith

class GenreConverterTest {

    private val converter: GenreConverter = GenreConverter()

    companion object {
        @JvmStatic
        fun provideStringToConvertAndResult(): List<Arguments> = listOf(
            Arguments.of(null, null),
            Arguments.of(Genres.ADVENTURE.title, Genres.ADVENTURE)
        )

        @JvmStatic
        fun provideGenresToConvertAndResult(): List<Arguments> = listOf(
            Arguments.of(null, null),
            Arguments.of(Genres.HORROR, Genres.HORROR.title),
            Arguments.of(Genres.FANTASY, Genres.FANTASY.title)
        )
    }

    @ParameterizedTest
    @MethodSource("provideGenresToConvertAndResult")
    fun shouldConvertToDatabaseColumn(genre: Genres?, expected: String?) {
        assertThat(converter.convertToDatabaseColumn(genre), `is`(expected))
    }

    @ParameterizedTest
    @MethodSource("provideStringToConvertAndResult")
    fun shouldConvertToEntityAttribute(dbData: String?, expected: Genres?) {
        assertThat(converter.convertToEntityAttribute(dbData), `is`(expected))
    }

    @Test
    fun shouldThrowExceptionWhenWrongDbData() {
        val actual = assertFailsWith<NoSuchElementException> { converter.convertToEntityAttribute("Wrong") }
        assertThat(actual.message, `is`("Array contains no element matching the predicate."))
    }

}