package com.mzkii.dev.fruitsbasket.search

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.mzkii.dev.fruitsbasket.R
import com.mzkii.dev.fruitsbasket.result.ResultActivity

class SearchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    val button = findViewById<Button>(R.id.button)
    val editText = findViewById<AppCompatEditText>(R.id.appCompatEditText)

    button.setOnClickListener {
      val inputText = editText.text.toString()
      if (inputText.isEmpty()) {
        Toast.makeText(this, R.string.no_github_id_message, Toast.LENGTH_SHORT).show()
      } else {
        startActivity(ResultActivity.createIntent(this, inputText))
      }
    }
  }
}
