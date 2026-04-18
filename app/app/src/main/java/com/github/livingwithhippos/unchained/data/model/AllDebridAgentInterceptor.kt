package com.github.livingwithhippos.unchained.data.model

import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp interceptor that appends the required `agent` query parameter to every AllDebrid API
 * request. The AllDebrid v4 API mandates this parameter to identify the calling application.
 */
object AllDebridAgentInterceptor : Interceptor {

    private const val AGENT_VALUE = "unchained-android"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("agent", AGENT_VALUE).build()
        return chain.proceed(original.newBuilder().url(url).build())
    }
}
