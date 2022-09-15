package remake.better.godel.mastery.films.handler

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import remake.better.godel.mastery.films.api.ErrorResponse
import java.util.stream.Collectors

@RestControllerAdvice
open class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    open fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ErrorResponse? {
        //@formatter:off
        return ErrorResponse(
            BAD_REQUEST.value(),
            ex.localizedMessage,
            request.getDescription(false)
        )
        //@formatter:on
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    open fun handleValidationError(ex: BindException, request: WebRequest): ErrorResponse? {
        //@formatter:off
        return ErrorResponse(
            BAD_REQUEST.value(),
            ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).sorted().collect(Collectors.joining(", ")),
            request.getDescription(false)
        )
        //@formatter:on
    }

    @ExceptionHandler
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    open fun handleGlobalException(ex: Exception, request: WebRequest): ErrorResponse {
        //@formatter:off
        return ErrorResponse(
            INTERNAL_SERVER_ERROR.value(),
            ex.localizedMessage,
            request.getDescription(false)
        )
        //@formatter:on
    }
}