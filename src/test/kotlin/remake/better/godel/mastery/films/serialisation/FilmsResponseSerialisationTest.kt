package remake.better.godel.mastery.films.serialisation

import org.junit.jupiter.api.BeforeEach
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.helper.dramaFilmResponse
import java.util.function.Supplier

class FilmsResponseSerialisationTest: JsonTestBase<FilmsResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { dramaFilmResponse() }
        expectedType = FilmsResponse::class.java
        fileName = "expected_films_response.json"
    }
}