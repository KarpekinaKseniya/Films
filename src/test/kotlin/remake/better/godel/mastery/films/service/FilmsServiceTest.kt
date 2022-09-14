package remake.better.godel.mastery.films.service

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.only
import org.mockito.Mockito.times
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.domain.Films
import remake.better.godel.mastery.films.helper.dramaFilmEntity
import remake.better.godel.mastery.films.helper.dramaFilmResponse
import remake.better.godel.mastery.films.helper.fantasyFilmEntity
import remake.better.godel.mastery.films.helper.fantasyFilmResponse
import remake.better.godel.mastery.films.repository.FilmsRepository
import remake.better.godel.mastery.films.specification.BaseSpecification
import remake.better.godel.mastery.films.transformer.FilmsTransformer
import kotlin.test.assertFailsWith

private const val PAGE: Int = 0
private const val SIZE: Int = 3
private val REQUEST = SearchRequest("Castro", null, null)
private val EXCEPTION: Exception = RuntimeException("Some message")

internal class FilmsServiceTest {

    @Mock
    private lateinit var filmsTransformer: FilmsTransformer

    @Mock
    private lateinit var filmsRepository: FilmsRepository

    @Mock
    private lateinit var filmsSpecification: BaseSpecification

    private var mockSpecification: Specification<Films> = mock(Specification::class.java) as Specification<Films>

    private lateinit var service: FilmsService

    @BeforeEach
    fun setUp() {
        openMocks(this)
        service = FilmsServiceImpl(filmsRepository, filmsTransformer, filmsSpecification)
    }

    @AfterEach
    fun verify() {
        verifyNoMoreInteractions(filmsRepository, filmsSpecification, filmsTransformer)
    }

    @Test
    fun shouldFindAllFilmsBySpecificationSuccess() {
        val expected: Page<FilmsResponse> = PageImpl(listOf(dramaFilmResponse(), fantasyFilmResponse()))

        //@formatter:off
        given(filmsSpecification.toSpecification(REQUEST)).willReturn(mockSpecification)
        given(filmsRepository.findAll(mockSpecification, PageRequest.of(PAGE, SIZE))).willReturn(PageImpl(listOf(dramaFilmEntity(), fantasyFilmEntity())))
        given(filmsTransformer.transformToResponse(dramaFilmEntity())).willReturn(dramaFilmResponse())
        given(filmsTransformer.transformToResponse(fantasyFilmEntity())).willReturn(fantasyFilmResponse())
        //@formatter:on

        val actual: Page<FilmsResponse> = service.findAllFilmsBySpecification(REQUEST, PAGE, SIZE)
        assertThat(actual, `is`(expected))

        then(filmsSpecification).should(only()).toSpecification(REQUEST)
        then(filmsRepository).should(only()).findAll(mockSpecification, PageRequest.of(PAGE, SIZE))
        then(filmsTransformer).should(times(1)).transformToResponse(dramaFilmEntity())
        then(filmsTransformer).should(times(1)).transformToResponse(fantasyFilmEntity())
    }

    @Test
    fun shouldFindAllFilmsBySpecificationThrowsExceptionWhenSpecificationReturnError() {
        given(filmsSpecification.toSpecification(REQUEST)).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception> { service.findAllFilmsBySpecification(REQUEST, PAGE, SIZE) }
        assertThat(actual, `is`(EXCEPTION))

        then(filmsSpecification).should(only()).toSpecification(REQUEST)
    }

    @Test
    fun shouldFindAllFilmsBySpecificationThrowsExceptionWhenRepositoryReturnError() {
        given(filmsSpecification.toSpecification(REQUEST)).willReturn(mockSpecification)
        given(filmsRepository.findAll(mockSpecification, PageRequest.of(PAGE, SIZE))).willThrow(EXCEPTION)

        val actual = assertFailsWith<Exception> { service.findAllFilmsBySpecification(REQUEST, PAGE, SIZE) }
        assertThat(actual, `is`(EXCEPTION))

        then(filmsSpecification).should(only()).toSpecification(REQUEST)
        then(filmsRepository).should(only()).findAll(mockSpecification, PageRequest.of(PAGE, SIZE))
    }

    @Test
    fun shouldFindAllFilmsBySpecificationThrowsExceptionWhenTransformerReturnError() {
        //@formatter:off
        given(filmsSpecification.toSpecification(REQUEST)).willReturn(mockSpecification)
        given(filmsRepository.findAll(mockSpecification, PageRequest.of(PAGE, SIZE))).willReturn(PageImpl(listOf(dramaFilmEntity(), fantasyFilmEntity())))
        given(filmsTransformer.transformToResponse(dramaFilmEntity())).willThrow(EXCEPTION)
        //@formatter:on

        val actual = assertFailsWith<Exception> { service.findAllFilmsBySpecification(REQUEST, PAGE, SIZE) }
        assertThat(actual, `is`(EXCEPTION))

        then(filmsSpecification).should(only()).toSpecification(REQUEST)
        then(filmsRepository).should(only()).findAll(mockSpecification, PageRequest.of(PAGE, SIZE))
        then(filmsTransformer).should(only()).transformToResponse(dramaFilmEntity())
    }
}