package com.rho.counterclick.controller

import com.rho.counterclick.service.CounterService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("counter")
class CounterController(
        private val counterService: CounterService
) {

    private val logger = LoggerFactory.getLogger(CounterController::class.java)

    // get number of the counter
    @GetMapping
    fun counter(): Mono<Long> {
        return counterService.getCounter()
                .doOnSuccess { logger.info("Counter number $it") }
    }

    @PostMapping
    fun increment(): Mono<Long> {
        return counterService.incrementCounter()
                .doOnSuccess { logger.info("Increment counter number to $it") }
    }
}