package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.fruitsbasket.R
import timber.log.Timber

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
      Timber.tag(this::class.java.simpleName).d("list = $repositoryList")
    })

    // エラーメッセージを購読する．
    viewModel.toast.observe(this, Observer {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })

//    val directions = ResultFragmentDirections.actionResultFragmentToDetailFragment(0)
//    findNavController().navigate(directions)
  }
}
