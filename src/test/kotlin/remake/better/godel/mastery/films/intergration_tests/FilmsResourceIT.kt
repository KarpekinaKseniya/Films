package remake.better.godel.mastery.films.intergration_tests

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import remake.better.godel.mastery.films.FilmsApplication
import remake.better.godel.mastery.films.intergration_tests.config.HSQLConfig
import uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@SpringBootTest(classes = [FilmsApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = [HSQLConfig::class])
@EnableConfigurationProperties
@TestPropertySource(locations = ["classpath:/application-test.properties"])
class FilmsResourceIT {

    @Value("\${local.server.port}")
    private var port: Int = 0

    companion object {
        @JvmStatic
        fun data(): List<Arguments> = listOf(
            Arguments.of(mapOf("lastName" to null), "all_films_response_200.json"),
            Arguments.of(
                mapOf("lastName" to "Schuler", "releasedFrom" to "2000", "releasedUntil" to "2005"),
                "mystery_film_response_200.json"
            ),
            Arguments.of(mapOf("lastName" to "Castro"), "castro_films_response_200.json"),
            Arguments.of(mapOf("releasedFrom" to "2000"), "after_2000_films_response_200.json"),
            Arguments.of(mapOf("releasedUntil" to "1998"), "schuler_films_response_200.json"),
            Arguments.of(mapOf("lastName" to "Schuler", "releasedFrom" to "2002"), "mystery_film_response_200.json"),
            Arguments.of(mapOf("lastName" to "Schuler", "releasedUntil" to "2000"), "schuler_films_response_200.json"),
            Arguments.of(mapOf("releasedFrom" to "2000", "releasedUntil" to "2002"), "castro_films_response_200.json")
        )
    }
    //@formatter:on

    @Test
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = ["classpath:integration/db/db_drop_table.sql"])
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = ["classpath:integration/db/db_setup.sql"])
    fun shouldReturnInternalErrorWhenDbDoesNotExist() {
        expectedFindAllFilms(emptyMap(), HttpStatus.SC_INTERNAL_SERVER_ERROR, "db_not_exist_error.json")
    }

    @Test
    fun shouldFindAlFilmsReturnBadRequestWhenWrongParams() {
        val params: Map<String, String> = mapOf("releasedFrom" to "1784", "releasedUntil" to "-2")
        expectedFindAllFilms(params, HttpStatus.SC_BAD_REQUEST, "response_400.json")
    }

    @ParameterizedTest
    @MethodSource("data")
    @Sql(
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = ["classpath:integration/db/db_cleanup.sql", "classpath:integration/db/db_data.sql"]
    )
    fun shouldFindAlFilmsReturnSuccess(params: Map<String, String>, responsePath: String) {
        expectedFindAllFilms(params, HttpStatus.SC_OK, responsePath)
    }

    @Throws(IOException::class)
    private fun expectedFindAllFilms(parametersMap: Map<String, String>, statusCode: Int, responseFile: String) {
        //@formatter:off
        RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .params(parametersMap)
            .get(buildRequestUrlStr())
            .then()
            .assertThat()
            .statusCode(statusCode)
            .body(sameJSONAs(getResponse(responseFile)))
        //@formatter:on
    }

    private fun buildRequestUrlStr(): String {
        return "http://localhost:$port/v1/films"
    }

    @Throws(IOException::class)
    private fun getResponse(file: String): String {
        return String(Files.readAllBytes(Paths.get("src/test/resources/integration/response/$file")))
    }
}