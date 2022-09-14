package remake.better.godel.mastery.films.resource

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import remake.better.godel.mastery.films.api.ErrorResponse
import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.api.SearchRequest
import remake.better.godel.mastery.films.service.FilmsService
import javax.validation.Valid

@RestController
@RequestMapping("/v1/films")
class FilmsResource(private val filmsService: FilmsService) {

    @Operation(
        summary = "Get all films",
        description = "Endpoint for getting all films by criteria", responses = [
            ApiResponse(responseCode = "200", description = "Ok"),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    //@formatter:on
    @GetMapping
    fun getAllFilms(
        @Valid request: SearchRequest,
        @RequestParam(value = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(value = "size", defaultValue = "5", required = false) size: Int
    ): ResponseEntity<Page<FilmsResponse>> {
        return ResponseEntity.ok(filmsService.findAllFilmsBySpecification(request, page, size))
    }
}