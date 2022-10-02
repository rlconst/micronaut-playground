package me.blzr

import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Named

@Client("/")
@Named("client")
interface BodyOperations {
    @Post("/map")
    fun getBodyMap(body: Map<String, String>): Map<String, String>

    @Post("/list")
    fun getBodyList(body: List<String>): List<String>

    @Post("/customMap")
    fun getBodyCustomMap(body: CustomMap): Map<String, String>?

    @Post("/customList")
    fun getBodyCustomList(body: CustomList): List<String>?

    @Post("/string")
    fun getBodyString(body: String): String
}