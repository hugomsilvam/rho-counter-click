package com.rho.counterclick.store

import reactor.core.publisher.Mono

interface CounterStore {

    fun selectCounter(): Mono<Long>

    fun incrementCounter(): Mono<Long>
}