package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mzkii.dev.fruitsbasket.R

class DetailFragment : Fragment() {

  private val requestId: Long by lazy {
    DetailFragmentArgs.fromBundle(requireArguments()).id
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Toast.makeText(context, "id = $requestId", Toast.LENGTH_SHORT).show()
  }
}
