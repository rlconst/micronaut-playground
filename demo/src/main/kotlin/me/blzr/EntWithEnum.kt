package me.blzr

import io.micronaut.core.convert.ConversionContext
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.DataType
import io.micronaut.data.model.runtime.convert.AttributeConverter
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Singleton
import jakarta.persistence.Convert
import jakarta.persistence.Converter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@MappedEntity("with_enum")
class EntWithEnum(
    @field:Id
    val id: Long,

    @field:TypeDef(type = DataType.STRING, converter = Enum1Converter::class)
    val enum1: Enum1,

    @field:TypeDef(type = DataType.INTEGER)
    @field:Convert(converter = Enum2Converter::class)
    val enum2: Enum2,
)

enum class Enum1(val id: String) {
    FOO("foo"),
    BAR("bar"),
    BAZ("baz")
}

@Singleton
class Enum1Converter : AttributeConverter<Enum1, String> {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    override fun convertToPersistedValue(entityValue: Enum1?, context: ConversionContext): String? {
        log.info("Converting $entityValue")
        return entityValue?.id
    }

    override fun convertToEntityValue(persistedValue: String?, context: ConversionContext): Enum1? {
        log.info("Converting $persistedValue")
        return Enum1.values().find { it.id == persistedValue }
    }

}

// @MappedEntity // You can't Map Enums
enum class Enum2(@field:Id val id: Int) {
    V10(10),
    V20(20),
    V30(30)
}

@Converter(autoApply = false)
class Enum2Converter : AttributeConverter<Enum2, Int> {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    override fun convertToPersistedValue(entityValue: Enum2?, context: ConversionContext): Int? {
        log.info("Converting $entityValue")
        return entityValue?.id
    }

    override fun convertToEntityValue(persistedValue: Int?, context: ConversionContext): Enum2? {
        log.info("Converting $persistedValue")
        return Enum2.values().find { it.id == persistedValue }
    }

}

@JdbcRepository
interface EntWithEnumRepo : CrudRepository<EntWithEnum, Long>