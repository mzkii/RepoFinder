package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mzkii.dev.fruitsbasket.R

class ResultFragment : Fragment() {

  private lateinit var viewModel: ResultViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_result, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    // viewModel を取得する
    val activity = requireActivity()
    viewModel = ViewModelProviders.of(activity).get(ResultViewModel::class.java)
    Toast.makeText(context, "sample = ${viewModel.hello}", Toast.LENGTH_SHORT).show()

//    val directions = ResultFragmentDirections.actionResultFragmentToDetailFragment(0)
//    findNavController().navigate(directions)
  }
}
