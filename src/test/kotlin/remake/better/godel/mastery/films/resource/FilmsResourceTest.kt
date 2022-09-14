package remake.better.godel.mastery.films.resource

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.helper.dramaFilmResponse
import remake.better.godel.mastery.films.helper.fantasyFilmResponse
import remake.better.godel.mastery.films.service.FilmsService
import kotlin.test.assertFailsWith

class FilmsResourceTest {

    private val request = SearchRequest("Castro", null, null)

    @Mock
    private lateinit var filmsService: FilmsService

    private lateinit var filmsResource: FilmsResource

    @BeforeEach
    fun setup() {
        openMocks(this)

        filmsResource = FilmsResource(filmsService)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(filmsService)
    }

    @Test
    fun shouldGetAllFilmsSuccess() {
        val expected: Page<FilmsResponse> = PageImpl(listOf(dramaFilmResponse(), fantasyFilmResponse()))

        given(filmsService.findAllFilmsBySpecification(request, 0, 5)).willReturn(expected)

        val actual: ResponseEntity<Page<FilmsResponse>> = filmsResource.getAllFilms(request, 0, 5)
        assertThat(actual, `is`(ok(expected)))

        then(filmsService).should(only()).findAllFilmsBySpecification(request, 0, 5)
    }

    @Test
    fun shouldGetAllFilmsThrowsExceptionWhenServiceReturnError() {
        val exception: Exception = RuntimeException("some error message")

        given(filmsService.findAllFilmsBySpecification(request, 0, 5)).willThrow(exception)

        val actual = assertFailsWith<Exception> { filmsResource.getAllFilms(request, 0, 5) }
        assertThat(actual, `is`(exception))

        then(filmsService).should(only()).findAllFilmsBySpecification(request, 0, 5)
    }
}