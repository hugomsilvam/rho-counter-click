package com.rho.counterclick.service

import reactor.core.publisher.Mono

interface CounterService {
    fun getCounter() : Mono<Long>
    fun incrementCounter() : Mono<Long>
}
