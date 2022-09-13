package remake.better.godel.mastery.films.converter

import remake.better.godel.mastery.films.domain.Genres
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class GenreConverter : AttributeConverter<Genres, String> {

    override fun convertToDatabaseColumn(attribute: Genres?): String? {
        if (attribute == null) {
            return null
        }
        return attribute.title
    }

    override fun convertToEntityAttribute(dbData: String?): Genres? {
        if (dbData == null) {
            return null
        }
        return Genres.values().first { genre: Genres -> dbData == genre.title }
    }
}