package remake.better.godel.mastery.films.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "director")
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
    @OneToMany(mappedBy = "director")
    val films: List<Films>?
) {
    override fun toString(): String {
        return "Directors(id=$id, firstName='$firstName', lastName='$lastName', birthDate=$birthDate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Directors

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (birthDate != other.birthDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + birthDate.hashCode()
        return result
    }
}