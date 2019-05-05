package com.mzkii.dev.fruitsbasket.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mzkii.dev.fruitsbasket.R
import com.mzkii.dev.fruitsbasket.api.APIClient
import com.mzkii.dev.fruitsbasket.api.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ResultViewModel : ViewModel() {

  private val compositeDisposable = CompositeDisposable()

  val repositoryList = MutableLiveData<List<Repository>>()
  val isLoading = MutableLiveData<Boolean>()
  val toast = MutableLiveData<Int>()

  fun fetchRepositoryList(gitHubId: String) {
    APIClient
      .retrofit
      .fetchReposList(gitHubId)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())

      // 読み込み開始時に isLoading を true にしてグルグルを出す
      .doOnSubscribe { isLoading.value = true }

      // 読み込み終了時に isLoading を false にしてグルグルを消す
      .doFinally { isLoading.value = false }

      .subscribeBy(
        // 取得できたら LiveData にリストを反映させる
        onSuccess = { repositoryList.value = it },

        // 取得に失敗したらエラーログを出す
        onError = {
          Timber.tag(ResultViewModel::class.java.simpleName).e(it)
          repositoryList.value = emptyList()
          toast.value = R.string.faild_fetch_repository_error_message
        }
      ).addTo(compositeDisposable)
  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}