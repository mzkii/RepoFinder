package com.mzkii.dev.fruitsbasket.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mzkii.dev.fruitsbasket.R

// 検索結果を表示したり，リポジトリの詳細情報を表示する画面．
// ResultFragment と DetailFragment を保持している Activity です．
class ResultActivity : AppCompatActivity() {

  // よくある activity を作るパターン
  companion object {
    private const val EXTRA_GITHUB_ID = "EXTRA_GITHUB_ID"

    fun createIntent(
      context: Context,
      githubId: String
    ): Intent {

      // intent を作って，EXTRA_GITHUB_ID をキーに，githubId を putExtra する
      val intent = Intent(context, ResultActivity::class.java)
      intent.putExtra(EXTRA_GITHUB_ID, githubId)
      return intent
    }
  }

  // id を初めて参照したタイミングで， EXTRA_GITHUB_ID をキーにして，
  // 事前に putExtra しておいた github id を取得する．
  private val id by lazy {
    intent.getStringExtra(EXTRA_GITHUB_ID)
  }

  // viewModel を保持する変数．
  private lateinit var viewModel: ResultViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)
    initView()
    observeState()
  }

  private fun initView() {
    // navigation の設定を諸々しておく．
    // 参考記事: https://qiita.com/jimmysharp/items/06f3157152fbc4cd5113
    val navController = findNavController(R.id.main_nav_host)
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  private fun observeState() {
    // viewModel を取得する．
    viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

    // リポジトリをネットワーク経由で取得する．
    // リポジトリの取得が完了したら，ResultFragment にあるリストが自動的に更新される．
    viewModel.fetchRepositoryList(id)

    // 読み込み状態を購読する．
    // 読み込み開始時に isLoading が true になり，終了時に isLoading が false になるので，
    // 読み込んでいる間だけグルグルを表示するようにしておく．
    viewModel.isLoading.observe(this, Observer { isLoading ->
      val progressBar = findViewById<ProgressBar>(R.id.progressBar)
      progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    })

    // エラーメッセージを購読する．
    // 何か文字が来たら Toast で表示しておく．
    viewModel.toast.observe(this, Observer {
      Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    })
  }

  // Navigation のバックボタンなどの制御
  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.main_nav_host).navigateUp()
  }
}
