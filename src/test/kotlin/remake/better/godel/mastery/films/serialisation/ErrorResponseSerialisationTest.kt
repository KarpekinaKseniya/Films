package remake.better.godel.mastery.films.serialisation

import org.junit.jupiter.api.BeforeEach
import remake.better.godel.mastery.films.api.ErrorResponse
import java.util.function.Supplier
import javax.servlet.http.HttpServletResponse.SC_CONFLICT

class ErrorResponseSerialisationTest : JsonTestBase<ErrorResponse>() {

    @BeforeEach
    fun before() {
        expected = Supplier { ErrorResponse(SC_CONFLICT, "Error Message", "Error Description") }
        fileName = "expected_error_response.json"
        expectedType = ErrorResponse::class.java
    }
}