package me.blzr

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Named

@Client("/")
@Named("client")
interface BodyOperations {
    @Post("/map")
    fun getBodyMap(@Body body: Map<String, String>): Map<String, String>

    @Post("/list")
    fun getBodyList(@Body body: List<String>): List<String>

    @Post("/customMap")
    fun getBodyCustomMap(@Body body: CustomMap): Map<String, String>

    @Post("/customList")
    fun getBodyCustomList(@Body body: CustomList): List<String>

    @Post("/string")
    fun getBodyString(@Body body: String): String
}