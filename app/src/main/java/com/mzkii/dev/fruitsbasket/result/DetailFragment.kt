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

class DetailFragment : Fragment() {

  private val repositoryUrl: String by lazy {
    DetailFragmentArgs.fromBundle(requireArguments()).repositoryUrl
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_detail, container, false)
    val webView = view.findViewById<WebView>(R.id.webView)
    webView.loadUrl(repositoryUrl)
    webView.webViewClient = WebViewClient()
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Toast.makeText(context, "url = $repositoryUrl", Toast.LENGTH_SHORT).show()
  }
}
