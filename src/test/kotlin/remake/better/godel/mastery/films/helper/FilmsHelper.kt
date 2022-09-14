package remake.better.godel.mastery.films.helper

import remake.better.godel.mastery.films.api.FilmsResponse
import remake.better.godel.mastery.films.domain.Directors
import remake.better.godel.mastery.films.domain.Films
import remake.better.godel.mastery.films.domain.Genres
import java.time.LocalDate

private const val DRAMA_FILM_ID: Int = 2
private const val FANTASY_FILM_ID: Int = 3
private const val DRAMA_FILM_NAME: String = "Foreigner Puzzle"
private const val FANTASY_FILM_NAME: String = "Monument Without a Leader"
private const val CASTRO_RELEASE_DATE: String = "2001-03-15"

private const val HUMOR_FILM_ID: Int = 5
private const val HUMOR_FILM_NAME: String = "Dog of Tomorrow"
private const val HUMOR_FILM_RELEASE_DATE: String = "1998-01-16"

private const val HORROR_FILM_ID: Int = 4
private const val HORROR_FILM_NAME: String = "Beasts and Ghosts"
private const val HORROR_FILM_RELEASE_DATE: String = "1997-12-12"

private const val MYSTERY_FILM_ID: Int = 6
private const val MYSTERY_FILM_NAME: String = "Celebrate the West"
private const val MYSTERY_FILM_RELEASE_DATE: String = "2003-06-10"

private const val CASTRO_ID: Int = 1
private const val CASTRO_FIRST_NAME: String = "Barys"
private const val CASTRO_LAST_NAME: String = "Castro"
private const val CASTRO_BIRTH_DATE: String = "1963-01-14"

private const val GAVREL_ID: Int = 2
private const val GAVREL_FIRST_NAME: String = "Gavrel"
private const val GAVREL_LAST_NAME: String = "Schuler"
private const val GAVREL_BIRTH_DATE: String = "1960-12-08"

fun dramaFilmEntity(): Films {
    return Films(
        DRAMA_FILM_ID,
        castroEntity(),
        DRAMA_FILM_NAME,
        LocalDate.parse(CASTRO_RELEASE_DATE),
        Genres.DRAMA
    )
}

fun fantasyFilmEntity(): Films {
    return Films(
        FANTASY_FILM_ID,
        castroEntity(),
        FANTASY_FILM_NAME,
        LocalDate.parse(CASTRO_RELEASE_DATE),
        Genres.FANTASY
    )
}

fun horrorFilmEntity(): Films {
    return Films(
        HORROR_FILM_ID,
        gavrelEntity(),
        HORROR_FILM_NAME,
        LocalDate.parse(HORROR_FILM_RELEASE_DATE),
        Genres.HORROR
    )
}

fun humorFilmEntity(): Films {
    return Films(
        HUMOR_FILM_ID,
        gavrelEntity(),
        HUMOR_FILM_NAME,
        LocalDate.parse(HUMOR_FILM_RELEASE_DATE),
        Genres.HUMOR
    )
}

fun mysteryFilmEntity(): Films {
    return Films(
        MYSTERY_FILM_ID,
        gavrelEntity(),
        MYSTERY_FILM_NAME,
        LocalDate.parse(MYSTERY_FILM_RELEASE_DATE),
        Genres.MYSTERY
    )
}

fun castroEntity(): Directors {
    return Directors(
        CASTRO_ID,
        CASTRO_FIRST_NAME,
        CASTRO_LAST_NAME,
        LocalDate.parse(CASTRO_BIRTH_DATE),
        null
    )
}

fun gavrelEntity(): Directors {
    return Directors(
        GAVREL_ID,
        GAVREL_FIRST_NAME,
        GAVREL_LAST_NAME,
        LocalDate.parse(GAVREL_BIRTH_DATE),
        null
    )
}

fun dramaFilmResponse(): FilmsResponse {
    return FilmsResponse(
        CASTRO_FIRST_NAME,
        CASTRO_LAST_NAME,
        LocalDate.parse(CASTRO_BIRTH_DATE),
        DRAMA_FILM_NAME,
        LocalDate.parse(CASTRO_RELEASE_DATE),
        Genres.DRAMA
    )
}

fun fantasyFilmResponse(): FilmsResponse {
    return FilmsResponse(
        CASTRO_FIRST_NAME,
        CASTRO_LAST_NAME,
        LocalDate.parse(CASTRO_BIRTH_DATE),
        FANTASY_FILM_NAME,
        LocalDate.parse(CASTRO_RELEASE_DATE),
        Genres.FANTASY
    )
}