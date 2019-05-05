package com.mzkii.dev.fruitsbasket.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mzkii.dev.fruitsbasket.api.APIClient
import com.mzkii.dev.fruitsbasket.api.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ResultViewModel : ViewModel() {

  // RxJava の購読をまとめてキャンセルするためのクラス．
  private val compositeDisposable = CompositeDisposable()

  // リポジトリのリストを保持する LiveData．
  val repositoryList = MutableLiveData<List<Repository>>()

  // リポジトリリスト読み込み状況を保持する LiveData．
  val isLoading = MutableLiveData<Boolean>()

  // エラーメッセージを保持する LiveData．
  val toast = MutableLiveData<String>()

  // gitHubId のパブリックなリポジトリを取得する．
  fun fetchRepositoryList(gitHubId: String) {
    APIClient
      .retrofit
      .fetchReposList(gitHubId)

      // fetchReposList を実行するスレッドを指定する．
      // Schedulers.io() : 入出力用のスレッド．
      .subscribeOn(Schedulers.io())

      // 結果を受け取るスレッドを指定する．
      // 結果を RecyclerView に反映させたいので MainThread(=UI Thread) を指定する．
      .observeOn(AndroidSchedulers.mainThread())

      // 読み込み開始時に isLoading を true にしてグルグルを出す．
      .doOnSubscribe { isLoading.value = true }

      // 読み込み終了時に isLoading を false にしてグルグルを消す．
      .doFinally { isLoading.value = false }

      .subscribeBy(
        // 取得できたら LiveData にリストを反映させる．
        onSuccess = { repositoryList.value = it },

        // 取得に失敗したらエラーログを出す．
        onError = {
          Timber.tag(ResultViewModel::class.java.simpleName).e(it)
          repositoryList.value = emptyList()
          toast.value = "リポジトリの取得に失敗しました"
        }

        // 忘れずに追加しておく．
      ).addTo(compositeDisposable)
  }

  override fun onCleared() {
    // ViewModel が消えた = Activity が破棄されたタイミングなので，
    // ここで dispose をして fetchReposList の購読をキャンセルする．
    // ※ 通信中に Activity が死んでしまって，その後に通信完了して View をいじってしまうと実行時エラーで落ちてしまう．
    compositeDisposable.dispose()
  }
}