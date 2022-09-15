package remake.better.godel.mastery.films.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import remake.better.godel.mastery.films.domain.Films

@Repository
interface FilmsRepository : JpaRepository<Films, Int>, JpaSpecificationExecutor<Films>