package remake.better.godel.mastery.films.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "film")
data class Films(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    val director: Directors,
    @Column(name = "name")
    val name: String,
    @Column(name = "release_date")
    val releaseDate: LocalDate,
    @Column(name = "genre")
    val genre: Genres
)