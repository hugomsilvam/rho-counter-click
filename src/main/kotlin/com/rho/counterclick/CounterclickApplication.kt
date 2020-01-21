package com.rho.counterclick

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CounterclickApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<CounterclickApplication>(*args)
}
