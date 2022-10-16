package me.blzr

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import javax.sql.DataSource

@MicronautTest
class EnumTest(
    private val entWithEnumRepo: EntWithEnumRepo,
    private val dataSource: DataSource,
) : StringSpec({

    "test the server is running" {
        val savedEntity = entWithEnumRepo.save(EntWithEnum(1, Enum1.FOO, Enum2.V10))
        val foundEntity = entWithEnumRepo.findById(1).get()

        foundEntity.id shouldBe 1
        foundEntity.enum1 shouldBe Enum1.FOO
        foundEntity.enum2 shouldBe Enum2.V10

        dataSource.connection.use { con ->
            con.prepareStatement("Select * from with_enum").executeQuery().use { rs ->
                rs.next()
                rs.getInt(1) shouldBe 1
                rs.getString(2) shouldBe "foo"
                rs.getInt(3) shouldBe 10
            }
        }
    }
})
