package com.mzkii.dev.fruitsbasket.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.fruitsbasket.R
import com.mzkii.dev.fruitsbasket.api.Repository
import com.mzkii.dev.fruitsbasket.result.RepositoryAdapter.RecyclerViewHolder

class RepositoryAdapter(
  private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<RecyclerViewHolder>() {

  private val repositoryItem = mutableListOf<Repository>()

  fun update(item: List<Repository>) {
    repositoryItem.clear()
    repositoryItem.addAll(item)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ) : RecyclerViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val view = layoutInflater.inflate(R.layout.item_repository, parent, false)
    return RecyclerViewHolder(view)
  }

  override fun getItemCount(): Int {
    return repositoryItem.size
  }

  override fun onBindViewHolder(
    holder: RecyclerViewHolder, position: Int
  ) {
    val item = repositoryItem[position]
    holder.repoName.text = item.name
    holder.repoUrl.text = item.html_url
    holder.parent.setOnClickListener { onItemClicked(item.html_url) }
  }

  class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val repoName: TextView = view.findViewById(R.id.repoName)
    val repoUrl: TextView = view.findViewById(R.id.repoUrl)
    val parent: ConstraintLayout = view.findViewById(R.id.parentComponent)
  }
}