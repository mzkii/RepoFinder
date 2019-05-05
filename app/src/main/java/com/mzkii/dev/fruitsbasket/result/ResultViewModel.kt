package com.mzkii.dev.fruitsbasket.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mzkii.dev.fruitsbasket.api.Repository

class ResultViewModel : ViewModel() {

  val repositoryList = MutableLiveData<List<Repository>>()

  fun fetchRepositoryList(gitHubId: String) {
    repositoryList.value = listOf()
  }
}