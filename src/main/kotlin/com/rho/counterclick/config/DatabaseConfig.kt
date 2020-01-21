package com.rho.counterclick.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.client.SSLMode
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(basePackages = ["com.rho.counterclick.repository"])
class DatabaseConfig : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host("ec2-3-215-41-107.compute-1.amazonaws.com")
                .port(5432)
                .username("sqhbvrorhjuntf")
                .password("3c150db5d02f7e795c547de305401f1f1df2338f14136a648a7ba9f575b493da")
                .database("dajvdq3pnd2jp1")
                .sslMode(SSLMode.REQUIRE)
                .build()
        )
    }
}