package com.mzkii.dev.fruitsbasket.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mzkii.dev.fruitsbasket.R

class ResultActivity : AppCompatActivity() {

  companion object {
    private const val EXTRA_GITHUB_ID = "EXTRA_GITHUB_ID"

    fun createIntent(
      context: Context,
      githubId: String
    ): Intent {
      val intent = Intent(context, ResultActivity::class.java)
      intent.putExtra(EXTRA_GITHUB_ID, githubId)
      return intent
    }
  }

  private val id by lazy {
    intent.getStringExtra(EXTRA_GITHUB_ID)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)
    val navController = findNavController(R.id.main_nav_host)
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
    Toast.makeText(this, "id = $id", Toast.LENGTH_SHORT).show()
  }

  override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.main_nav_host).navigateUp()
  }
}
