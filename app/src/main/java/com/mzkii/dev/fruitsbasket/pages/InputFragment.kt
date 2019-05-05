package com.mzkii.dev.fruitsbasket.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mzkii.dev.fruitsbasket.R

class InputFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_input, container, false)
    view.findViewById<Button>(R.id.button).setOnClickListener {
      val inputText = (view.findViewById<AppCompatEditText>(R.id.editText).text).toString()
      if (inputText.isEmpty()) {
        Toast.makeText(context, "id を入力してください", Toast.LENGTH_SHORT).show()
      } else {
        val directions = InputFragmentDirections.actionInputFragmentToResultFragment(inputText)
        findNavController().navigate(directions)
      }
    }
    return view
  }
}
