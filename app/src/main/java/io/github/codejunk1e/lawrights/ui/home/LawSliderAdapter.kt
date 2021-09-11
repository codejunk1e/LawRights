package io.github.codejunk1e.lawrights.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.codejunk1e.lawrights.databinding.LawCardItemBinding
import io.github.codejunk1e.lawrights.models.CardModel

class LawSliderAdapter(val items :List<CardModel>) : RecyclerView.Adapter<LawSliderAdapter.LawSliderViewHolder>() {

    inner class LawSliderViewHolder(val binding : LawCardItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(card: CardModel) {
             binding.apply {
                 rootLayout.setBackgroundResource(card.image)
                 caption.text = card.caption
                 description.text = card.desc
                 actionText.text = card.actionText
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawSliderViewHolder =
        LawSliderViewHolder(LawCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LawSliderViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}