package com.mzkii.dev.fruitsbasket.search

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.mzkii.dev.fruitsbasket.R
import com.mzkii.dev.fruitsbasket.result.ResultActivity

// 最初の github id を入力してもらう画面
class SearchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    // view を取得する
    val button = findViewById<Button>(R.id.button)
    val editText = findViewById<AppCompatEditText>(R.id.appCompatEditText)

    // button 押したときの処理
    button.setOnClickListener {
      val inputText = editText.text.toString()
      if (inputText.isEmpty()) {
        Toast.makeText(this, "id を入力してください", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }

      // github id が入力されていれば次の画面へ
      startActivity(ResultActivity.createIntent(this, inputText))
    }
  }
}
