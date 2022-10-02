package com.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import jakarta.inject.Named
import me.blzr.BodyOperations
import me.blzr.CustomList
import me.blzr.CustomMap


@MicronautTest
class DemoTest(
    @Named("client") private val client: BodyOperations,
) : StringSpec({

    "test map request" {
        val map = mapOf("foo" to "bar", "123" to "890")
        client.getBodyMap(map) shouldBe map
    }

    "test list request" {
        val list = listOf("foo", "bar", "baz")
        client.getBodyList(list) shouldBe list
    }

    "test custom map request" {
        val map = mapOf("foo" to "bar", "123" to "890")
        client.getBodyCustomMap(CustomMap(map)) shouldBe map
    }

    "test custom list request" {
        val list = listOf("foo", "bar", "baz")
        client.getBodyCustomList(CustomList(list)) shouldBe list
    }
})
