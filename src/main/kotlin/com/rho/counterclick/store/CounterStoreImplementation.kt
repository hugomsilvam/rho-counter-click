package com.rho.counterclick.store

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class CounterStoreImplementation() : CounterStore {

    override fun selectCounter(): Mono<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun incrementCounter(): Mono<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}