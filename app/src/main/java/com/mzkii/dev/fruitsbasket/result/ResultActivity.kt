package com.mzkii.dev.fruitsbasket.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mzkii.dev.fruitsbasket.R

// 検索結果を表示したり，リポジトリの詳細情報を表示する画面
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

  // id を初めて参照したタイミングで，
  // EXTRA_GITHUB_ID をキーに，事前に putExtra した github id を取得する
  private val id by lazy {
    intent.getStringExtra(EXTRA_GITHUB_ID)
  }

  // viewModel を保持する変数
  private lateinit var viewModel: ResultViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)

    // navigation の設定諸々
    // 参考記事: https://qiita.com/jimmysharp/items/06f3157152fbc4cd5113
    val navController = findNavController(R.id.main_nav_host)
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)

    // viewModel を取得する
    viewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)

    // リポジトリを取得する
    viewModel.fetchRepositoryList(id)
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.main_nav_host).navigateUp()
  }
}
