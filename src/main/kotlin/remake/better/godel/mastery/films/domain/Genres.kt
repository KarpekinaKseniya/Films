package remake.better.godel.mastery.films.domain

import com.fasterxml.jackson.annotation.JsonValue

enum class Genres(@JsonValue val title: String) {
    ADVENTURE("Adventure"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    HUMOR("Humor"),
    MYSTERY("Mystery"),
    NONFICTION("Nonfiction"),
    ROMANCE("Romance"),
    SCI_FI("Sci-fi");
}