package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.fruitsbasket.R

// ユーザーのリポジトリ一覧を表示する画面
class ResultFragment : Fragment() {

  // recyclerView で使う Adapter を定義しておく．
  // リストのアイテムがクリックされたら，そのリポジトリの URL が clickedItemUrl として渡ってくるので，
  // それを使って DetailFragment に遷移させる．
  private val adapter = RepositoryAdapter { clickedItemUrl ->

    // 詳しくは result_navigation.xml を参照．
    // detailFragment の argument タグに repositoryUrl が指定されているので，clickedItemUrl を渡しておく．
    val directions =
      ResultFragmentDirections.actionResultFragmentToDetailFragment(clickedItemUrl)

    // これで clickedItemUrl を DetailFragment に渡して遷移する．
    findNavController().navigate(directions)
  }

  private lateinit var viewModel: ResultViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_result, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initView(view)
    observeState()
  }

  private fun initView(view: View) {
    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    recyclerView.addItemDecoration(itemDecoration)  // リストのアイテム一つずつに上下の仕切り線を追加
    recyclerView.layoutManager = LinearLayoutManager(context) // 縦にリストを並べる
    recyclerView.adapter = adapter  // Adapter を設定
  }

  private fun observeState() {
    // viewModel を取得する．
    val activity = requireActivity()
    viewModel = ViewModelProviders.of(activity).get(ResultViewModel::class.java)

    // リポジトリのリストの内容を購読する．
    // 内容が変化したら勝手に中身が実行される．
    viewModel.repositoryList.observe(this, Observer { repositoryList ->
      adapter.update(repositoryList)
    })
  }
}
