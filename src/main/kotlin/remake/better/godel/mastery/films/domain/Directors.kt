package remake.better.godel.mastery.films.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "director")
data class Directors(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(nullable = false, name = "first_name")
    val firstName: String,
    @Column(nullable = false, name = "last_name")
    val lastName: String,
    @Column(nullable = false, name = "birth_date")
    val birthDate: LocalDate,
    @OneToMany(mappedBy = "id")
    val films: List<Films>
)