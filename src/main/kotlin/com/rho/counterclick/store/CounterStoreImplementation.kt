package com.rho.counterclick.store

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class CounterStoreImplementation() : CounterStore {

    var sharedCounter: Long = 0

    override fun selectCounter(): Mono<Long> {
        return Mono.fromCallable {
            sharedCounter;
        }
    }

    @Synchronized
    override fun incrementCounter(): Mono<Long> {
        sharedCounter++
        return Mono.fromCallable {
            sharedCounter

        }
    }
}