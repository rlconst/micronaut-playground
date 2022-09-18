package me.blzr

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

@Singleton
class Application : ApplicationEventListener<ServerStartupEvent> {
    val log: Logger = LoggerFactory.getLogger(Application::class.java)

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        log.info("Application started")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            // Bridge JUL to Slf4j for liquibase
            // https://micronaut-projects.github.io/micronaut-liquibase/latest/guide/#_logging
            SLF4JBridgeHandler.removeHandlersForRootLogger()
            SLF4JBridgeHandler.install()

            Micronaut
                .build()
                .mainClass(Application::class.java)
                .args(*args)
                .start()
        }
    }
}
