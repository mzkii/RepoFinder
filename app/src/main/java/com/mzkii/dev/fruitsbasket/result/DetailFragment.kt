package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mzkii.dev.fruitsbasket.R

// リポジトリの詳細画面．WebView で単に URL 先を表示させるだけ．
class DetailFragment : Fragment() {

  // SafeArgs を使って，遷移元の ResultFragment から渡ってきた URL を取得しておく．
  // repositoryUrl が最初に参照されたタイミングで初期化される．
  private val repositoryUrl: String by lazy {
    DetailFragmentArgs.fromBundle(requireArguments()).repositoryUrl
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initView(view)
    Toast.makeText(context, "$repositoryUrl を表示しています", Toast.LENGTH_LONG).show()
  }

  private fun initView(view: View) {
    // webView で github のリポジトリを表示する．
    val webView = view.findViewById<WebView>(R.id.webView)
    webView.loadUrl(repositoryUrl)
    webView.webViewClient = WebViewClient()
  }
}
