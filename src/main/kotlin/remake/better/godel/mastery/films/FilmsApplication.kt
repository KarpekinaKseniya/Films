package remake.better.godel.mastery.films

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class FilmsApplication

fun main(args: Array<String>) {
    runApplication<FilmsApplication>(*args)
}
