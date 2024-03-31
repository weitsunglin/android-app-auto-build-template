package com.example.android_app_template

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class NetworkUtils {

    private val client = OkHttpClient()

    suspend fun httpRequestWithokhttp3(url: String): String {
        val request = Request.Builder()
            .url(url)
            .build()

        val response: Response = withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }

        return if (response.isSuccessful) {
            response.body?.string() ?: "No Response"
        } else {
            "Failed to execute request"
        }
    }
}