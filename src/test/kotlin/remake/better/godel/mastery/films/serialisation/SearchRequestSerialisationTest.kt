package remake.better.godel.mastery.films.serialisation

import org.junit.jupiter.api.BeforeEach
import remake.better.godel.mastery.films.api.SearchRequest
import java.util.function.Supplier

class SearchRequestSerialisationTest: JsonTestBase<SearchRequest>() {

    @BeforeEach
    fun before() {
        expected = Supplier { SearchRequest("Castro", 2010, 2020) }
        expectedType = SearchRequest::class.java
        fileName = "expected_search_request.json"
    }
}