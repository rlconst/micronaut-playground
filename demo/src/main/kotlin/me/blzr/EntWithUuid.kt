package me.blzr

import io.micronaut.data.annotation.AutoPopulated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@MappedEntity("with_uuid")
class EntWithUuid(
    @field:Id
    @field:AutoPopulated
    var id: UUID?,

    val nullableUuid: UUID?,
)

@JdbcRepository
interface EntWithUuidRepo : CrudRepository<EntWithUuid, UUID> {
    @Query(
        """
        select * from with_uuid
        where :value is null or :value = nullable_uuid 
    """, nativeQuery = true
    )
    fun findByUuid(value: UUID?): Collection<EntWithUuid>
}