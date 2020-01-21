package com.rho.counterclick.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.client.SSLMode
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(basePackages = ["com.rho.counterclick.repository"])
class DatabaseConfig : AbstractR2dbcConfiguration() {

    @Value("\${database.credentials.host}")
    val host: String? = null

    @Value("\${database.credentials.port}")
    val port: Int = 0

    @Value("\${database.credentials.username}")
    val username: String? = null

    @Value("\${database.credentials.password}")
    val password: String? = null

    @Value("\${database.credentials.database}")
    val database: String? = null

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
            .host(host.orEmpty())
            .port(port)
            .username(username.orEmpty())
            .password(password)
            .database(database)
            .sslMode(SSLMode.REQUIRE)
            .build()
        )
    }
}
