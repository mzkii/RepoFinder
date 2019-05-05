package com.mzkii.dev.fruitsbasket.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.fruitsbasket.R
import timber.log.Timber

// ユーザーのリポジトリ一覧を表示する画面
class ResultFragment : Fragment() {

  // recyclerView で使う Adapter を定義しておく．
  // リストのアイテムがクリックされたら，そのリポジトリの ID が clickedItemId として渡ってくるので，
  // それを使って DetailFragment に遷移させる．
  private val adapter = RepositoryAdapter { clickedItemId ->

    // 詳しくは result_navigation.xml を参照．
    // detailFragment の argument タグに id が指定されているので，clickedItemId を渡しておく．
    val directions =
      ResultFragmentDirections.actionResultFragmentToDetailFragment(clickedItemId)

    // これで DetailFragment に遷移する．
    findNavController().navigate(directions)
  }

  private lateinit var viewModel: ResultViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_result, container, false)
    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    recyclerView.addItemDecoration(itemDecoration)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    // viewModel を取得する．
    val activity = requireActivity()
    viewModel = ViewModelProviders.of(activity).get(ResultViewModel::class.java)

    // リポジトリのリストの内容を購読する．内容が変化したら実行される．
    viewModel.repositoryList.observe(this, Observer { repositoryList ->
      Timber.tag(this::class.java.simpleName).d("list = $repositoryList")
      adapter.update(repositoryList)
    })

    // エラーメッセージを購読する．
    viewModel.toast.observe(this, Observer {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })
  }
}
