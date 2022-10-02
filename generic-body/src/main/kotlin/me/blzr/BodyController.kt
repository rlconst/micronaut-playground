package me.blzr

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/body")
class BodyController : BodyOperations {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Post("/map")
    override fun getBodyMap(
        @Body body: Map<String, String>
    ): Map<String, String> {
        log.info("Map read: $body")
        return body
    }

    @Post("/list")
    override fun getBodyList(
        @Body body: List<String>
    ): List<String> {
        log.info("Map read: $body")
        return body
    }

    @Post("/customMap")
    override fun getBodyCustomMap(
        @Body body: CustomMap
    ): Map<String, String> {
        log.info("Map read: $body")
        return body.map
    }

    @Post("/customList")
    override fun getBodyCustomList(
        @Body body: CustomList
    ): List<String> {
        log.info("Map read: $body")
        return body.list
    }
}

@Introspected
class CustomMap(
    val map: Map<String, String>
)

@Introspected
class CustomList(
    val list: List<String>
)