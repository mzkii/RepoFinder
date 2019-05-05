package com.mzkii.dev.fruitsbasket.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
  @GET("users/{user}/repos")
  fun fetchReposList(@Path("user") user: String): Single<List<Repository>>
}