package remake.better.godel.mastery.films.intergration_tests.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL
import javax.sql.DataSource

@Configuration
open class HSQLConfig {

    @Bean
    open fun dataSource(): DataSource {
        //@formatter:off
        return EmbeddedDatabaseBuilder()
            .setType(HSQL)
            .addScript("integration/db/db_setup.sql")
            .generateUniqueName(true).build()
        //@formatter:on
    }
}