package me.blzr

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import java.util.*

@MicronautTest
class NullableUuidTest(
    private val application: EmbeddedApplication<*>,
    private val entWithUuidRepo: EntWithUuidRepo,
) : StringSpec({

    "test the server is running" {
        val uuid = UUID.randomUUID()
        entWithUuidRepo.saveAll(
            listOf(
                EntWithUuid(null, uuid),
                EntWithUuid(null, null),
            )
        )
        entWithUuidRepo.findByUuid(uuid).size shouldBe 1
        entWithUuidRepo.findByUuid(null).size shouldBe 1
    }
})
