package com.rho.counterclick.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.rho.counterclick.service.CounterService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer

@Configuration
class WebSocketController(private val counterService: CounterService) {

    private val logger = LoggerFactory.getLogger(WebSocketController::class.java)

    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun handlerMapping(): HandlerMapping {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = mutableListOf("*", "http://localhost:3006")

        val corsConfigurationMap = mutableMapOf<String, CorsConfiguration>()
        corsConfigurationMap["/ws/bananas"] = corsConfiguration

        val simpleUrlHandlerMapping = SimpleUrlHandlerMapping()
        simpleUrlHandlerMapping.setCorsConfigurations(corsConfigurationMap)
        simpleUrlHandlerMapping.urlMap = mapOf("/ws/bananas" to webSocketHandler())
        simpleUrlHandlerMapping.order = -1

        return simpleUrlHandlerMapping
    }

    @Bean
    fun webSocketHandler(): WebSocketHandler {
        val om = ObjectMapper()
        val connections = ConcurrentHashMap<String, MessageHandler>()

        class ForwardingMessageHandler(
            val session: WebSocketSession,
            val sink: FluxSink<WebSocketMessage>
        ) : MessageHandler {
            private val sessionID = session.id

            override fun handleMessage(message: Message<*>) {

                val payload = counterService.getCounter().map { it }
                val ce = CounterEvent(sessionID, payload)
                logger.info("payload $payload")
                val tm = session.textMessage(payload.toString())
                sink.next(tm)
            }
        }

        return WebSocketHandler { session ->
            logger.info("session $session")
            val publisher = Flux.create(Consumer<FluxSink<WebSocketMessage>> { sink ->
                connections[session.id] = ForwardingMessageHandler(session, sink)
            })
                .doFinally {
                    connections.remove(session.id)
                }
            session.send(publisher)
        }
    }
}

data class CounterEvent(val sessionID: String, val counter: Mono<Long>)
