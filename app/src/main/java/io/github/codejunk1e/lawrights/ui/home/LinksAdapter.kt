package io.github.codejunk1e.lawrights.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.codejunk1e.lawrights.databinding.LawCardLinkItemBinding
import io.github.codejunk1e.lawrights.models.LinksModel
import io.github.codejunk1e.lawrights.ui.home.LinksAdapter.LinksViewHolder
import javax.inject.Inject

class LinksAdapter @Inject constructor(
    val items :List<LinksModel>,
    ) :RecyclerView.Adapter<LinksViewHolder>() {

    lateinit var clickFnx : (LinksModel) -> Unit

    inner class LinksViewHolder(val binding: LawCardLinkItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(card: LinksModel) {
            binding.apply {
                title.text = card.title
                rotLayout.setCardBackgroundColor(ContextCompat.getColor(binding.rotLayout.context, card.color))
                root.setOnClickListener {
                    clickFnx(card)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder =
        LinksViewHolder(LawCardLinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size
}