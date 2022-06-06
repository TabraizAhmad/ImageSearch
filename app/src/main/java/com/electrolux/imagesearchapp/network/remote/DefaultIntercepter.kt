package com.electrolux.imagesearchapp.network.remote

import com.electrolux.imagesearchapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class DefaultIntercepter:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.FLICKR_ACCESS_KEY)
            .addQueryParameter("method","flickr.photos.search")
            .addQueryParameter("format","json")
            .addQueryParameter("extras","media")
            .addQueryParameter("extras","url_q")
            .addQueryParameter("nojsoncallback","true")
            .build()

        // Request customization: add request headers

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)

    }
}