package com.rho.counterclick.service

import com.rho.counterclick.repository.CounterRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CounterServiceImplementation(
        private val counterRepository: CounterRepository
) : CounterService {

    override fun getCounter(): Mono<Long> {
        return counterRepository.getCounter(1)
                .map { it.value }
    }

    override fun incrementCounter(): Mono<Long> {
        return counterRepository.incrementCounter(1)
                .map { it.value }
    }
}