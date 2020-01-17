package com.rho.counterclick.service

import com.rho.counterclick.store.CounterStore
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CounterServiceImplementation(
        private val counterStore : CounterStore
) : CounterService {

    override fun getCounter(): Mono<Long> {
        return counterStore.selectCounter()
    }

    override fun incrementCounter(): Mono<Long> {
        return counterStore.incrementCounter()
    }
}