package com.rho.counterclick.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.postgresql.Driver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class DatabaseConfig {
    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.driverClassName = Driver::class.java.name
        config.jdbcUrl = dbUrl
        return HikariDataSource(config)
    }
}