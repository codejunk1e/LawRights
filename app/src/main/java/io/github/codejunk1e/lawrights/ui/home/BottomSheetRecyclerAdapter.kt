package io.github.codejunk1e.lawrights.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.codejunk1e.lawrights.databinding.LawBotomsheetRecyclerItemBinding
import io.github.codejunk1e.lawrights.datasource.local.RightsEntity
import io.github.codejunk1e.lawrights.ui.home.BottomSheetRecyclerAdapter.BSRVViewHolder
import javax.inject.Inject

class BottomSheetRecyclerAdapter @Inject constructor()
    :RecyclerView.Adapter<BSRVViewHolder>() {

    var items :List<RightsEntity> = listOf()

    inner class BSRVViewHolder(val binding : LawBotomsheetRecyclerItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: RightsEntity) {
             binding.apply {

                 val string = item.title
                     .split(" ")
                     .map { it.lowercase() }
                     .map { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
                     .joinToString(" ")

                 title.text = string
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BSRVViewHolder =
        BSRVViewHolder(LawBotomsheetRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BSRVViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}