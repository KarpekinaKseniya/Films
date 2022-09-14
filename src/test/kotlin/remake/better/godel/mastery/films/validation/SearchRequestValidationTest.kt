package remake.better.godel.mastery.films.validation

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import remake.better.godel.mastery.films.api.SearchRequest
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchRequestValidationTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setup() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun shouldSuccessValidate() {
        val violations : Set<ConstraintViolation<SearchRequest>> = validator.validate(SearchRequest(null, null, null))
        assertTrue { violations.isEmpty() }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 1799])
    fun shouldReturnErrorWhenReleasedFromLessThan1800(releasedFrom: Int) {
        val violations : Set<ConstraintViolation<SearchRequest>> = validator.validate(SearchRequest("Castro", releasedFrom, null))
        val errorMessage: String = violations.iterator().next().message
        assertFalse { violations.isEmpty() }
        assertThat(errorMessage, `is`("Released From should be more than 1800"))
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -5465, 1567])
    fun shouldReturnErrorWhenReleasedUntilLessThan1800(releasedUntil: Int) {
        val violations : Set<ConstraintViolation<SearchRequest>> = validator.validate(SearchRequest("Castro", null, releasedUntil))
        val errorMessage: String = violations.iterator().next().message
        assertFalse { violations.isEmpty() }
        assertThat(errorMessage, `is`("Released Until should be more than 1800"))
    }
}