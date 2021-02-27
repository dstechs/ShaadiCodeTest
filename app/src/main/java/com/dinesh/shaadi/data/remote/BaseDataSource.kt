package com.dinesh.shaadi.data.remote

import com.dinesh.shaadi.util.RequestResource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): RequestResource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return RequestResource.success(body)
            }
            return RequestResource.error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return RequestResource.error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): RequestResource<T> {
        return RequestResource.error("Network call has failed for a following reason: $message")
    }

}