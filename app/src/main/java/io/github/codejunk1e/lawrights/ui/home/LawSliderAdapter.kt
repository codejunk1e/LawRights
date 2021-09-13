package io.github.codejunk1e.lawrights.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.codejunk1e.lawrights.databinding.LawCardItemBinding
import io.github.codejunk1e.lawrights.models.CardModel
import javax.inject.Inject

class LawSliderAdapter @Inject constructor(
    val items :List<CardModel>
    ) : RecyclerView.Adapter<LawSliderAdapter.LawSliderViewHolder>() {

    inner class LawSliderViewHolder(val binding : LawCardItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(card: CardModel) {
             binding.apply {
                 rootLayout.setBackgroundResource(card.image)
                 binding.rootLayout.context.apply {
                     caption.text = getString(card.caption)
                     description.text = getString(card.desc)
                     actionText.text = getString(card.actionText)
                 }
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawSliderViewHolder =
        LawSliderViewHolder(LawCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LawSliderViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}