package me.blzr

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/")
class BodyController : BodyOperations {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Post("/string")
    override fun getBodyString(
        @Body body: String
    ): String {
        return body
    }

    @Post("/map", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    override fun getBodyMap(
        @Body body: Map<String, String>
    ): Map<String, String> {
        log.info("Map read: $body")
        return body
    }

    @Post("/list", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    override fun getBodyList(
        @Body body: List<String>
    ): List<String> {
        log.info("Map read: $body")
        return body
    }

    @Post("/customMap", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    override fun getBodyCustomMap(
        @Body body: CustomMap
    ): Map<String, String> {
        log.info("Map read: $body")
        return body.map
    }

    @Post("/customList", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    override fun getBodyCustomList(
        @Body body: CustomList
    ): List<String> {
        log.info("Map read: $body")
        return body.list
    }
}

@Introspected
data class CustomMap(
    val map: Map<String, String>
)

@Introspected
data class CustomList(
    val list: List<String>
)