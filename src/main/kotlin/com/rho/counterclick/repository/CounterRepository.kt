package com.rho.counterclick.repository

import com.rho.counterclick.model.Counter
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CounterRepository : ReactiveCrudRepository<Counter, Long> {

    @Query("SELECT * FROM Counters WHERE counterID = :counterID ")
    fun getCounter(counterID: Long): Mono<Counter>

    @Query("UPDATE Counters set counterValue = counterValue +1 WHERE counterID = :counterID")
    fun incrementCounter(counterID: Long): Mono<Counter>
}