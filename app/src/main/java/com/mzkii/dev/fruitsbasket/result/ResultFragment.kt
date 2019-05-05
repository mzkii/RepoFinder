package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.fruitsbasket.R

// ユーザーのリポジトリ一覧を表示する画面
class ResultFragment : Fragment() {

  private lateinit var viewModel: ResultViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_result, container, false)
    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    // viewModel を取得する．
    val activity = requireActivity()
    viewModel = ViewModelProviders.of(activity).get(ResultViewModel::class.java)

    // リポジトリのリストの内容を購読する．内容が変化したら実行される．
    viewModel.repositoryList.observe(this, Observer { repositoryList ->

      // 結果が返ってきたらグルグルを非表示にする.
      view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    })

//    val directions = ResultFragmentDirections.actionResultFragmentToDetailFragment(0)
//    findNavController().navigate(directions)
  }
}
