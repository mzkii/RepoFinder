package com.mzkii.dev.fruitsbasket.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object APIClient {
  private const val BASE_URL = "https://api.github.com/"
  private val moshi = Moshi.Builder().build()
  val retrofit = Retrofit
    .Builder()
    .client(OkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(GitHubService::class.java)
}